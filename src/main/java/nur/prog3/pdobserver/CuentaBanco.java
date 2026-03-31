package nur.prog3.pdobserver;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CuentaBanco {
    private double saldo;
    private PropertyChangeSupport observado;
    public CuentaBanco() {
        saldo = 0.0;
        observado = new PropertyChangeSupport(this);
    }

    public void suscribirse(PropertyChangeListener observador) {
        observado.addPropertyChangeListener(observador);
    }

    public void depositar(double monto) {
        double oldSaldo = saldo;
        saldo += monto;
        double nuevoSaldo = saldo;

        // aqui paso el evento, toca notificar
        observado.firePropertyChange("DEPOSITO",
                oldSaldo, nuevoSaldo);
    }

    public void depositarInterno(double monto) {
        saldo += monto;
    }

    public void retirar(double monto) {
        saldo -= monto;
    }

    public String toString() {
        return "El saldo de la cuenta es " + saldo;
    }
}
