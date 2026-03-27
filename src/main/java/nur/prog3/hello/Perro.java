package nur.prog3.hello;

public class Perro {
    private String tamano;
    protected int edad;

    public Perro(String tamano, int edad) {
        this.tamano = tamano;
        this.edad = edad;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
