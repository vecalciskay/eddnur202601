package nur.prog3.imagenes.objetos;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Figura {
    protected double area;
    protected double perimetro;
    protected Color color;
    protected int x;
    protected int y;
    protected boolean seleccionada;
    protected PropertyChangeSupport observado;

    public void addObserver(PropertyChangeListener listener) {
        observado.addPropertyChangeListener(listener);
    }

    public void setPosicion(int nx, int ny) {
        x = nx;
        y = ny;
        observado.firePropertyChange("FIGURA", true, false);
    }

    public void setSeleccionado() {
        seleccionada = true;
        observado.firePropertyChange("FIGURA", true, false);
    }

    public void deseleccionar() {
        boolean anteriorEstado = seleccionada;
        seleccionada = false;
        if (anteriorEstado)
            observado.firePropertyChange("FIGURA", true, false);
    }

    public abstract boolean coordenadaDentroDeFigura(int x, int y);

    public abstract void dibujar(Graphics g);

    public boolean isSeleccionada() {
        return seleccionada;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPerimetro() {
        return perimetro;
    }

    public void setPerimetro(double perimetro) {
        this.perimetro = perimetro;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
