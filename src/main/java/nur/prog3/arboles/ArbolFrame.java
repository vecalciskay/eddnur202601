package nur.prog3.arboles;

import javax.swing.*;
import java.awt.*;

public class ArbolFrame extends JFrame {
    private Arbol<String> modelo;
    private ArbolPanel panel;

    public ArbolFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.getContentPane().setLayout(new BorderLayout());

        // hacer un arbol
        modelo = armarArbol();

        panel = new ArbolPanel(modelo);
        this.getContentPane().add(panel, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    private Arbol<String> armarArbol() {
        Arbol<String> a = new Arbol<>();
        a.insertar(null, "D");

        a.insertar("D", "N");
        a.insertar("D", "E");
        a.insertar("D", "W");

        a.insertar("N","C");
        a.insertar("N","A");

        a.insertar("W","T");
        a.insertar("W","U");

        //Arbol<String> a = new Arbol<>("D-(W-(U T) E N-(A C))");
        return a;
    }

    public static void main() {
        new ArbolFrame();
    }
}
