package nur.prog3.mandel.objects;

public class NumeroComplejo {
    private double valorReal;
    private double valorImaginario;

    public NumeroComplejo(double valorReal, double valorImaginario) {
        this.valorReal = valorReal;
        this.valorImaginario = valorImaginario;
    }

    public NumeroComplejo add(NumeroComplejo arg) {
        this.valorReal += arg.getValorReal();
        this.valorImaginario += arg.getValorImaginario();
        return this;
    }

    public NumeroComplejo multiply(NumeroComplejo arg) {
        valorReal = valorReal * arg.getValorReal() -
                valorImaginario * arg.getValorImaginario();
        valorImaginario = valorReal * arg.getValorImaginario() +
                valorImaginario * arg.getValorReal();
        //(a + bi)*(c+di) = ac + adi + bci - bd
        return this;
    }

    public double getAbs() {
        double sumaCatetosAlCuadrado =
                valorReal * valorReal +
                valorImaginario * valorImaginario;
        double hipotenusa =
                Math.sqrt(sumaCatetosAlCuadrado);

        return hipotenusa;
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
