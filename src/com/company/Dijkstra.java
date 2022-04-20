package com.company;

public class Dijkstra {
    public static void findBestPath(int n, int[][] weightCon, int s, int e) {
        int MAX_F = 999999999;
        int[] f = new int[n+1];
        boolean[] check = new boolean[n+1];
        int[] trace = new int[n+1];

        for (int i = 1; i <= n; i++) {
            f[i] = MAX_F;
            check[i] = true;
            trace[i] = 0;
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
                    trace[i] = v;
                }
            }

        }

        System.out.println(f[e]);
        int[] path = new int[n+1];
        int d = 1;
        path[d] = e;
        while (trace[e] != 0) {
            e = trace[e];
            d++;
            path[d] = e;
        }

        for(int i = d; i>0; --i) {
            System.out.print(path[i] + " ");
        }
    }
}
