package nur.prog3.imagenes.objetos;

import java.awt.*;
import java.beans.PropertyChangeSupport;

public class Cuadrado extends Figura {
    private int tamano;

    public Cuadrado(int x, int y, int tamano, Color c) {
        this.x = x;
        this.y = y;
        this.color = c;
        this.tamano = tamano;
        this.observado = new PropertyChangeSupport(this);
        seleccionada = false;
    }

    @Override
    public boolean coordenadaDentroDeFigura(int px, int py) {
        if (px > this.x && px < (this.x + tamano) &&
            py > this.y && py < (this.y + tamano))
            return true;
        return false;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(this.color);
        g.fillRect(x, y, tamano, tamano);
    }
}
