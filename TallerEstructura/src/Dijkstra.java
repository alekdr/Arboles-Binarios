/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author itsmeale_m
 */

import java.util.*;

public class Dijkstra {
    static class Node { 
        int v, w; 
        Node(int v,int w){ this.v=v; this.w=w; } 
    }

    static class Result {
        int[] dist;
        int[] prev;
        Result(int[] dist, int[] prev) {
            this.dist = dist;
            this.prev = prev;
        }
    }

    static Result dijkstra(int n, Map<Integer, List<Node>> graph, int src) {
        int[] dist = new int[n];
        int[] prev = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);

        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
        pq.add(new int[]{0, src});

        while (!pq.isEmpty()) {
            var cur = pq.poll();
            int d = cur[0], u = cur[1];
            if (d != dist[u]) continue;

            for (Node edge : graph.getOrDefault(u, Collections.emptyList())) {
                if (dist[edge.v] > dist[u] + edge.w) {
                    dist[edge.v] = dist[u] + edge.w;
                    prev[edge.v] = u; 
                    pq.add(new int[]{dist[edge.v], edge.v});
                }
            }
        }
        return new Result(dist, prev);
    }


    static List<Integer> getPath(int[] prev, int dest) {
        LinkedList<Integer> path = new LinkedList<>();
        for (int at = dest; at != -1; at = prev[at]) {
            path.addFirst(at);
        }
        return path;
    }

    public static void main(String[] args) {
        Map<Integer, List<Node>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(new Node(1,10), new Node(4,3)));
        graph.put(1, Arrays.asList(new Node(2,2), new Node(4,4)));
        graph.put(2, Arrays.asList(new Node(3,9)));
        graph.put(3, Arrays.asList(new Node(2,7)));
        graph.put(4, Arrays.asList(new Node(1,1), new Node(2,8), new Node(3,2)));

        int n = 5;
        int src = 0;

        Result res = dijkstra(n, graph, src);

        System.out.println("distancias minimas desde el vwrtice " + src + ":");
        for (int i = 0; i < n; i++) {
            System.out.print("→ " + i + " : ");
            if (res.dist[i] == Integer.MAX_VALUE) {
                System.out.println("inaccesible");
            } else {
                System.out.println(res.dist[i] + " Camino: " + getPath(res.prev, i));
            }
        }
    }
}
