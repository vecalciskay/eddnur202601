package nur.prog3.mandel.objects;

public class NumeroComplejo {
    private final double valorReal;
    private final double valorImaginario;

    public NumeroComplejo(double valorReal, double valorImaginario) {
        this.valorReal = valorReal;
        this.valorImaginario = valorImaginario;
    }

    public NumeroComplejo add(NumeroComplejo arg) {
        return new NumeroComplejo(
                this.valorReal + arg.getValorReal(),
                this.valorImaginario + arg.getValorImaginario()
        );
    }

    public NumeroComplejo multiply(NumeroComplejo arg) {
        double nuevoReal = valorReal * arg.getValorReal() -
                valorImaginario * arg.getValorImaginario();
        double nuevoImaginario = valorReal * arg.getValorImaginario() +
                valorImaginario * arg.getValorReal();
        return new NumeroComplejo(nuevoReal, nuevoImaginario);
    }

    public double abs() {
        double sumaCatetosAlCuadrado =
                valorReal * valorReal +
                valorImaginario * valorImaginario;

        return Math.sqrt(sumaCatetosAlCuadrado);
    }

    public double getValorReal() {
        return valorReal;
    }

    public double getValorImaginario() {
        return valorImaginario;
    }

    @Override
    public String toString() {
        return "(" + valorReal + "," +
                valorImaginario + ")";
    }
}
