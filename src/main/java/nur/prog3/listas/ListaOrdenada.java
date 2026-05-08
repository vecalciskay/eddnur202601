package nur.prog3.listas;

public class ListaOrdenada<E> extends Lista<E> {
    @Override
    public void insertar(E o) {
        if (!(o instanceof Comparable)) {
            super.insertar(o);
            return;
        }
        if (tamano == 0) {
            super.insertar(o);
            return;
        }

        Comparable<E> objComparable = (Comparable<E>) o;
        if(objComparable.compareTo(primero.getContenido()) <= 0) {
            super.insertar(o);
            return;
        }

        Nodo<E> actual = primero;
        while(objComparable.compareTo(actual.getSiguiente().getContenido()) > 0) {
            actual = actual.getSiguiente();
        }
        Nodo<E> nuevo = new Nodo<E>(o);
        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);
        tamano++;
    }

}
