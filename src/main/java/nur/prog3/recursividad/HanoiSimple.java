package nur.prog3.recursividad;

public class HanoiSimple {
    private int numeroAnillos;
    public HanoiSimple(int n) {
        this.numeroAnillos = n;
    }

    public void h(int de, int a, int pp, int n) {
        if(n == 1) {
            System.out.println("De " + de  + " a " + a);
            return;
        }
        h(de,pp,a,n-1);
        h(de,a,pp,1);
        h(pp,a,de,n-1);
    }

    public void resolver() {
        h(1,3,2,numeroAnillos);
    }
}
