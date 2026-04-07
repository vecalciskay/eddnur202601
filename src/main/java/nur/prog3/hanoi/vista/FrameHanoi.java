package nur.prog3.hanoi.vista;

import nur.prog3.hanoi.objects.JuegoHanoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameHanoi extends JFrame {

    private final JuegoHanoi modelo;
    private final PanelHanoi panel;

    public FrameHanoi() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        modelo = new JuegoHanoi(3);
        modelo.inicializar();
        panel = new PanelHanoi(modelo);
        modelo.addObserver(panel);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, BorderLayout.CENTER);

        JButton btn = new JButton("Hacer");
        this.getContentPane().add(btn, BorderLayout.SOUTH);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnHacer_click();
            }
        });

        this.setVisible(true);
        this.pack();
    }

    private void btnHacer_click() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                modelo.resolver();
            }
        });

        t.start();
    }

    public static void main(String[] args) {
        new FrameHanoi();
    }
}
