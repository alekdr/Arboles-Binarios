/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author itsmeale_m
 */

import java.util.Arrays;

public class Warshall {
    static boolean[][] warshall(boolean[][] reach) {
        int n = reach.length;
        boolean[][] r = new boolean[n][n];
        for (int i = 0; i < n; i++) r[i] = Arrays.copyOf(reach[i], n);

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    r[i][j] = r[i][j] || (r[i][k] && r[k][j]);

        return r;
    }

    public static void main(String[] args) {
        boolean[][] reach = {
            {true,  true,  false, false}, 
            {false, true,  true,  false}, 
            {true,  false, true,  true }, 
            {false, false, false, true }  
        };

        boolean[][] res = warshall(reach);

        System.out.println("Matriz de alcanzabilidad:");
        for (boolean[] row : res) System.out.println(Arrays.toString(row));


        boolean fuertementeConexo = true;
        for (boolean[] re : res) {
            for (int j = 0; j < res.length; j++) {
                if (!re[j]) {
                    fuertementeConexo = false;
                    break;
                }
            }
        }

        System.out.println("\n es fuertemente conexo? " + fuertementeConexo);
    }
}
