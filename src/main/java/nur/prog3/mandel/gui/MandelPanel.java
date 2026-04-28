package nur.prog3.mandel.gui;

import nur.prog3.mandel.objects.Imagen;
import nur.prog3.mandel.objects.MatrizMandel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MandelPanel extends JPanel
    implements MouseListener, MouseMotionListener,
        PropertyChangeListener {
    private final MatrizMandel modelo;
    private final Logger logger = LogManager.getRootLogger();

    public MandelPanel(MatrizMandel modelo) {
        this.modelo = modelo;
        this.modelo.addObserver(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Imagen img = modelo.getImagen();
        img.dibujar(g);
        // Si hay cuadrado, dibujarlo
        if (modelo.getZoomX() >= 0) {
            g.setColor(Color.yellow);
            g.drawRect(modelo.getZoomX(), modelo.getZoomY(),
                    50, 50);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(modelo.getAncho(), modelo.getAlto());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.modelo.nuevasCoordenadas();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        logger.info("Sale edl panel");
        modelo.setZoomX(-1);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        logger.info("Se movio a la posicion: " + x + "," + y);
        this.modelo.setZoomY(y);
        this.modelo.setZoomX(x);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
