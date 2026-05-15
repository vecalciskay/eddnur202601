package nur.prog3.bancored;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ComandoFin extends ComandoBanco {
    private static final Logger logger = LogManager.getRootLogger();
    public ComandoFin(ProtocoloBanco protocolo) {
        this.protocolo = protocolo;
        this.nombre = "Comando Fin";
        this.regexPrincipal = ConfigRed.CMD_FIN;
    }

    @Override
    public boolean atenderComandoSegunProtocolo() throws IOException {
        protocolo.getSalida().println("OK");
        logger.info(">>> OK");

        return true;
    }

    @Override
    public void ejecutarComoCliente(Object[] argumentos) throws IOException {
        protocolo.getSalida().println(ConfigRed.CMD_FIN);
        protocolo.getSalida().flush();
        logger.info(">>> FIN");

        String ok = protocolo.getEntrada().readLine();
        logger.info("<<< " + ok);
    }
}
