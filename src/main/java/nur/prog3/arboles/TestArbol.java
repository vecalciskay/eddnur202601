package nur.prog3.arboles;

public class TestArbol {
    static void main() {
        Arbol<String> a = new Arbol<>();       a.insertar("", "D");

        a.insertar("D", "N");
        a.insertar("D", "E");
        a.insertar("D", "W");

        a.insertar("N","C");
        a.insertar("N","A");

        a.insertar("W","T");
        a.insertar("W","U");


        System.out.println(a);

        String resultado = a.buscar("C");
        System.out.println(resultado);

        a.recorrerBfs();
        a.recorrerDfs();
    }
}
