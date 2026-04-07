package nur.prog3.hanoi.objects;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TestHanoi implements PropertyChangeListener {
    public static void main(String[] args) {
        JuegoHanoi j = new JuegoHanoi(3);
        TestHanoi vista = new TestHanoi();
        j.addObserver(vista);

        j.inicializar();
        System.out.println(j);

        j.resolver();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        JuegoHanoi j = (JuegoHanoi)evt.getSource();
        System.out.println(j);
    }
}
