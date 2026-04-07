package nur.prog3.hanoi.vista;

import nur.prog3.hanoi.objects.JuegoHanoi;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanelHanoi extends JPanel
        implements PropertyChangeListener {
    private final JuegoHanoi modelo;

    public PanelHanoi(JuegoHanoi m) {
        this.modelo = m;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        DibujadorHanoi dibujador = new DibujadorHanoi(modelo);
        dibujador.dibujar(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400,400);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
