package nur.prog3.bancored;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComandoDeposito extends ComandoBanco {
    private static final Logger logger = LogManager.getRootLogger();
    private Cuenta cuenta;
    private int ci;

    public ComandoDeposito(ProtocoloBanco protocolo, Cuenta clt) {
        this.protocolo = protocolo;
        this.nombre = "Comando Deposito";
        this.regexPrincipal = ConfigRed.REGEX_DEPOSITO;
        this.cuenta = clt;
        this.ci = clt.getCi();
    }

    public ComandoDeposito(ProtocoloBanco protocolo, int ci) {
        this.protocolo = protocolo;
        this.nombre = "Comando Deposito";
        this.regexPrincipal = ConfigRed.REGEX_DEPOSITO;
        this.cuenta = null;
        this.ci = ci;
    }

    @Override
    public boolean atenderComandoSegunProtocolo() throws IOException {
        Double[] monto = new Double[1];
        if (expresionValida(primeraLinea, ConfigRed.REGEX_DEPOSITO, monto)) {
            cuenta.depositar(monto[0]);
            protocolo.getSalida().println("OK - " + cuenta.getSaldo());
            protocolo.getSalida().flush();
            logger.info(">>> OK - " + cuenta.getSaldo());
        } else {
            protocolo.getSalida().println("ERROR");
            protocolo.getSalida().flush();
            logger.info(">>> ERROR");
        }
        return false;
    }

    @Override
    public boolean expresionValida(String expr, String regex, Object[] resultado) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expr);

        if (matcher.find()) {
            if (regex.equals(ConfigRed.REGEX_DEPOSITO))
                resultado[0] = Double.parseDouble(matcher.group(1));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void ejecutarComoCliente(Object[] argumentos) throws IOException {
        Double monto = (Double)argumentos[0];
        String msg = ConfigRed.CMD_DEPOSITO + " " + monto.toString();
        logger.info(">>> " + msg);
        protocolo.getSalida().println(msg);
        protocolo.getSalida().flush();

        String respuesta = protocolo.getEntrada().readLine();
        logger.info("<<< " + respuesta);
        /*Double[] saldoRespuesta = new Double[1];
        if (expresionValida(respuesta,
                ConfigRed.RESPUESTA_OK_SALDO, saldoRespuesta)) {
            logger.error("No es OK, algo paso");
            throw new IOException("El monto es incorrecto o algo asi");
        }*/
        logger.info("Monto depositado");
    }
}
