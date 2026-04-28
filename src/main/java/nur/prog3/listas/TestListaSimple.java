package nur.prog3.listas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestListaSimple {
    private static final Logger logger = LogManager.getRootLogger();
    public static void main(String[] args) {
        ListaSimple lista = new ListaSimple();
        logger.info(lista);

        lista.insertar("Hugo");
        lista.insertar("Paco");
        lista.insertar("Luis");
        logger.info(lista);
    }
}
