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

public class CuentaCliente {
    private final static Logger logger = LogManager.getRootLogger();
    public static void main(String[] args)
            throws IOException {
        logger.info("Se conecta al ervidor en puerto: " + ConfigRed.PUERTO);

        ProtocoloBanco protocolo = ProtocoloBanco.crearNuevo();
        protocolo.hola(5738);
        protocolo.depositar(400);
        protocolo.fin();
    }
}
