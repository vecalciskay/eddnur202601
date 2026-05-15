package nur.prog3.bancored;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ComandoSaldo extends ComandoBanco {
    private static final Logger logger = LogManager.getRootLogger();
    private Cuenta cliente;

    public ComandoSaldo(ProtocoloBanco protocolo, Cuenta clt) {
        this.protocolo = protocolo;
        this.nombre = "Comando Saldo";
        this.regexPrincipal = ConfigRed.CMD_SALDO;
        this.cliente = clt;
    }

    @Override
    public boolean atenderComandoSegunProtocolo() throws IOException {
        Integer[] cis = new Integer[1];
        logger.info("Revisa si el cmd [" + primeraLinea + "] es correcto");
        if (!expresionValida(primeraLinea, ConfigRed.CMD_SALDO, cis)) {
            logger.error("El comando no es correcto o un error al crear obtener cuenta");
            protocolo.getSalida().println("ERROR");
            protocolo.getSalida().flush();
        } else {
            protocolo.getSalida().println("OK - Saldo " + cliente.getSaldo());
            protocolo.getSalida().flush();
            logger.info(">>> OK - Saldo " + cliente.getSaldo());
        }
        return false;
    }

    @Override
    public void ejecutarComoCliente(Object[] argumentos) throws IOException {

    }
}
