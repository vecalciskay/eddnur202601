package nur.prog3.hanoi.objects;

public class JuegoHanoi {
    private Torre[] torres;

    public JuegoHanoi(int numeroAnillos) {
        torres = new Torre[3];
        torres[0] = new Torre();
        torres[1] = new Torre();
        torres[2] = new Torre();
    }
}
