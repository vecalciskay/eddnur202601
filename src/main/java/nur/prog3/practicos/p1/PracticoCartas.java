package nur.prog3.practicos.p1;

import java.util.Random;

public class PracticoCartas {

    private static final String[] PALOS = {"Corazones", "Diamantes", "Treboles", "Picas"};
    private static final String[] VALORES = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private static final int TOTAL_CARTAS = 52;

    public static void main(String[] args) {
        Carta[] baraja = crearBarajaOrdenada();

        System.out.println("Baraja original");
        mostrarBaraja(baraja, false);

        mezclar(baraja);

        System.out.println();
        System.out.println("Baraja mezclada");
        mostrarBaraja(baraja, true);

        ResultadoMezcla resultado = calcularResultado(baraja);

        System.out.println();
        mostrarResultado(resultado);
        mostrarConclusion(resultado);
    }

    public static Carta[] crearBarajaOrdenada() {
        Carta[] baraja = new Carta[TOTAL_CARTAS];
        int posicion = 0;

        for (String palo : PALOS) {
            for (String valor : VALORES) {
                baraja[posicion] = new Carta(palo, valor, posicion);
                posicion++;
            }
        }

        return baraja;
    }

    public static void mezclar(Carta[] baraja) {
        Random random = new Random();

        for (int i = baraja.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            Carta temporal = baraja[i];
            baraja[i] = baraja[j];
            baraja[j] = temporal;
        }
    }

    public static ResultadoMezcla calcularResultado(Carta[] baraja) {
        int distanciaMinima = Integer.MAX_VALUE;
        int distanciaMaxima = 0;
        int sumaDistancias = 0;
        int cartasMovidas = 0;
        int cartasSinMover = 0;

        for (int posicionActual = 0; posicionActual < baraja.length; posicionActual++) {
            int distancia = calcularDistancia(posicionActual, baraja[posicionActual]);

            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
            }

            if (distancia > distanciaMaxima) {
                distanciaMaxima = distancia;
            }

            sumaDistancias += distancia;

            if (distancia == 0) {
                cartasSinMover++;
            } else {
                cartasMovidas++;
            }
        }

        double distanciaPromedio = (double) sumaDistancias / baraja.length;

        return new ResultadoMezcla(
                distanciaMinima,
                distanciaMaxima,
                distanciaPromedio,
                cartasMovidas,
                cartasSinMover
        );
    }

    private static void mostrarBaraja(Carta[] baraja, boolean incluirDistancia) {
        for (int posicionActual = 0; posicionActual < baraja.length; posicionActual++) {
            Carta carta = baraja[posicionActual];
            String linea = posicionActual
                    + " - "
                    + carta
                    + " - original: "
                    + carta.getPosicionOriginal();

            if (incluirDistancia) {
                linea += " - distancia: " + calcularDistancia(posicionActual, carta);
            }

            System.out.println(linea);
        }
    }

    private static int calcularDistancia(int posicionActual, Carta carta) {
        return Math.abs(posicionActual - carta.getPosicionOriginal());
    }

    private static void mostrarResultado(ResultadoMezcla resultado) {
        System.out.println("Metricas");
        System.out.println("Distancia minima: " + resultado.getDistanciaMinima());
        System.out.println("Distancia maxima: " + resultado.getDistanciaMaxima());
        System.out.printf("Distancia promedio: %.2f%n", resultado.getDistanciaPromedio());
        System.out.println("Cartas movidas: " + resultado.getCartasMovidas());
        System.out.println("Cartas sin mover: " + resultado.getCartasSinMover());
        System.out.printf("Porcentaje de cartas movidas: %.2f%%%n", resultado.getPorcentajeMovidas(TOTAL_CARTAS));
    }

    private static void mostrarConclusion(ResultadoMezcla resultado) {
        System.out.println();
        System.out.println("Conclusion:");

        if (resultado.getCartasMovidas() >= 45 && resultado.getDistanciaPromedio() >= 12) {
            System.out.printf(
                    "La baraja quedo bien mezclada porque %d de %d cartas cambiaron de posicion y la distancia promedio fue %.2f.%n",
                    resultado.getCartasMovidas(),
                    TOTAL_CARTAS,
                    resultado.getDistanciaPromedio()
            );
        } else if (resultado.getCartasMovidas() >= 35) {
            System.out.printf(
                    "La baraja quedo mezclada de forma aceptable porque %d de %d cartas cambiaron de posicion, aunque la distancia promedio fue %.2f.%n",
                    resultado.getCartasMovidas(),
                    TOTAL_CARTAS,
                    resultado.getDistanciaPromedio()
            );
        } else {
            System.out.printf(
                    "La mezcla fue debil porque solo %d de %d cartas cambiaron de posicion y la distancia promedio fue %.2f.%n",
                    resultado.getCartasMovidas(),
                    TOTAL_CARTAS,
                    resultado.getDistanciaPromedio()
            );
        }

        System.out.printf(
                "La distancia maxima fue %d y quedaron %d %s en su posicion original.%n",
                resultado.getDistanciaMaxima(),
                resultado.getCartasSinMover(),
                textoCartas(resultado.getCartasSinMover())
        );
    }

    private static String textoCartas(int cantidad) {
        return cantidad == 1 ? "carta" : "cartas";
    }
}
