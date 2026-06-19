package nur.prog3.practicos.p1;

public class ResultadoMezcla {

    private final int distanciaMinima;
    private final int distanciaMaxima;
    private final double distanciaPromedio;
    private final int cartasMovidas;
    private final int cartasSinMover;

    public ResultadoMezcla(
            int distanciaMinima,
            int distanciaMaxima,
            double distanciaPromedio,
            int cartasMovidas,
            int cartasSinMover
    ) {
        this.distanciaMinima = distanciaMinima;
        this.distanciaMaxima = distanciaMaxima;
        this.distanciaPromedio = distanciaPromedio;
        this.cartasMovidas = cartasMovidas;
        this.cartasSinMover = cartasSinMover;
    }

    public int getDistanciaMinima() {
        return distanciaMinima;
    }

    public int getDistanciaMaxima() {
        return distanciaMaxima;
    }

    public double getDistanciaPromedio() {
        return distanciaPromedio;
    }

    public int getCartasMovidas() {
        return cartasMovidas;
    }

    public int getCartasSinMover() {
        return cartasSinMover;
    }

    public double getPorcentajeMovidas(int totalCartas) {
        return cartasMovidas * 100.0 / totalCartas;
    }
}
