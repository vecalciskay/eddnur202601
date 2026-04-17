package nur.prog3.imagenes.gui;

import nur.prog3.imagenes.objetos.Cuadrado;
import nur.prog3.imagenes.objetos.Figura;
import nur.prog3.imagenes.objetos.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrameImagenSimple extends JFrame {
    private final Logger logger = LogManager.getRootLogger();

    private final Imagen modelo;
    private final PanelImagenSimple panel;
    private final ArrayList<Figura> figuras;

    public FrameImagenSimple() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        modelo = new Imagen(500,500);
        figuras = new ArrayList<>();
        panel = new PanelImagenSimple(modelo, figuras);

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

        JMenuBar bar = new JMenuBar();
        this.setJMenuBar(bar);
        JMenu menu = new JMenu("Figuras");
        bar.add(menu);

        JMenuItem item = new JMenuItem("Cuadrado rojo");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFiguras_cuadradoClick(Color.red);
            }
        });
        menu.add(item);

        item = new JMenuItem("Cuadrado azul");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFiguras_cuadradoClick(Color.blue);
            }
        });
        menu.add(item);

        this.pack();
        this.setVisible(true);
    }

    private void menuFiguras_cuadradoClick(Color c) {
        logger.info("Click en opcion cuadrado del menu");

        Figura f = new Cuadrado(100,100,40, c);
        figuras.add(f);
        f.addObserver(panel);
        f.setPosicion(100,100);
    }

    private void lh_clicked() {
        int color = 0x0000FF00;
        this.modelo.lineaHorizontal(color, 100, 20);
    }

    public static void main(String[] args) {
        new FrameImagenSimple();
    }
}
