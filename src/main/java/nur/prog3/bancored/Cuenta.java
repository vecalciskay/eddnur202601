package nur.prog3.bancored;

public class Cuenta {
    private int ci;
    private double saldo;

    public Cuenta(int ci) {
        this.ci = ci;
        saldo = 0;
    }

    public int getCi() {
        return ci;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double v) {
        this.saldo += v;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "ci=" + ci +
                ", saldo=" + saldo +
                '}';
    }
}
