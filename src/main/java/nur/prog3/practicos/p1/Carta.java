package nur.prog3.practicos.p1;

public class Carta {

    private final String palo;
    private final String valor;
    private final int posicionOriginal;

    public Carta(String palo, String valor, int posicionOriginal) {
        this.palo = palo;
        this.valor = valor;
        this.posicionOriginal = posicionOriginal;
    }

    public String getPalo() {
        return palo;
    }

    public String getValor() {
        return valor;
    }

    public int getPosicionOriginal() {
        return posicionOriginal;
    }

    @Override
    public String toString() {
        return valor + " de " + palo;
    }
}
