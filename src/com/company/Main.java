package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    // List of agents
    static Agent agent_1 = new Agent(new int[]{2, 1, 1}, new int[]{-1, 2, 4, 2, -1, -1}, "agent 1", 1);
    static Agent agent_2 = new Agent(new int[]{2, 1, 1}, new int[]{2, -1, 3, -1, -1}, "agent 2", 2);
    static Agent agent_3 = new Agent(new int[]{2, 1, 1}, new int[]{4, 3, -1, -1, 2, 2}, "agent 3", 3);
    static Agent agent_4 = new Agent(new int[]{2, 1, 1}, new int[]{2, -1, 6, -1, 3, -1}, "agent 4", 4);
    static Agent agent_5 = new Agent(new int[]{1, 0, 2}, new int[]{-1, -1, 2, 3, -1, -1}, "agent 5", 5);
    static Agent agent_6 = new Agent(new int[]{2, 1, 2}, new int[]{-1, -1, 2, -1, -1, -1}, "agent 6", 6);
    static List<Agent> agentContractors = new ArrayList<>(List.of(new Agent[]{agent_1, agent_2, agent_5, agent_6}));


    // List of tasks
    static Task task_1 = new Task(new int[]{1, 1, 2}, "task 1");
    static Task task_2 = new Task(new int[]{2, 2, 3}, "task 2");

    // Social Network
    public static final int[][] mapConnection = {{-1,-1,-1,-1,-1,-1,-1}
            ,{-1,-1,2,4,2,-1,-1}
            ,{-1,2,-1,3,-1,-1,-1}
            ,{-1,4,3,-1,6,2,2}
            ,{-1,2,-1,6,-1,3,-1}
            ,{-1,-1,-1,2,3,-1,-1}
            ,{-1,-1,-1,2,-1,-1,-1}
    };

    public static void main(String[] args) {


        // Add tasks to solve
        HashMap<Task, Agent> managerList = new HashMap<>();
        managerList.put(task_1, agent_3);
        managerList.put(task_2, agent_4);

        // Generate Initial Population
        FunctionGA.select(FunctionGA.initPopulation(2,agentContractors,managerList));

    }
}
