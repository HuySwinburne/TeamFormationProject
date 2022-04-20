package com.company;

import java.util.List;

public class Main {

    // List of agents
    static Agent a1 = new Agent(new int[]{2, 1, 1}, new int[]{1, 2, 3, 4}, "agent 1", 1);
    static Agent a2 = new Agent(new int[]{2, 1, 1}, new int[]{2, 1, 3}, "agent 2", 2);
    static Agent a3 = new Agent(new int[]{2, 2, 1}, new int[]{3, 1, 2, 4, 5}, "agent 3", 3);
    static Agent a4 = new Agent(new int[]{2, 1, 1}, new int[]{4, 1, 3, 5}, "agent 4", 4);
    static Agent a5 = new Agent(new int[]{1, 0, 2}, new int[]{5, 3, 4}, "agent 5", 5);
    static Agent a6 = new Agent(new int[]{2, 1, 2}, new int[]{6, 3}, "agent 6", 6);

    static List<Agent> allAgent = List.of(new Agent[]{a1, a2, a3, a4, a5, a6});

    // List of tasks
    static Task t2 = new Task(new int[]{1, 1, 2}, "task 2");
    static Task t3 = new Task(new int[]{2, 2, 3}, "task 3");

    public static void main(String[] args) {

        /*
        // Add tasks to solve
        List<Task> taskArrayList = new ArrayList<>();
        taskArrayList.add(t2);
        taskArrayList.add(t3);

        // Add agents to be agent managers
        List<Agent> agentArrayList = new ArrayList<>();
        agentArrayList.add(a3);
        agentArrayList.add(a4);

        Function.result(taskArrayList, agentArrayList);

        System.out.println("\n--------Result--------");
        Function.resultHashMap.forEach(Function::printAllAgentInList);
        */

        /*
        Dijkstra's Algorithm
         */
        int n = 6;                              // Number of nodes
        int[][] a = new int[n + 1][n + 1];      // Weights

        // Set up high weight for disconnected edges
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                a[i][j] = 10;
            }
        }

        // Given edge weights
        a[1][2] = 2;    a[2][1] = 2;    a[1][3] = 4;    a[3][1] = 4;
        a[1][4] = 2;    a[4][1] = 2;    a[2][3] = 3;    a[3][2] = 3;
        a[3][4] = 5;    a[4][3] = 5;    a[3][5] = 1;    a[5][3] = 1;
        a[3][6] = 2;    a[6][3] = 2;    a[4][5] = 3;    a[5][4] = 3;

        // Test the distance
        System.out.println("\nDistance from 4 to");
        Dijkstra.findBestPath(n, a, 4, 1);
        System.out.println("\nDistance from 4 to 2");
        Dijkstra.findBestPath(n, a, 4, 2);
        System.out.println("\nDistance from 4 to 3");
        Dijkstra.findBestPath(n, a, 4, 3);
        System.out.println("\nDistance from 4 to 4");
        Dijkstra.findBestPath(n, a, 4, 4);
        System.out.println("\nDistance from 4 to 5");
        Dijkstra.findBestPath(n, a, 4, 5);
        System.out.println("\nDistance from 4 to 6");
        Dijkstra.findBestPath(n, a, 4, 6);
    }

}
