package nur.prog3.listas;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListaTest {
    @Test
    public void tamanoListaVacia() {
        // Arrange
        Lista<String> lista = new Lista<>();

        // Act
        int resultado = lista.tamano();

        // Assert
        int esperado = 0;
        assertEquals(esperado, resultado);
    }

}
