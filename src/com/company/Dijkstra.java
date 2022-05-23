package com.company;

public class Dijkstra {
    public static int findBestPath(int n, int[][] weightCon, int s, int e) {
        int MAX_F = 999999999;
        int[] f = new int[n+1];
        boolean[] check = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            f[i] = MAX_F;
            check[i] = true;
        }

        f[s] = 0;
        int v = s;
        int fmin;

        while (v != e) {
            fmin = MAX_F;
            for (int i = 1; i <= n; i++) {
                if (check[i] && fmin > f[i]) {
                    fmin = f[i];
                    v = i;
                }
            }
            if(fmin == MAX_F) break;
            check[v] = false;
            for(int i =1; i <= n; i++) {
                if(check[i] && weightCon[v][i] > 0 && f[i] > f[v] + weightCon[v][i]) {
                    f[i] = f[v] + weightCon[v][i];
                }
            }
        }
        return f[e];
    }
}