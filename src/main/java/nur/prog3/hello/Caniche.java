package nur.prog3.hello;

public class Caniche extends Perro {
    public Caniche(String tamano, int edad) {
        super(tamano,edad);
    }
    public void comer() {
        System.out.println("El caniche de edd "+ edad + " come");
    }
}
