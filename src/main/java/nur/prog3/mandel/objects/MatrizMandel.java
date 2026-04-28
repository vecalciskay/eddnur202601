package nur.prog3.mandel.objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MatrizMandel {
    private final Logger logger = LogManager.getRootLogger();
    private int[][] puntos;
    private int ancho;
    private int alto;
    private double minReal;
    private double minImaginario;
    private double maxReal;
    private double maxImaginario;
    private NumeroComplejo inferiorIzquierda;
    private NumeroComplejo superiorDerecha;
    private int zoomX;
    private int zoomY;
    private PropertyChangeSupport observado;

    private static final int[] PALETTE = buildPalette();

    public MatrizMandel(int w, int h) {
        puntos = new int[w][h];
        alto = h;
        ancho = w;
        minImaginario = -1;
        minReal = -1.5;
        maxImaginario = 1;
        maxReal = 0.5;
        inferiorIzquierda = new NumeroComplejo(minReal,minImaginario);
        superiorDerecha = new NumeroComplejo(maxReal, maxImaginario);

        zoomX = -1;
        zoomY = -1;
        observado = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener listener) {
        observado.addPropertyChangeListener(listener);
    }

    public int divergenteEn(NumeroComplejo z0,
                            int max) {
        // Testeamos si z0 nos lleva a una secuencia divergente
        int color = 0;
        NumeroComplejo z = z0;
        double valorAbsolutoZ = z.abs();
        while (valorAbsolutoZ < 2 && color < max) {
            z = z.multiply(z).add(z0);
            valorAbsolutoZ = z.abs();
            color++;
        }
        return color;
    }

    public void hacerMandel() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                NumeroComplejo z = reglaDe3PlanoRealAComplejo(i,j);
                int color = divergenteEn(z, 255);
                puntos[i][j] = color;
            }
        }
    }

    public NumeroComplejo reglaDe3PlanoRealAComplejo(int x, int y) {
        double difMaxMinReal = maxReal - minReal;
        double difMaxMinImaginario = maxImaginario - minImaginario;
        double valorReal = x * (difMaxMinReal) / ancho + minReal;
        double valorImaginario = minImaginario + y * difMaxMinImaginario / alto;
        return new NumeroComplejo(valorReal, valorImaginario);
    }

    public Imagen getImagen() {
        Imagen img = new Imagen(ancho,alto);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int iteracion = puntos[i][j];


                int color = PALETTE[iteracion];
                int r = (color >> 16) & 0xFF;
                int g = (color >>  8) & 0xFF;
                int b =  color        & 0xFF;
                img.setRgb(i, j, r, g, b);

                //img.setRgb(i, j, iteracion, iteracion, iteracion);
            }
        }
        return img;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int getZoomX() {
        return zoomX;
    }

    public void setZoomX(int zoomX) {
        this.zoomX = zoomX;
        observado.firePropertyChange("MANDEL", true, false);
    }

    public int getZoomY() {
        return zoomY;
    }

    public void setZoomY(int zoomY) {
        this.zoomY = zoomY;
    }

    private static int[] buildPalette() {
        // Colores ancla: la paleta "Ultra Fractal" clásica
        // usada en Wikipedia y la mayoría de visualizadores de referencia
        int[][] anclas = {
                {  0,   7, 100},  // azul profundo
                { 32, 107, 203},  // azul medio
                {237, 255, 255},  // cian casi blanco
                {255, 170,   0},  // dorado
                {  0,   2,   0},  // negro (borde del conjunto)
        };

        int[] palette = new int[256];
        int numSegmentos = anclas.length - 1;
        int porSegmento = 256 / numSegmentos;

        for (int seg = 0; seg < numSegmentos; seg++) {
            int[] desde = anclas[seg];
            int[] hasta  = anclas[seg + 1];
            for (int i = 0; i < porSegmento; i++) {
                float t = (float) i / porSegmento;
                int r = (int) (desde[0] + t * (hasta[0] - desde[0]));
                int g = (int) (desde[1] + t * (hasta[1] - desde[1]));
                int b = (int) (desde[2] + t * (hasta[2] - desde[2]));
                palette[seg * porSegmento + i] = (r << 16) | (g << 8) | b;
            }
        }
        // Último slot = negro (interior del conjunto)
        palette[255] = 0x000000;
        return palette;
    }

    public void nuevasCoordenadas() {
        int nuevoMinX = zoomX;
        int nuevoMaxY = zoomY;
        int nuevoMaxX = zoomX + 50;
        int nuevoMinY = zoomY + 50;

        NumeroComplejo nuevoMinimo =
                reglaDe3PlanoRealAComplejo(nuevoMinX, nuevoMinY);
        NumeroComplejo nuevoMaximo =
                reglaDe3PlanoRealAComplejo(nuevoMaxX, nuevoMaxY);

        logger.info("[" + nuevoMinX + ", " + nuevoMinY + "] -> " +
                nuevoMinimo.toString());

        logger.info("[" + nuevoMaxX + ", " + nuevoMaxY + "] -> " +
                nuevoMaximo.toString());

        minReal = nuevoMinimo.getValorReal();
        minImaginario = nuevoMinimo.getValorImaginario();
        maxReal = nuevoMaximo.getValorReal();
        maxImaginario = nuevoMaximo.getValorImaginario();

        hacerMandel();

        observado.firePropertyChange("MANDEL", true, false);
    }
}
