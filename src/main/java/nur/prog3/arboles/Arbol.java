package nur.prog3.arboles;

import nur.prog3.listas.Lista;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Arbol<E> {
    private Nodo<E> raiz;
    private HashMap<String, Nodo<E>>  nodos;

    public Arbol() {
        raiz = null;
        nodos = new LinkedHashMap<>();
    }

    public void insertar(String padre, E hijo) {
        if (padre == null) {
            raiz = new Nodo(hijo);
            nodos.put(hijo.toString(), raiz);
            return;
        }
        Nodo<E> nodoPadre = nodos.get(padre);
        Nodo<E> nodoHijo = new Nodo<>(hijo);
        nodoPadre.insertarHijo(nodoHijo);
        nodos.put(hijo.toString(), nodoHijo);
    }

    public Nodo<E> getRaiz() {
        return raiz;
    }

    @Override
    public String toString() {
        if (raiz == null) {
            return "[VACIO]";
        }
        return raiz.toString();
    }

    public void setRaiz(Nodo<E> raiz) {
        this.raiz = raiz;
    }

    class Nodo<E> {
        private E contenido;
        private Lista<Nodo<E>> hijos;

        public Nodo(E contenido) {
            this.contenido = contenido;
            this.hijos = new Lista<>();
        }

        public E getContenido() {
            return contenido;
        }

        public Lista<Nodo<E>> getHijos() {
            return hijos;
        }

        @Override
        public String toString() {
            if(hijos.tamano() == 0)
                return contenido.toString();
            StringBuilder resultado = new StringBuilder(contenido.toString());
            resultado.append("-(");
            String separador = "";
            for(Nodo<E> hijo : hijos) {
                resultado.append(separador);
                resultado.append(hijo.toString());
                separador = " ";
            }
            resultado.append(")");
            return resultado.toString();
        }

        public void insertarHijo(Nodo<E> hijo) {

            hijos.insertar(hijo);
        }
    }
}
