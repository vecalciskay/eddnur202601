package nur.prog3.imagenes.gui;

import nur.prog3.imagenes.objetos.Figura;
import nur.prog3.imagenes.objetos.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class PanelImagenSimple extends JPanel
    implements PropertyChangeListener,
        MouseListener, MouseMotionListener {
    private final Logger logger = LogManager.getRootLogger();
    private final Imagen modelo;
    private final ArrayList<Figura> figuras;

    public PanelImagenSimple(Imagen modelo, ArrayList<Figura> figuras) {
        this.modelo = modelo;
        this.modelo.addObserver(this);
        this.figuras = figuras;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
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

        if (figuras != null) {
            for(Figura f: figuras) {
                f.dibujar(g);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        logger.info("Hizo click en la posicion " + e.getX() + "," + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(Figura f: figuras) {
            if(f.coordenadaDentroDeFigura(e.getX(), e.getY()))
                f.setSeleccionado();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(Figura f: figuras) {
            f.deseleccionar();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for(Figura f: figuras) {
            if(f.isSeleccionada()) {
                f.setPosicion(e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
