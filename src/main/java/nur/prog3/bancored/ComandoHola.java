package nur.prog3.bancored;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComandoHola extends ComandoBanco {
    private static final Logger logger = LogManager.getRootLogger();
    private int ci;
    public ComandoHola(ProtocoloBanco protocolo) {
        this.protocolo = protocolo;
        this.nombre = "Comando Hola";
        this.regexPrincipal = ConfigRed.REGEX_HOLA;
        this.ci = 0;
    }

    @Override
    public boolean atenderComandoSegunProtocolo() throws IOException {
        Integer[] cis = new Integer[1];
        logger.info("Revisa si el cmd [" + primeraLinea + "] es correcto");
        if (!expresionValida(primeraLinea, ConfigRed.REGEX_HOLA, cis)) {
            logger.error("El comando no es correcto o un error al crear obtener cuenta");
            protocolo.getSalida().println("ERROR");
            protocolo.getSalida().flush();
        } else {
            this.ci = cis[0];
            protocolo.getSalida().println("OK");
            protocolo.getSalida().flush();
            logger.info(">>> OK");
        }
        return false;
    }

    @Override
    public boolean expresionValida(String expr, String regex, Object[] resultado) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expr);

        if (matcher.find()) {
            logger.info("Cumple con la regex");
            if (regex.equals(ConfigRed.REGEX_HOLA)) {
                resultado[0] = Integer.parseInt(matcher.group(1));
                logger.info("El argumento para el comando es: " + resultado[0]);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void ejecutarComoCliente(Object[] argumentos) throws IOException {
        this.ci = (Integer)argumentos[0];
        protocolo.getSalida().println("HOLA " + this.ci);
        protocolo.getSalida().flush();
        logger.info(">>> HOLA " + this.ci);
        String linea = protocolo.getEntrada().readLine();
        logger.info("<<< " + linea);
    }

    public int getCi() {
        return ci;
    }
}
