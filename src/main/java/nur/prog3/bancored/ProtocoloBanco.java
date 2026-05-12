package nur.prog3.bancored;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * El protocolo tiene los siguientes comandos:
 *
 * Saludar al servidor para que sepa cual cuenta trabajar
 * >>> HOLA [ci]
 * <<< OK
 *
 * Depositar un monto en esa cuenta
 * >>> DEPOSITAR [double]
 * <<< OK - SALDO [xxx]
 *
 * Fin de conversacion
 * >>> FIN
 * <<< OK
 */
public class ProtocoloBanco implements Runnable {
    private static final Logger logger = LogManager.getRootLogger();
    private Socket cliente;
    private PrintWriter salida;
    private BufferedReader entrada;
    private BancoServidor banco;

    private ProtocoloBanco(Socket clt) throws IOException {
        cliente = clt;
        salida = new PrintWriter(cliente.getOutputStream());
        entrada = new BufferedReader(
                new InputStreamReader(cliente.getInputStream())
        );
    }

    public static ProtocoloBanco crearParaServer(Socket clt, BancoServidor bco)
            throws IOException {
        ProtocoloBanco protocolo =  new ProtocoloBanco(clt);
        protocolo.setBanco(bco);
        return protocolo;
    }

    public void setBanco(BancoServidor bco) {
        this.banco = bco;
    }

    public static ProtocoloBanco crearNuevo() throws IOException {
        InetAddress addr = InetAddress.getLocalHost();

        Socket clt = new Socket(addr, ConfigRed.PUERTO);
        ProtocoloBanco obj = new ProtocoloBanco(clt);

        return obj;
    }

    public void depositar(double monto) throws IOException {
        String msg = ConfigRed.CMD_DEPOSITO + " " + String.valueOf(monto);
        logger.info(">>> " + msg);
        salida.println(msg);

        String respuesta = entrada.readLine();
        logger.info("<<< " + respuesta);
        double[] saldoRespuesta = new double[1];
        if (expresionOkSaldoValida(respuesta,
                ConfigRed.RESPUESTA_OK_SALDO, saldoRespuesta)) {
            logger.error("No es OK, algo paso");
            throw new IOException("El monto es incorrecto o algo asi");
        }
        logger.info("Monto depositado");
    }

    private boolean expresionOkSaldoValida(
            String respuesta, String respuestaOkSaldo,
            double[] saldo) {
        Pattern regex = Pattern.compile(respuestaOkSaldo);
        Matcher matcher = regex.matcher(respuesta);

        if (matcher.find()) {
            saldo[0] = Double.parseDouble(matcher.group(1));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run() {
        Cuenta clt = null;
        try {
            clt = comandoHola();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (clt == null) {
            logger.info("Comando equivocado, termina todo");
            try {
                cerrarConexion();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        try {
            comandoDepositar(clt);
            comandoFin();
            logger.info(banco);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void comandoFin() throws IOException {
        String cmd = entrada.readLine();
        this.cerrarConexion();
    }

    private void comandoDepositar(Cuenta clt) throws IOException {

        String cmd = entrada.readLine();
        double[] monto = new double[1];
        if (expresionOkSaldoValida(cmd, ConfigRed.CMD_DEPOSITO, monto)) {
            clt.depositar(monto[0]);
            salida.println("OK - " + clt.getSaldo());
        } else {
            salida.println("ERROR");
        }
    }

    private void cerrarConexion() throws IOException {
        cliente.close();
    }

    private Cuenta comandoHola() throws IOException {
        String cmd = entrada.readLine();
        Cuenta cta = expresionHolaValida(cmd, ConfigRed.CMD_HOLA);
        return cta;
    }

    private Cuenta expresionHolaValida(String cmd, String cmdHola) {
        Pattern regex = Pattern.compile(cmdHola);
        Matcher matcher = regex.matcher(cmd);

        if (matcher.find()) {
            int ci = Integer.parseInt(matcher.group(1));
            Cuenta cta = null;
            for(Cuenta ctaEnBanco : banco.getCuentas()) {
                if (ctaEnBanco.getCi() == ci) {
                    cta = ctaEnBanco;
                }
            }
            if (cta == null) {
                cta = new Cuenta(ci);
                banco.addCuenta(cta);
            }
            return cta;
        } else {
            return null;
        }
    }

    public void hola(int ci) throws IOException {
        salida.println("HOLA " + ci);
        entrada.readLine();
    }

    public void fin() throws IOException {
        salida.println("FIN");
        entrada.readLine();
        cerrarConexion();
    }
}
