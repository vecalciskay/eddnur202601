package nur.prog3.mandel.objects;

public class MatrizMandel {
    private int[][] puntos;
    private int ancho;
    private int alto;
    private NumeroComplejo inferiorIzquierda;
    private NumeroComplejo superiorDerecha;

    public MatrizMandel(int w, int h) {
        puntos = new int[w][h];
        alto = h;
        ancho = w;
        inferiorIzquierda = new NumeroComplejo(-2,-2);
        superiorDerecha = new NumeroComplejo(2,2);
    }

    public int divergenteEn(NumeroComplejo z0,
                            int max) {
        // Testeamos si z0 nos lleva a una secuencia divergente
        int iter = 0;
        NumeroComplejo z = z0;
        boolean convergente = true;
        while (convergente && iter < max) {
            double valorAbsolutoZ = z.getAbs();
            if (valorAbsolutoZ >= 2.0)
            {
                convergente = false;
                break;
            }
            NumeroComplejo zn1 =
                    z.multiply(z).add(z0);
            z = zn1;
            iter++;
        }
        return iter;
    }

    public void hacerMandel() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                hacerMandel(i,j);
            }
        }
    }

    public void hacerMandel(int x, int y) {
        double v_re = (double)x * 4.0 / (double)ancho - 2.0;
        double v_im = - (double)y * 4.0 / (double)alto + 2.0;
        NumeroComplejo z0 = new NumeroComplejo(v_re,v_im);
        int color = divergenteEn(z0, 255);

        puntos[x][y] = color;
    }

    public Imagen getImagen() {
        Imagen img = new Imagen(ancho,alto);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int c = puntos[i][j];
                img.setRgb(i,j,c,c,c);
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
}
