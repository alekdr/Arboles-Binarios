/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Adiciones_ArbolesABC;
/**
 *
 * @author itsmeale
 */
public class AdicionArbol_B {

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
        AdicionArbol_B b = new AdicionArbol_B();
        int[] valores = {32, 67, 43, 25, 52, 56, 78, 64, 23, 67, 24, 26, 27}; //añado aqui las nuevas claves {24,26,27}

        for (int v : valores) b.insertar(v);

        System.out.println("Arboles");
        System.out.print("In Order:"); b.inOrder(b.raiz); System.out.println();
        System.out.print("Pre Order:"); b.preOrder(b.raiz); System.out.println();
        System.out.print("Post Order:"); b.postOrder(b.raiz); System.out.println();
    }
}
