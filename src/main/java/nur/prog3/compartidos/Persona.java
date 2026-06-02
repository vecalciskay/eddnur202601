package nur.prog3.compartidos;

public class Persona {
    private long ci;
    private String nombre;
    private int pesokg;

    public Persona(long ci, String nombre, int pesokg) {
        this.ci = ci;
        this.nombre = nombre;
        this.pesokg = pesokg;
    }

    public long getCi() {
        return ci;
    }

    public void setCi(long ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPesokg() {
        return pesokg;
    }

    public void setPesokg(int pesokg) {
        this.pesokg = pesokg;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "ci=" + ci +
                ", nombre='" + nombre + '\'' +
                ", pesokg=" + pesokg +
                '}';
    }
}
