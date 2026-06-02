package nur.prog3.tablashash;

import nur.prog3.compartidos.Persona;

import java.util.HashMap;

public class TestTabla {
    static void main() {
        HashMap<Long, Persona> tabla = new HashMap<>();

        tabla.put((long)5767, new Persona(5767, "Hugo", 46));
        tabla.put((long)5932, new Persona(5932, "Paco", 46));

        Persona p = tabla.get((long)5767);
        System.out.println(p);
    }
}
