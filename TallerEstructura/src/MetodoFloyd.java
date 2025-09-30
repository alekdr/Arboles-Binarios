/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author itsmeale_m
 */

import java.util.Arrays;

public class MetodoFloyd {
    static final int INF = 999999;

    static int[][] floydWarshall(int[][] dist, int[][] pred) {
        int n = dist.length;
        int[][] d = new int[n][n];

        for (int i = 0; i < n; i++) {
            d[i] = Arrays.copyOf(dist[i], n);
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] != INF) {
                    pred[i][j] = i;
                } else {
                    pred[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (d[i][k] + d[k][j] < d[i][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                        pred[i][j] = pred[k][j];
                    }
                }
            }
        }
        return d;
    }

    static void printPath(int u, int v, int[][] pred) {
        if (u == v) {
            System.out.print(u);
        } else if (pred[u][v] == -1) {
            System.out.print("No hay camino");
        } else {
            printPath(u, pred[u][v], pred);
            System.out.print(" -> " + v);
        }
    }

    public static void main(String[] args) {
        int[][] dist = {
            {0,   3,   INF, 7},
            {8,   0,   2,   INF},
            {5,   INF, 0,   1},
            {2,   INF, INF, 0}
        };

        int n = dist.length;
        int[][] pred = new int[n][n];

        int[][] res = floydWarshall(dist, pred);

        System.out.println("Matriz de distancias minimas:");
        for (int[] row : res) {
            System.out.println(Arrays.toString(row));
        }

        System.out.print("\nCamino minimo de 0 a 3: ");
        printPath(0, 3, pred);
        System.out.println("\nDistancia minima: " + res[0][3]);
    }
}
