package nur.prog3.recursividad;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Simple {
    private static Logger _logger = LogManager.getRootLogger();
    public static void main() {
        Simple obj = new Simple();
        int suma = obj.calcularSuma(10);
        _logger.warn("Termina todo, la suma es: " + suma);
    }

    /**
     * calcuarSUma(5)
     * 5 + calcularSuma(4)
     * 5 + 4 + calcularSuma(3)
     * @return
     */
    public int calcularSuma(int n) {
        if (n == 0) {
            return 0;
        }
        int suma = n + calcularSuma(n-1);
        return suma;
    }
}
