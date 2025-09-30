/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author itsmeale_m
 */

import java.util.*;

//el punto 1 y 2 del ejercicio están en eeste mismo codigo :)
//prim cumple con:
//El orden en que se seleccionan las aristas.
// El costo total del MST.
//Kruskal cumple con:
//Las aristas seleccionadas
// El número de aristas descartadas por formar ciclo.


//Compara el MST obtenido con el del algoritmo de Prim


public class KruskalPrimMST {

    static int minKey(int[] key, boolean[] used) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < key.length; v++)
            if (!used[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        return minIndex;
    }

    static int primMST(int[][] graph, int start) {
        int V = graph.length;
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] used = new boolean[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[start] = 0; parent[start] = -1;

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, used);
            used[u] = true;
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != Integer.MAX_VALUE && !used[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }

        int total = 0;
        System.out.println("Aristas del MST de Prim (inicio en " + start + "):");
        for (int i = 0; i < V; i++) {
            if (parent[i] != -1) {
                System.out.println(parent[i] + " - " + i + " : " + graph[i][parent[i]]);
                total += graph[i][parent[i]];
            }
        }
        System.out.println("Costo total del MST de Prim = " + total);
        return total;
    }

    static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        Edge(int s, int d, int w) { src = s; dest = d; weight = w; }
        @Override
        public int compareTo(Edge other) { return this.weight - other.weight; }
    }

    static class Subset {
        int parent, rank;
    }

    static int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    static void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x), yroot = find(subsets, y);
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    static int kruskalMST(int V, List<Edge> edges) {
        Collections.sort(edges);
        Subset[] subsets = new Subset[V];
        for (int v = 0; v < V; v++) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        int e = 0, i = 0, total = 0, descartadas = 0;
        Edge[] result = new Edge[V-1];

        while (e < V-1 && i < edges.size()) {
            Edge next = edges.get(i++);
            int x = find(subsets, next.src);
            int y = find(subsets, next.dest);
            if (x != y) {
                result[e++] = next;
                union(subsets, x, y);
            } else {
                descartadas++;
            }
        }

        System.out.println("\nAristas de Mst de Kruskal):");
        for (i = 0; i < e; i++) {
            System.out.println(result[i].src + " - " + result[i].dest + " : " + result[i].weight);
            total += result[i].weight;
        }
        System.out.println("costo total del MST de Kruskal) = " + total);
        System.out.println("numero de aristas descartadas por ciclo = " + descartadas);
        return total;
    }

    public static void main(String[] args) {
        final int INF = Integer.MAX_VALUE;
        int V = 6;
        int[][] graph = {
            {INF, 6, 1, 5, INF, INF},
            {6, INF, 2, INF, 5, INF},
            {1, 2, INF, 2, 6, INF},
            {5, INF, 2, INF, INF, 4},
            {INF, 5, 6, INF, INF, 3},
            {INF, INF, INF, 4, 3, INF}
        };

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0,1,6));
        edges.add(new Edge(0,2,1));
        edges.add(new Edge(0,3,5));
        edges.add(new Edge(1,2,2));
        edges.add(new Edge(1,4,5));
        edges.add(new Edge(2,3,2));
        edges.add(new Edge(2,4,6));
        edges.add(new Edge(3,5,4));
        edges.add(new Edge(4,5,3));

        int costPrim0 = primMST(graph, 0);
        int costPrim2 = primMST(graph, 2);
        int costKruskal = kruskalMST(V, edges);

        System.out.println("\nCoincide Prim 0 con Kruskal: " + (costPrim0 == costKruskal));
        System.out.println("Coincide Prim 2 con Kruskal: " + (costPrim2 == costKruskal));
    }
}
