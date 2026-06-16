package nur.prog3.arboles;

import nur.prog3.listas.Lista;

public class ArbolString extends Arbol<String> {

    private static final String REGEX_Nodo = "[A-Z]";
    public ArbolString() {
        super();
    }

    public ArbolString(String expr) {
        raiz = leerExpresion(expr);
    }


    /**
     * Expresion puede ser:
     * <ul>
     *     <li>T</li>
     *     <li>T-(A B)</li>
     *     <li>T-(A B-(R N P Q) C-(X Z-(V S D)))</li>
     * </ul>
     */
    public Nodo<String> leerExpresion(String exprce) {
        String expr = exprce.trim();
        Nodo<String> nodo = null;
        char c;
        int nbParentesis = 0;
        boolean leeHijos = false;
        int comienzaHijo = -1;
        for(int i=0; i<expr.length(); i++) {
            c = expr.charAt(i);
            String charC = String.valueOf(c);
            if (comienzaHijo < 0 && charC.matches(REGEX_Nodo)) {
                nodo = new Nodo<String>(charC);
                continue;
            }
            if (c == '-') {
                leeHijos = true;
                continue;
            }
            if (c == '(' && leeHijos && nbParentesis == 0) {
                // Leemos todos los hijos
                Lista<String> hijos = leerHijosDeExpresion(expr.substring(i+1, expr.length() - 1));
                for(String exprHijo : hijos) {
                    Nodo<String> hijo = leerExpresion(exprHijo);
                    nodo.getHijos().insertar(hijo);
                }
                break;
            }
        }
        return nodo;
    }

    private Lista<String> leerHijosDeExpresion(String exprce) {
        String expr = exprce.trim();
        Lista<String> resultado = new Lista<>();
        int comienzaHijo = 0;
        int i = -1;
        char c;
        int nbParentesis = 0;
        String exprHijo;
        while(i<(expr.length() - 1)) {
            i++;
            c = expr.charAt(i);
            if (c == ' ' && nbParentesis == 0) {
                exprHijo = expr.substring(comienzaHijo, i);
                resultado.insertar(exprHijo);
                comienzaHijo = i;
                continue;
            }
            if (c == '(') {
                nbParentesis++;
                continue;
            }
            if (c == ')') {
                nbParentesis--;
            }
        }
        exprHijo = expr.substring(comienzaHijo);
        resultado.insertar(exprHijo);

        return resultado;
    }

}
