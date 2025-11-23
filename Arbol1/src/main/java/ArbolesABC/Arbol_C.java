/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArbolesABC;

/**
 *
 * @author itsmeale
 */

public class Arbol_C {

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

    void inOrder(Nodo r) {
        if (r != null) {
            inOrder(r.izquierda);
            System.out.print(r.dato + " ");
            inOrder(r.derecha);
        }
    }

    void preOrder(Nodo r) {
        if (r != null) {
            System.out.print(r.dato + " ");
            preOrder(r.izquierda);
            preOrder(r.derecha);
        }
    }

    void postOrder(Nodo r) {
        if (r != null) {
            postOrder(r.izquierda);
            postOrder(r.derecha);
            System.out.print(r.dato + " ");
        }
    }

    public static void main(String[] args) {
        Arbol_C c = new Arbol_C();
        String[] valores = {"a", "y", "e", "f", "g", "x", "w", "u", "z", "r", "b"};

        for (String v : valores) c.insertar(v);

        System.out.println("Arboles");
        System.out.print("In Order:"); c.inOrder(c.raiz); System.out.println();
        System.out.print("Pre Order:"); c.preOrder(c.raiz); System.out.println();
        System.out.print("Post Order:"); c.postOrder(c.raiz); System.out.println();
    }
}
