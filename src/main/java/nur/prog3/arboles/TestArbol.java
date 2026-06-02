package nur.prog3.arboles;

public class TestArbol {
    static void main() {
        Arbol<String> a = new Arbol<>();       a.insertar(null, "D");

        a.insertar("D", "N");
        a.insertar("D", "E");
        a.insertar("D", "W");

        System.out.println(a);
    }
}
