package nur.prog3.excepciones;

public class ManejoExcepcion {

    public static void main(String[] args) throws IllegalArgumentException {
        ManejoExcepcion obj = new ManejoExcepcion();

        /*
        try {
            obj.m(-6);
        } catch(IllegalArgumentException e) {
            System.out.println("Error, pero todo bien");

                                                     }*/
        obj.m(-6);
    }

    /**
     *
     * @param a
     * @throws IllegalArgumentException
     */
    public void m(int a) throws IllegalArgumentException {
        if (a < 0)
            throw new IllegalArgumentException("No negativos");
    }
}


