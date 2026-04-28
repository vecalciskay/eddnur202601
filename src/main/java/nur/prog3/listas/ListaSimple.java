package nur.prog3.listas;

public class ListaSimple {
    private NodoSimple primero;

    public ListaSimple() {
        primero = null;
    }

    @Override
    public String toString() {
        if (primero == null) {
            return "{VACIA}";
        }
        StringBuilder resultado = new StringBuilder();
        NodoSimple actual = primero;
        while(actual != null) {
            resultado.append(actual.toString());
            actual = actual.getEnlace();
        }

        return resultado.toString();
    }

    public void insertar(Object o) {
        NodoSimple nuevo = new NodoSimple(o);
        nuevo.setEnlace(primero);
        primero = nuevo;
    }
}
