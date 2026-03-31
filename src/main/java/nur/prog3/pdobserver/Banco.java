package nur.prog3.pdobserver;

import nur.prog3.pdobserver.comercial.UnBsCada100Depositados;

public class Banco {
    public static void main(String[] args) {
        CuentaBanco cta = new CuentaBanco();
        UnBsCada100Depositados campanaComercial =
                new UnBsCada100Depositados();
        cta.suscribirse(campanaComercial);

        cta.depositar(100);
        cta.retirar(50);
        cta.depositar(300);
        System.out.println(cta);
    }
}
