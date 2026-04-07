package nur.prog3.hanoi.vista;

import nur.prog3.hanoi.objects.Anillo;
import nur.prog3.hanoi.objects.JuegoHanoi;

import java.awt.*;

public class DibujadorHanoi {
    private final JuegoHanoi modelo;

    public DibujadorHanoi(JuegoHanoi modelo) {
        this.modelo = modelo;
    }

    public void dibujar(Graphics g) {
        dibujarTorre(g,0, 100);
        dibujarTorre(g,1, 200);
        dibujarTorre(g,2, 300);
    }

    private void dibujarTorre(Graphics g, int numTorre, int x) {
        g.setColor(Color.black);
        g.fillRect(x,50,10,300);

        g.setColor(Color.blue);
        int yAnillo = 300;
        for(Anillo a : modelo.getTorre(numTorre).getAnillos()) {
            int tamano = a.getTamano() * 30;
            g.fillRect(x - tamano/2, yAnillo, tamano, 20);
            yAnillo -= 50;
        }
    }
}
