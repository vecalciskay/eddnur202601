package nur.prog3.arboles;

import javax.swing.*;
import java.awt.*;

public class ArbolPanel extends JPanel {
    private Arbol<String> modelo;

    public ArbolPanel(Arbol<String> modelo) {
        this.modelo = modelo;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600,600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        IDibujable dibujo = new DibujoArbol(modelo);
        dibujo.dibujar(g, 0, 0);
    }
}
