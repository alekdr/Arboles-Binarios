/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Busqueda_de_Claves;

/**
 *
 * @author itsmeale
 */


public class BusquedaArbol_B {

    class Nodo {
        int dato;
        Nodo izquierda, derecha;

        Nodo(int d) { dato = d; }
    }

    Nodo raiz;

    void insertar(int d) {
        raiz = insertarRec(raiz, d);
    }

    Nodo insertarRec(Nodo r, int d) {
        if (r == null) return new Nodo(d);
        if (d < r.dato) r.izquierda = insertarRec(r.izquierda, d);
        else if (d > r.dato) r.derecha = insertarRec(r.derecha, d);
        return r;
    }

    boolean buscarConRecorrido(int clave) {
        System.out.print( "buscando clave " + clave + " = ");
        return buscarRec(raiz, clave);
    }

    boolean buscarRec(Nodo r, int clave) {
        if (r == null) {
            System.out.println("no encontrado");
            return false;
        }

        System.out.print(r.dato + " ");

        if (clave == r.dato) {
            System.out.println(" encontrado");
            return true;
        }

        if (clave < r.dato)
            return buscarRec(r.izquierda, clave);
        else
            return buscarRec(r.derecha, clave);
    }

    public static void main(String[] args) {

        BusquedaArbol_B arbol = new BusquedaArbol_B();
        int[] B = {32, 67, 43, 25, 52, 56, 78, 64, 23, 67};
        
        for (int v : B) arbol.insertar(v);

        System.out.println("busqueda de los arboles:");

        arbol.buscarConRecorrido(23);
        arbol.buscarConRecorrido(24);
        arbol.buscarConRecorrido(25);
    }
}
