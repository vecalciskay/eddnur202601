package nur.prog3.hanoi.objects;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class JuegoHanoi {
    private Torre[] torres;
    private int n;
    private PropertyChangeSupport observado;

    public JuegoHanoi(int numeroAnillos) {
        torres = new Torre[3];
        torres[0] = new Torre();
        torres[1] = new Torre();
        torres[2] = new Torre();
        n = numeroAnillos;
        observado = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener listener) {
        observado.addPropertyChangeListener(listener);
    }

    public void inicializar() {
        for (int i = n; i > 0; i--) {
            Anillo a = new Anillo(i);
            torres[0].meter(a);
        }
    }

    public void resolver() {
        hacerHanoi(0,2,1,n);
    }

    public void hacerHanoi(int de, int a, int pp, int n) {
        if(n == 1) {
            Anillo anillo = torres[de].sacar();
            torres[a].meter(anillo);
            observado.firePropertyChange("HANOI",
                    true, false);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                ;
            }
            return;
        }
        hacerHanoi(de,pp,a,n-1);
        hacerHanoi(de,a,pp,1);
        hacerHanoi(pp,a,de,n-1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(torres[0]).append("\n");
        sb.append(torres[1]).append("\n");
        sb.append(torres[2]).append("\n");
        return sb.toString();
    }

    public Torre getTorre(int numTorre) {
        return torres[numTorre];
    }
}
