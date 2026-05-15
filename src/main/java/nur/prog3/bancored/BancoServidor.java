package nur.prog3.bancored;

import nur.prog3.listas.Lista;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.ServerSocket;
import java.net.Socket;

public class BancoServidor {
    private static final Logger logger = LogManager.getRootLogger();
    private ServerSocket socketServer;
    private Lista<Cuenta> cuentas;

    private BancoServidor(ServerSocket srv) {
        this.socketServer = srv;
        cuentas = new Lista<>();
    }

    public static void main(String[] args) throws IOException {
        BancoServidor srv = BancoServidor.crear();
        srv.comenzar();
    }

    private void comenzar() throws IOException {
        logger.info("Escuchando en puerto " + ConfigRed.PUERTO);

        while(true) {
            Socket clt = socketServer.accept();
            logger.info("Cliente conectado");
            Thread t = new Thread(ProtocoloBanco.crearParaServer(clt, this));
            t.start();
        }
    }

    private static BancoServidor crear() throws IOException {
        return new BancoServidor(new ServerSocket(ConfigRed.PUERTO));
    }

    @Override
    public String toString() {
        return cuentas.toString();
    }

    public Lista<Cuenta> getCuentas() {
        return cuentas;
    }

    public void addCuenta(Cuenta cta) {
        cuentas.insertar(cta);
    }
}
