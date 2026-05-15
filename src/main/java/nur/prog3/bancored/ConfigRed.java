package nur.prog3.bancored;

import java.util.HashMap;
import java.util.Map;

public class ConfigRed {
    public static final int PUERTO = 6868;

    public static final String CMD_FIN = "FIN";
    public static final String REGEX_DEPOSITO = "DEPOSITO\\s+([0-9]+|[0-9]+\\.[0-9]+)$";
    public static final String RESPUESTA_OK_SALDO = "^OK\\s+\\-\\s+([0-9]+|[0-9]+\\.[0-9]+)$";
    public static final String REGEX_HOLA = "HOLA\\s+([0-9]+)$";
    public static final String CMD_DEPOSITO = "DEPOSITO";
    public static final String CMD_SALDO = "SALDO";
    public static final Map<TipoComando, String> expresiones =
            new HashMap<>();

    static {
        expresiones.put(TipoComando.Hola, REGEX_HOLA);
        expresiones.put(TipoComando.Fin, CMD_FIN);
        expresiones.put(TipoComando.Deposito, REGEX_DEPOSITO);
        expresiones.put(TipoComando.Saldo, CMD_SALDO);

    }
}
