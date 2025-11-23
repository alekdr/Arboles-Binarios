/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Busqueda_de_Claves;

/**
 *
 * @author itsmeale
 */


public class BusquedaArbol_C {

    class Nodo {
        String dato;
        Nodo izquierda, derecha;

        Nodo(String d) { dato = d; }
    }

    Nodo raiz;

    void insertar(String d) {
        raiz = insertarRec(raiz, d);
    }

    Nodo insertarRec(Nodo r, String d) {
        if (r == null) return new Nodo(d);
        if (d.compareTo(r.dato) < 0) r.izquierda = insertarRec(r.izquierda, d);
        else if (d.compareTo(r.dato) > 0) r.derecha = insertarRec(r.derecha, d);
        return r;
    }

    boolean buscarConRecorrido(String clave) {
        System.out.print( "buscando clave " + clave + " = "); 
        return buscarRec(raiz, clave);
    }

    boolean buscarRec(Nodo r, String clave) {
        if (r == null) {
            System.out.println("no encontrado");
            return false;
        }

        System.out.print(r.dato + " ");

        if (clave.equals(r.dato)) {
            System.out.println("encontrado");
            return true;
        }

        if (clave.compareTo(r.dato) < 0)
            return buscarRec(r.izquierda, clave);
        else
            return buscarRec(r.derecha, clave);
    }

    public static void main(String[] args) {

        BusquedaArbol_C arbol = new BusquedaArbol_C();
        String[] C = {"a", "y", "e", "f", "g", "x", "w", "u", "z", "r", "b"};

        for (String v : C) arbol.insertar(v);

        System.out.println("busqueda de los arboles:");

        arbol.buscarConRecorrido("u");
        arbol.buscarConRecorrido("v");
        arbol.buscarConRecorrido("w");
    }
}
