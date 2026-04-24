package nur.prog3.mandel.gui;

import nur.prog3.mandel.objects.MatrizMandel;

import javax.swing.*;
import java.awt.*;

public class MAndelFrame extends JFrame {
    private final MatrizMandel modelo;
    private final MandelPanel panel;

    public MAndelFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.getContentPane().setLayout(new BorderLayout());

        modelo = new MatrizMandel(500,500);
        modelo.hacerMandel();
        panel = new MandelPanel(modelo);

        this.getContentPane().add(panel, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MAndelFrame();
    }
}
