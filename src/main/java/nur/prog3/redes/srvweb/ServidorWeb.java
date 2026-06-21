package nur.prog3.redes.srvweb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServidorWeb implements Runnable {
    private static final Logger logger = LogManager.getRootLogger();
    private final int puerto;
    private EnumStatus status;
    private boolean pararServidor;
    private int clientesAtendidos;
    private final PropertyChangeSupport observador;
    private Thread threadServidor;

    public ServidorWeb(int puerto) {
        logger.debug("Crea ServidorWeb en puerto {}", puerto);
        this.puerto = puerto;
        this.status = EnumStatus.CREADO;
        this.pararServidor = false;
        this.clientesAtendidos = 0;
        this.observador = new PropertyChangeSupport(this);
    }

    public void anadirObservador(PropertyChangeListener listener) {
        this.observador.addPropertyChangeListener(listener);
    }

    public String getInfo() {
        if (this.status == EnumStatus.COMENZADO) {
            return this.status.toString() + "(" + puerto + ") - clientes: " + this.clientesAtendidos;
        }
        return this.status.toString();
    }

    public void comenzar() throws IOException {
        logger.info("Comenzando ServidorWeb, preparando el thread principal");
        threadServidor = new Thread(this);
        threadServidor.start();
    }

    public void pararServidor() {
        this.pararServidor = true;
        if (threadServidor != null && threadServidor.isAlive()) {
            try {
                Socket clt = new Socket("127.0.0.1", puerto);
                try (BufferedWriter writer =
                             new BufferedWriter(
                                     new OutputStreamWriter(clt.getOutputStream(), StandardCharsets.UTF_8))) {
                    writer.write("GET / HTTP/1.1\r\n");
                }
                clt.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.status = EnumStatus.PARADO;
        observador.firePropertyChange("SERVIDOR", true, false);
    }

    @Override
    public void run() {
        this.status = EnumStatus.COMENZADO;
        logger.info("Servidor web escuchando en puerto {}", puerto);
        observador.firePropertyChange("SERVIDOR", true, false);

        ServerSocket srv =  null;
        try {
            srv = new ServerSocket(puerto);
        } catch (IOException e) {
            logger.error("Error al abrir puerto", e);
            return;
        }
        while(!pararServidor) {

            Socket cliente = null;
            try {
                cliente = srv.accept();
                int valorAntiguo = clientesAtendidos;
                int valorNuevo = valorAntiguo + 1;
                clientesAtendidos =  valorNuevo;
                observador.firePropertyChange("CLIENTE", valorAntiguo, valorNuevo);

                ProtocoloWeb protocolo = new ProtocoloWeb(cliente);
                Thread threadProtocolo = new Thread(protocolo);
                threadProtocolo.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            srv.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public EnumStatus getStatus() {
        return status;
    }
}
