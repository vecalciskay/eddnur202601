package nur.prog3.listas;

public class Cola<E> extends Lista<E> {
    public Cola() {
        super();
    }

    public void push(E o) {
        this.adicionar(o);
    }

    public E pull() {
        E obj = primero.getContenido();
        eliminar(1);
        return obj;
    }
}
