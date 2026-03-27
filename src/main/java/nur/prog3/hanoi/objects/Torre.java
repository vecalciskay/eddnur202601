package nur.prog3.hanoi.objects;

import java.util.Stack;

public class Torre {
    private Stack<Anillo> anillos;

    public Torre() {
        this.anillos = new Stack<>();
    }

    public Stack<Anillo> getAnillos() {
        return anillos;
    }

    public void setAnillos(Stack<Anillo> anillos) {
        this.anillos = anillos;
    }

    public void meter(Anillo a) {
        anillos.push(a);
    }

    public Anillo sacar() {
        return anillos.pop();
    }
}
