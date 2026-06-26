package nur.prog3.redes.srvweb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class ServidorWebVentana extends JFrame implements PropertyChangeListener {
    private static final Logger logger = LogManager.getRootLogger();
    private static final int PUERTO = 9999;
    private final ServidorWeb servidor;
    private final JButton btnIniciarServidor;
    private final JButton btnPararServidor;
    private final JLabel lblStatusServidor;

    public ServidorWebVentana() {
        this.servidor = new ServidorWeb(PUERTO);
        this.servidor.anadirObservador(this);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Servidor Web");

        this.getContentPane().setLayout(new BorderLayout(5, 5));

        btnIniciarServidor = new JButton("Iniciar Servidor");
        btnIniciarServidor.addActionListener(e -> btnIniciarServidorClicked());
        this.getContentPane().add(btnIniciarServidor, BorderLayout.WEST);
        btnIniciarServidor.setFont(btnIniciarServidor.getFont().deriveFont(24.0f));

        btnPararServidor = new JButton("Parar Servidor");
        btnPararServidor.addActionListener(e -> btnPararServidorClicked());
        this.getContentPane().add(btnPararServidor, BorderLayout.EAST);
        btnPararServidor.setFont(btnPararServidor.getFont().deriveFont(24.0f));

        lblStatusServidor = new JLabel(servidor.getInfo());
        this.getContentPane().add(lblStatusServidor, BorderLayout.SOUTH);
        lblStatusServidor.setFont(lblStatusServidor.getFont().deriveFont(24.0f));

        this.pack();
        this.setVisible(true);
    }

    private void btnPararServidorClicked() {
        if (servidor.getStatus() != EnumStatus.COMENZADO) {
            logger.info("Servidor ya esta parado");
            return;
        }

        servidor.pararServidor();
        logger.info("Servidor parado");
    }

    private void btnIniciarServidorClicked() {
        if (servidor.getStatus() == EnumStatus.COMENZADO) {
            logger.info("Servidor ya esta iniciado, no hace nada");
            return;
        }
        try {
            servidor.comenzar();
            logger.info("Servidor ya esta iniciado");
        } catch (IOException e) {
            logger.error("Error al iniciar", e);
            JOptionPane.showMessageDialog(this,
                    "Error al iniciar Servidor",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        lblStatusServidor.setText(servidor.getInfo());
    }

    static void main() {
        new ServidorWebVentana();
    }
}
