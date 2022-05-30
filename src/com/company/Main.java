package com.company;

import java.util.*;

import static com.company.FunctionGA.*;

public class Main {

    // List of agents
    static Agent agent_1 = new Agent(new int[]{2, 1, 1}, new int[]{-1, 2, 4, 2, -1, -1}, "agent 1", 1);
    static Agent agent_2 = new Agent(new int[]{2, 1, 3}, new int[]{2, -1, 3, -1, -1}, "agent 2", 2);
    static Agent agent_3 = new Agent(new int[]{2, 1, 1}, new int[]{4, 3, -1, -1, 2, 2}, "agent 3", 3);
    static Agent agent_4 = new Agent(new int[]{2, 1, 1}, new int[]{2, -1, 6, -1, 3, -1}, "agent 4", 4);
    static Agent agent_5 = new Agent(new int[]{1, 1, 2}, new int[]{-1, -1, 2, 3, -1, -1}, "agent 5", 5);
    static Agent agent_6 = new Agent(new int[]{2, 1, 2}, new int[]{-1, -1, 2, -1, -1, -1}, "agent 6", 6);

    // List of tasks
    static Task task_1 = new Task(new int[]{4, 2, 4}, "task 1");
    static Task task_2 = new Task(new int[]{3, 3, 3}, "task 2");
    static List<Agent> agentContractorList = new ArrayList<>(List.of(new Agent[]{agent_1, agent_2, agent_5, agent_6}));
    static List<Task> taskList = new ArrayList<>(List.of(new Task[]{task_1, task_2}));
    static List<Chromosome> result = new ArrayList<>();

    // Social Network
    public static final int[][] mapConnection = {{-1,-1,-1,-1,-1,-1,-1}
            ,{-1,-1,2,4,2,-1,-1}
            ,{-1,2,-1,3,-1,-1,-1}
            ,{-1,4,3,-1,6,2,2}
            ,{-1,2,-1,6,-1,3,-1}
            ,{-1,-1,-1,2,3,-1,-1}
            ,{-1,-1,-1,2,-1,-1,-1}
    };

    static HashMap<Task, Agent> managerList = new HashMap<>() {{
        put(task_1, agent_3);
        put(task_2, agent_4);
    }};

    public static void main(String[] args) {

        List<Gen> genList = generateGens();
        List<Chromosome> chromosomeResult = new ArrayList<>();
        for(Gen gen: genList){
            chromosomeResult.add(gen.getChromosomeList().get(0));
        }

        System.out.println("Final solution:");
        Chromosome chromosome = printResult(chromosomeResult);

        if(chromosome!=null) {
            for (Gen gen : genList) {
                if (gen.getChromosomeList().get(0) == chromosome) {
                    System.out.println("This chromosome is in Gen: " + gen.getId());
                    break;
                }
            }
        }
    }
}