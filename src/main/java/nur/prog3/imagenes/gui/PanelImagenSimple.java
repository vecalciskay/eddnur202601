package nur.prog3.imagenes.gui;

import nur.prog3.imagenes.objetos.Imagen;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanelImagenSimple extends JPanel
    implements PropertyChangeListener {
    private final Imagen modelo;

    public PanelImagenSimple(Imagen modelo) {
        this.modelo = modelo;
        this.modelo.addObserver(this);
    }

    @Override
    public Dimension getPreferredSize() {
        if (modelo == null)
            return new Dimension(400,400);
        return new Dimension(this.modelo.getAncho(),
                this.modelo.getAlto());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(modelo != null) {
            modelo.dibujar(g);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
