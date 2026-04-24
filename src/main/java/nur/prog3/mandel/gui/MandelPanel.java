package nur.prog3.mandel.gui;

import nur.prog3.mandel.objects.Imagen;
import nur.prog3.mandel.objects.MatrizMandel;

import javax.swing.*;
import java.awt.*;

public class MandelPanel extends JPanel {
    private final MatrizMandel modelo;

    public MandelPanel(MatrizMandel modelo) {
        this.modelo = modelo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Imagen img = modelo.getImagen();
        img.dibujar(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(modelo.getAncho(), modelo.getAlto());
    }
}
