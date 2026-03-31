package nur.prog3.pdobserver.comercial;

import nur.prog3.pdobserver.CuentaBanco;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UnBsCada100Depositados implements PropertyChangeListener {
    public void ejecutar(CuentaBanco cta, double monto) {
        int campanaComercial = (int)monto / 100;
        if (campanaComercial > 0) {
            cta.depositarInterno(campanaComercial);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        double oldSaldo = (double) evt.getOldValue();
        double nuevoSaldo = (double)evt.getNewValue();
        CuentaBanco cta = (CuentaBanco) evt.getSource();

        double monto = nuevoSaldo - oldSaldo;
        ejecutar(cta, monto);
    }
}
