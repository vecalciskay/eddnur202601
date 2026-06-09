package nur.prog3.listas;

public class Pila<E> extends Lista<E> {

    public Pila() {
        super();
    }

    public void push(E o) {
        this.insertar(o);
    }

    public E pop() {
        E obj = primero.getContenido();
        eliminar(1);
        return obj;
    }
}
