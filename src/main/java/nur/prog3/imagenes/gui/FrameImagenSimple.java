package nur.prog3.imagenes.gui;

import nur.prog3.imagenes.objetos.Imagen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameImagenSimple extends JFrame {
    private final Imagen modelo;
    private final PanelImagenSimple panel;

    public FrameImagenSimple() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        modelo = new Imagen(500,500);
        panel = new PanelImagenSimple(modelo);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, BorderLayout.CENTER);

        JButton lh = new JButton("Linea horizontal");
        lh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lh_clicked();
            }
        });
        this.getContentPane().add(lh, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }

    private void lh_clicked() {
        int color = 0x0000FF00;
        this.modelo.lineaHorizontal(color, 100, 20);
    }

    public static void main(String[] args) {
        new FrameImagenSimple();
    }
}
