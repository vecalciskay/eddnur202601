package nur.prog3.bancored;

public class ConfigRed {
    public static final int PUERTO = 6868;

    public static final String CMD_FIN = "FIN";
    public static final String CMD_DEPOSITO = "DEPOSITO\\s+([0-9]+|[0-9]+\\.[0-9]+)$";
    public static final String RESPUESTA_OK_SALDO = "^OK\\s+\\-\\s+([0-9]+|[0-9]+\\.[0-9]+)$";
    public static final String CMD_HOLA = "HOLA\\s+([0-9]+)$";
}
