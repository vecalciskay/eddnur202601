package nur.prog3.redes.srvweb;

import nur.prog3.listas.Lista;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

public class ProtocoloWeb implements Runnable {
    private final Socket clt;
    private final OutputStream salida;
    private final InputStream entrada;
    private static Logger logger = LogManager.getRootLogger();
    private static final String REGEX_GET = "^GET\\s/op\\?(q|i)=(.+)\\sHTTP/1\\.1$";
    private static final String MIME_HTML =  "text/html";
    private static final String MIME_JPG = "image/jpeg";
    private static final String COMANDO_TEXTO = "q";
    private static final String COMANDO_IMAGEN = "i";

    public ProtocoloWeb(Socket cliente) throws IOException {
        logger.info("Construyendo ProtocoloWeb");
        this.clt = cliente;
        this.salida = clt.getOutputStream();
        this.entrada = clt.getInputStream();
        logger.info("ProtocoloWeb creados los canales de entrada y salida");
    }

    @Override
    public void run() {
        logger.info("ProtocoloWeb run");
        // Lee toda la entrada en un string y la analiza para poder dar la respuesta
        BufferedReader in = new BufferedReader(
                new InputStreamReader(entrada, StandardCharsets.UTF_8));
        Lista<String> solicitud = new Lista<>();
        try {
            while (in.ready()) {
                solicitud.adicionar(in.readLine());
            }
            if (solicitud.tamano() == 0) {
                logger.warn("Solicitud vacia, no hace nada");
                return;
            }
            logger.info("ProtocoloWeb solicitud recibida: " + solicitud.toString());

            String comandoGet = solicitud.obtener(1);
            Pattern pattern = Pattern.compile(REGEX_GET);
            Matcher matcher = pattern.matcher(comandoGet);
            if (matcher.find()) {
                String comando = matcher.group(1);
                String expresion = decodificarUrl(matcher.group(2));
                logger.info("La expresion es: " + expresion);

                if (comando.equals(COMANDO_TEXTO))
                    responderComandoTexto(expresion);
                else if (comando.equals(COMANDO_IMAGEN))
                    responderComandoImagen(expresion);
                logger.info("Respondio de acuerdo al comando");
            } else {
                responderComandoNoConocido(comandoGet);
            }

        } catch(IOException e) {
            logger.error("ProtocoloWeb error al leer la solicitud", e);
        } finally {
            cerrar();
        }
        logger.info("ProtocoloWeb finalizado");
    }

    private void responderComandoNoConocido(String comandoGet) {
        String html = crearError(comandoGet);
        String encabezado =
                crearEncabezado(HTTP_NOT_FOUND, "Not found", MIME_HTML, html.length());
        enviar(encabezado, html.getBytes(StandardCharsets.UTF_8));
    }

    private String crearEncabezado(int code, String msg, String mime, int bytes) {
        return "HTTP/1.1 " + code + " " + msg + "\r\n" +
                        "Content-Type: " + mime + "; charset=UTF-8\r\n" +
                        "Content-Length: " + bytes + "\r\n" +
                        "Connection: close\r\n" +
                        "\r\n"; // Linea en blanco obligatoria
    }

    private String crearError(String comandoGet) {
        return "<html><body><h1>Error 404</h1><p>No se conoce ese comando</p></body></html>";
    }

    private void enviar(String encabezado, byte[] contents) {
        try {
            salida.write(encabezado.getBytes(StandardCharsets.UTF_8));
            salida.write(contents);
            salida.flush();
            salida.close();
        } catch (IOException e) {
            logger.error("No puede escribir en la salida",e);
        }
    }

    private void responderComandoImagen(String expresion) {
        logger.info("ProtocoloWeb responderComandoImagen");
        byte[] img = crearImagen(expresion);
        String encabezado = crearEncabezado(HTTP_OK, "Ok", MIME_JPG, img.length);
        enviar(encabezado, img);
    }

    private byte[] crearImagen(String expresion) {
        try {
            BufferedImage image = new BufferedImage(300, 300, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D g2d = image.createGraphics();
            g2d.drawString(expresion, 40, 40);
            g2d.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            logger.error("No pudo crear la imagen", e);
            return null;
        }
    }

    private void responderComandoTexto(String expresion) {
        logger.info("ProtocoloWeb responderComandoTexto");
        String html = crearHtml(expresion);
        String encabezado = crearEncabezado(HTTP_OK, "Ok", MIME_HTML, html.length());
        enviar(encabezado, html.getBytes(StandardCharsets.UTF_8));
    }

    private String crearHtml(String expresion) {
        return "<html><body><h1>Expresión matemática</h1><p>" +
                expresion +
                "</p></body></html>";
    }

    private String decodificarUrl(String encoded) {
        return URLDecoder.decode(encoded, StandardCharsets.UTF_8);
    }

    private void cerrar() {
        logger.info("ProtocoloWeb cerrando socket");
        if (clt != null) {
            try {
                clt.close();
            } catch (IOException e) {
                logger.error("ProtocoloWeb error al cerrar la socket", e);
            }
        }
    }
}
