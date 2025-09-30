/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author itsmeale_m
 */

import java.util.*;

public class BFS {
    static List<Integer> bfsShortestPath(Map<Integer, List<Integer>> g, int start, int goal) {
        Queue<Integer> q = new LinkedList<>();
        Map<Integer,Integer> parent = new HashMap<>();
        
        q.add(start);
        parent.put(start, null);
        
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == goal) break;
            for (int v : g.getOrDefault(u, Collections.emptyList())) {
                if (!parent.containsKey(v)) {
                    parent.put(v, u);
                    q.add(v);
                }
            }
        }
        
        if (!parent.containsKey(goal)) return Collections.emptyList();
        
        LinkedList<Integer> path = new LinkedList<>();
        Integer cur = goal;
        while (cur != null) { 
            path.addFirst(cur); 
            cur = parent.get(cur); 
        }
        return path;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        g.put(0, Arrays.asList(1,2));
        g.put(1, Arrays.asList(0,3));
        g.put(2, Arrays.asList(0,3));
        g.put(3, Arrays.asList(1,2,4));
        g.put(4, Arrays.asList(3,5));
        g.put(5, Arrays.asList(4));
        
        int origen = 0;
        int destino = 5;
        
        List<Integer> camino = bfsShortestPath(g, origen, destino);
        
        if (camino.isEmpty()) {
            System.out.println("no existe camino de " + origen + " a " + destino);
        } else {
            System.out.println("camino mas corto de " + origen + " a " + destino + ": " + camino);
        }
    }
}
