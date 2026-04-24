package nur.prog3.mandel.objects;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Imagen  {
    private int ancho;
    private int alto;
    private int[][] pixeles;
    private PropertyChangeSupport observado;

    public Imagen(int w, int h) {
        ancho = w;
        alto = h;
        pixeles = new int[ancho][alto];
        observado = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener listener) {
        observado.addPropertyChangeListener(listener);
    }

    public int[] getRgb(int x, int y) {
        int px = pixeles[x][y];
        // Obtenemos los ultimos 8 bits
        int b = px & 0x000000FF;
        // Obtenemos los 8 bits de G y los movemos a la derecha
        int g = px & 0x0000FF00 >> 8;

        int r = px & 0x00FF0000 >> 16;

        int[] rgb = new int[3];
        rgb[0] = r;
        rgb[1] = g;
        rgb[2] = b;

        return rgb;
    }

    public void lineaHorizontal(int color, int altura,
                                int anchoDeLaLinea) {
        for (int i = 0; i < ancho; i++) {

            for (int j = altura; j < (altura + anchoDeLaLinea); j++) {
                pixeles[i][j] = color;
            }
        }
        observado.firePropertyChange("IMAGEN", true, false);
    }

    public void setRgb(int x, int y, int r, int g, int b) {
        int c = b;
        c = c | (g << 8);
        c = c | (r << 16);

        pixeles[x][y] = c;
    }

    public void dibujar(Graphics g) {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(pixeles[i][j]);
                g.setColor(c);
                g.drawLine(i,j,i,j);
            }
        }
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int[][] getPixeles() {
        return pixeles;
    }

    public void setPixeles(int[][] pixeles) {
        this.pixeles = pixeles;
    }
}
