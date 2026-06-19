package nur.prog3.arboles;

import nur.prog3.listas.Cola;
import nur.prog3.listas.Lista;
import nur.prog3.listas.Pila;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Arbol<E> {
    protected Nodo<E> raiz;
    protected HashMap<String, Nodo<E>>  nodos;

    public Arbol() {
        raiz = null;
        nodos = new LinkedHashMap<>();
    }

    /**
     * Recorrer en anchura
     */
    public void recorrerBfs() {
        for(Nodo<E> nodoHijo : nodos.values()) {
            nodoHijo.resetVisita();
        }
        Cola<Nodo<E>> colaVisita = new Cola<>();
        colaVisita.push(raiz);
        while(colaVisita.tamano() > 0) {
            Nodo<E> nodo = colaVisita.pull();
            nodo.visitar();
            System.out.print(nodo.getContenido() + " - ");
            Lista<Nodo<E>> hijosNoVisitadosYNoEnCola = new Lista<>();
            for(Nodo<E> hijo : nodo.getHijos()) {
                if (hijo.getVisitado() > 0)
                    continue;
                boolean hijoEnCola = false;
                for(Nodo<E> enCola : colaVisita) {
                    if (hijo == enCola) {
                        hijoEnCola = true;
                        break;
                    }
                }
                if (hijoEnCola)
                    continue;

                hijosNoVisitadosYNoEnCola.insertar(hijo);
            }
            for(Nodo<E> nodosEncontrados : hijosNoVisitadosYNoEnCola) {
                colaVisita.push(nodosEncontrados);
            }
        }
        System.out.println();
    }

    /**
     * Recorrer en profundidad
     */
    public void recorrerDfs() {
        for(Nodo<E> nodoHijo : nodos.values()) {
            nodoHijo.resetVisita();
        }
        Pila<Nodo<E>> pilaVisita = new Pila<>();
        pilaVisita.push(raiz);
        while(pilaVisita.tamano() > 0) {
            Nodo<E> nodo = pilaVisita.pop();
            nodo.visitar();
            System.out.print(nodo.getContenido() + " - ");
            Lista<Nodo<E>> hijosNoVisitadosYNoEnPila = new Lista<>();
            for(Nodo<E> hijo : nodo.getHijos()) {
                if (hijo.getVisitado() > 0)
                    continue;
                boolean hijoEnPila = false;
                for(Nodo<E> enPila : pilaVisita) {
                    if (hijo == enPila) {
                        hijoEnPila = true;
                        break;
                    }
                }
                if (hijoEnPila)
                    continue;

                hijosNoVisitadosYNoEnPila.insertar(hijo);
            }
            for(Nodo<E> nodosEncontrados : hijosNoVisitadosYNoEnPila) {
                pilaVisita.push(nodosEncontrados);
            }
        }
        System.out.println();
    }

    public String insertar(String padre, E hijo) {
        if (padre != null && padre.isEmpty()) {
            raiz = new Nodo(hijo);
            if (hijo instanceof Identificable) {
                Identificable hijoId = (Identificable)hijo;
                nodos.put(hijoId.getId(), raiz);
                return hijoId.getId();
            } else {
                nodos.put(hijo.toString(), raiz);
                return hijo.toString();
            }
        }
        Nodo<E> nodoPadre = nodos.get(padre);
        Nodo<E> nodoHijo = new Nodo<>(hijo);
        nodoPadre.insertarHijo(nodoHijo);
        if (hijo instanceof Identificable) {
            Identificable hijoId = (Identificable)hijo;
            nodos.put(hijoId.getId(), nodoHijo);
            return hijoId.getId();
        } else {
            nodos.put(hijo.toString(), nodoHijo);
            return  hijo.toString();
        }
    }

    public Nodo<E> getRaiz() {
        return raiz;
    }

    public E buscar(String id) {
        if (raiz != null) {
            Nodo<E> resultado = raiz.buscar(id);
            if (resultado != null)
                return resultado.getContenido();
        }
        return null;
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

    public class Nodo<E> {
        private E contenido;
        private Lista<Nodo<E>> hijos;
        private int visitado;

        public Nodo(E contenido) {
            this.contenido = contenido;
            this.hijos = new Lista<>();
            visitado = 0;
        }

        public void visitar() {
            visitado++;
        }

        public int getVisitado() {
            return visitado;
        }

        public void resetVisita() {
            visitado = 0;
        }

        public Nodo<E> buscar(String id) {
            if (contenido.toString().equals(id)) {
                return this;
            }
            for(Nodo<E> hijo : hijos) {
                Nodo<E> resultadoHijo = hijo.buscar(id);
                if (resultadoHijo != null)
                    return resultadoHijo;
            }
            return null;
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
