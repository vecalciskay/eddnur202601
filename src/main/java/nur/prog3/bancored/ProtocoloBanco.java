package nur.prog3.bancored;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * El protocolo tiene los siguientes comandos:
 *
 * Saludar al servidor para que sepa cual cuenta trabajar
 * >>> HOLA [ci]
 * <<< OK
 *
 * Depositar un monto en esa cuenta
 * >>> DEPOSITAR [double]
 * <<< OK - SALDO [xxx]
 *
 * Fin de conversacion
 * >>> FIN
 * <<< OK
 */
public class ProtocoloBanco implements Runnable {
    private static final Logger logger = LogManager.getRootLogger();
    private Socket cliente;
    private PrintWriter salida;
    private BufferedReader entrada;
    private BancoServidor banco;
    private Cuenta cuenta;

    private ProtocoloBanco(Socket clt) throws IOException {
        cliente = clt;
        salida = new PrintWriter(cliente.getOutputStream());
        entrada = new BufferedReader(
                new InputStreamReader(cliente.getInputStream())
        );
    }

    public static ProtocoloBanco crearParaServer(Socket clt, BancoServidor bco)
            throws IOException {
        ProtocoloBanco protocolo =  new ProtocoloBanco(clt);
        protocolo.setBanco(bco);
        return protocolo;
    }

    public void setBanco(BancoServidor bco) {
        this.banco = bco;
    }

    public static ProtocoloBanco crearNuevo() throws IOException {
        InetAddress addr = InetAddress.getLocalHost();

        Socket clt = new Socket(addr, ConfigRed.PUERTO);
        ProtocoloBanco obj = new ProtocoloBanco(clt);

        return obj;
    }

    @Override
    public void run() {
        ComandoBanco cmd = null;
        try {
            cmd = comandoRecibido(null);
            if (!(cmd instanceof ComandoHola)) {
                logger.error("Error de protocolo debe comenzar con Hola");
                salida.println("ERROR");
                salida.flush();
                cerrarConexion();
            }
            cmd.atenderComandoSegunProtocolo();
            ComandoHola cmdHola = (ComandoHola) cmd;
            cuenta = getCuentaCliente(cmdHola.getCi());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            boolean sesionTerminada = false;
            while(!sesionTerminada) {
                cmd = comandoRecibido(cuenta);
                if (cmd == null) {
                    logger.error("No conoce ese comando");
                    salida.println("ERROR");
                    salida.flush();
                    logger.info(">>> ERROR");
                    continue;
                }
                logger.info("Ahora pasa la mano al comando para que ejecute");
                sesionTerminada = cmd.atenderComandoSegunProtocolo();
            }

            logger.info("Cierra conexion");
            cerrarConexion();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Cuenta getCuentaCliente(int ci) {
        for(Cuenta clt : banco.getCuentas()) {
            if (clt.getCi() == ci) {
                return clt;
            }
        }
        if (ci > 0) {
            Cuenta nueva = new Cuenta(ci);
            banco.addCuenta(nueva);
            return nueva;
        }
        return null;
    }

    private ComandoBanco comandoRecibido(Cuenta clt) throws IOException {
        String linea = entrada.readLine();
        logger.info("<<< " + linea);

        ComandoBanco cmd = null;
        for(TipoComando tipoComando : TipoComando.values()) {
            String regexCmd = ConfigRed.expresiones.get(tipoComando);
            Pattern pattern = Pattern.compile(regexCmd);
            Matcher matcher = pattern.matcher(linea);

            if (matcher.find()) {
                cmd = comandoPorTipoComando(tipoComando, clt);
                logger.info("El comando que se hara cargo es: " + cmd.getNombre());
                cmd.setPrimeraLinea(linea);
                return cmd;
            }
        }
        return null;
    }

    private ComandoBanco comandoPorTipoComando(TipoComando tipoComando, Cuenta clt) {
        ComandoBanco cmd;
        cmd = switch(tipoComando) {
            case TipoComando.Hola -> new ComandoHola(this);
            case TipoComando.Fin -> new ComandoFin(this);
            case TipoComando.Deposito -> new ComandoDeposito(this, clt);
            case TipoComando.Saldo -> new ComandoSaldo(this, clt);
        };

        return cmd;
    }

    public PrintWriter getSalida() {
        return salida;
    }

    public BufferedReader getEntrada() {
        return entrada;
    }

    private void cerrarConexion() throws IOException {
        cliente.close();
        logger.info("Se cierra la conexion con el cliente");
    }

    public void hola(int ci) throws IOException {
        ComandoBanco cmd = new ComandoHola(this);
        cmd.ejecutarComoCliente(new Integer[] { ci });
    }

    public void fin() throws IOException {
        ComandoBanco cmd = new ComandoFin(this);
        cmd.ejecutarComoCliente(new Object[1]);
        cerrarConexion();
    }
    public void depositar(double monto) throws IOException {
        ComandoBanco cmd = new ComandoDeposito(this, 10);
        cmd.ejecutarComoCliente(new Double[] {monto});
    }
}
