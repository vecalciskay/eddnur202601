package nur.prog3.redes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSimple {
    public static void main(String[] args) {
        int puerto = 7676;
        try {
            ServerSocket srv = new ServerSocket(puerto);
            System.out.println("Creado el servidor en puerto " + puerto);

            System.out.println("Esperando....");
            Socket clt = srv.accept();
            System.out.println("Cliente conectado!!");

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(clt.getInputStream()));
            PrintWriter writer = new PrintWriter(clt.getOutputStream());

            String line = reader.readLine();
            System.out.println("<<< " + line);
            while(!line.isEmpty()) {
                line = reader.readLine();
                System.out.println("<<< " + line);
            }

            clt.close();
            srv.close();
        } catch(Exception err) {
            System.out.println(err);
        }
    }
}
