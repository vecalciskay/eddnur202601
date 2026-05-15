package nur.prog3.bancored;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ComandoBanco {
    protected String regexPrincipal;
    protected String nombre;
    protected ProtocoloBanco protocolo;
    protected String primeraLinea;

    public void setPrimeraLinea(String linea) {
        primeraLinea = linea;
    }

    public String getRegexPrincipal() {
        return regexPrincipal;
    }

    public String getNombre() {
        return nombre;
    }

    public ProtocoloBanco getProtocolo() {
        return protocolo;
    }

    public abstract boolean atenderComandoSegunProtocolo() throws IOException;
    public boolean expresionValida(String expr, String regex, Object[] resultado) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expr);
        if (matcher.find())
            return true;
        return false;
    }

    public abstract void ejecutarComoCliente(Object[] argumentos) throws IOException;
}
