package nur.prog3.arboles;

import java.awt.*;

/**
 * Esta hecha solamente para dibujar un arbol
 */
public class DibujoArbol implements IDibujable {
    private static final int TAMANO_NODO = 60;
    private static final int ESPACIO_HORIZONTAL = 30;
    private static final int ESPACIO_VERTICAL = 40;
    private Arbol<String> modelo;
    public DibujoArbol(Arbol<String> modelo) {
        this.modelo = modelo;
    }
    @Override
    public void dibujar(Graphics g, int x, int y) {

        Arbol<String>.Nodo<String> raiz = modelo.getRaiz();
        dibujarSubArbol(g, raiz, x, y);
    }

    private void dibujarSubArbol(Graphics g, Arbol<String>.Nodo<String> nodo, int x, int y) {
        if (nodo.getHijos().tamano() == 0) {

            dibujarNodoSuelto(g, nodo.getContenido(), x, y);
            return;
        }

        int ancho = calcularAncho(nodo);
        int xNodo = x + ancho / 2 - TAMANO_NODO / 2;
        int xHijo = x;
        int yHijo = y + TAMANO_NODO + ESPACIO_VERTICAL;
        for(Arbol<String>.Nodo<String> hijo : nodo.getHijos()) {

            int anchoHijo = calcularAncho(hijo);
            // linea
            g.setColor(Color.black);
            g.drawLine(xNodo + TAMANO_NODO / 2, y + TAMANO_NODO / 2,
                    xHijo + anchoHijo / 2, yHijo + TAMANO_NODO / 2);

            dibujarSubArbol(g, hijo, xHijo, yHijo);
            xHijo = xHijo + ESPACIO_HORIZONTAL + anchoHijo;
        }


        dibujarNodoSuelto(g,nodo.getContenido(),xNodo,y);
    }

    private void dibujarNodoSuelto(Graphics g, String contenido, int x, int y) {
        g.setColor(Color.white);
        g.fillArc(x, y, TAMANO_NODO, TAMANO_NODO, 0, 360);

        g.setColor(Color.black);
        g.drawArc(x, y, TAMANO_NODO, TAMANO_NODO, 0, 360);
        Font myFont = new Font("Arial", Font.PLAIN, 20);
        g.setFont(myFont);
        g.drawString(contenido, x + TAMANO_NODO / 2 - 5, y + TAMANO_NODO / 2 + 5);
    }

    private int calcularAncho(Arbol<String>.Nodo<String> nodo) {
        if(nodo.getHijos().tamano() == 0) {
            return TAMANO_NODO;
        }
        int resultado = 0;
        int separacionHorizontal = 0;
        for(Arbol<String>.Nodo<String> hijo : nodo.getHijos()) {
            resultado = resultado + separacionHorizontal + calcularAncho(hijo);
            separacionHorizontal = ESPACIO_HORIZONTAL;
        }
        return resultado;
    }
}
