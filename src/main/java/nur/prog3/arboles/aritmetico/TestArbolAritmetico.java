package nur.prog3.arboles.aritmetico;

import nur.prog3.arboles.Arbol;

public class TestArbolAritmetico {
    static void main() {
        ArbolAritmetico a = new ArbolAritmetico();

        String id = a.insertar("", new Operador(EnumOperacion.Suma));

        a.insertar(id, new Numero(2));
        a.insertar(id, new Numero(5));

        //ArbolAritmetico a = new ArbolAritmetico("((4 * 8) - (5/3))");

        System.out.println(a.toString() + " = " + a.evaluar());
    }
}
