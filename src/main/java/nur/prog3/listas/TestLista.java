package nur.prog3.listas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public class TestLista {
    private static final Logger logger = LogManager.getRootLogger();
    public static void main(String[] args) {
        Lista<String> lista = new Lista<>();
        logger.info(lista);

        lista.insertar("Hugo");
        lista.insertar("Paco");
        lista.insertar("Luis");
        logger.info(lista);

        logger.info("Esta lista tiene " + lista.tamano() + " elementos");

        Iterator<String> iterador = lista.iterator();
        while(iterador.hasNext()) {
            String elemento = iterador.next();
            logger.info("Elemento: " + elemento);
        }

        logger.info("Ahora con un for");

        for(String s:lista) {
            logger.info("Elemento desde for:" + s);
        }

        lista.eliminar(2);
        logger.info(lista);

        ListaOrdenada<String> l = new ListaOrdenada<>();
        logger.info(l);

        l.insertar("Hugo");
        l.insertar("Paco");
        l.insertar("Luis");
        logger.info(l);
    }
}
