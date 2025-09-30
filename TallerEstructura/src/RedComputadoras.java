/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author itsmeale_m
 */
import java.util.*;

public class RedComputadoras{

    static class Red {
        Map<String, Set<String>> adj = new HashMap<>();

        void addComputer(String c) {
            adj.putIfAbsent(c, new HashSet<>());
        }

        void addConnection(String a, String b) {
            addComputer(a);
            addComputer(b);
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        void showAll() {
            adj.forEach((k, v) -> System.out.println(k + " -> " + v));
        }

        boolean canCommunicate(String start, String end) {
            if (!adj.containsKey(start) || !adj.containsKey(end)) return false;
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            queue.add(start);
            visited.add(start);
            while (!queue.isEmpty()) {
                String current = queue.poll();
                if (current.equals(end)) return true;
                for (String neighbor : adj.get(current)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
            return false;
        }

        List<String> shortestPath(String start, String end) {
            if (!adj.containsKey(start) || !adj.containsKey(end)) return Collections.emptyList();
            Map<String, String> prev = new HashMap<>();
            Queue<String> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();
            queue.add(start);
            visited.add(start);
            while (!queue.isEmpty()) {
                String current = queue.poll();
                if (current.equals(end)) break;
                for (String neighbor : adj.get(current)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        prev.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
            List<String> path = new LinkedList<>();
            String at = end;
            while (at != null && prev.containsKey(at)) {
                path.add(0, at);
                at = prev.get(at);
            }
            if (at != null && at.equals(start)) {
                path.add(0, start);
            }
            return path;
        }
    }

    public static void main(String[] args) {
        Red red = new Red();

        red.addConnection("PC1", "PC2");
        red.addConnection("PC1", "PC3");
        red.addConnection("PC2", "PC4");
        red.addConnection("PC3", "PC5");
        red.addConnection("PC4", "PC6");

        System.out.println("Conexiones:");
        red.showAll();

        System.out.println("\n PC1 puede comunicarse con PC6?");
        System.out.println(red.canCommunicate("PC1", "PC6") ? "Si" : "No");

        System.out.println("\nCamino mas corto de PC1 a PC6:");
        List<String> camino = red.shortestPath("PC1", "PC6");
        if (!camino.isEmpty())
            System.out.println(camino + " (" + (camino.size() - 1) + " saltos)");
        else
            System.out.println("No existe conexion.");
    }
}
