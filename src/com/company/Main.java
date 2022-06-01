package com.company;

import java.util.*;

import static com.company.FunctionGA.*;

public class Main {

    // List of agents
    static Agent agent_1 = new Agent(new int[]{1,2,0,2},"agent 1", 1);
    static Agent agent_2 = new Agent(new int[]{0,1,2,0},"agent 2", 2);
    static Agent agent_3 = new Agent(new int[]{2,0,2,1},"agent 3", 3);
    static Agent agent_4 = new Agent(new int[]{0,1,2,0},"agent 4", 4);
    static Agent agent_5 = new Agent(new int[]{2,2,0,0},"agent 5", 5);
    static Agent agent_6 = new Agent(new int[]{1,1,1,2},"agent 6", 6);
    static Agent agent_7 = new Agent(new int[]{1,2,1,0},"agent 7", 7);
    static Agent agent_8 = new Agent(new int[]{2,0,1,1},"agent 8", 8);
    static Agent agent_9 = new Agent(new int[]{2,0,1,1},"agent 9", 9);
    static Agent agent_10 = new Agent(new int[]{0,1,1,2},"agent 10", 10);
    static Agent agent_11 = new Agent(new int[]{1,2,1,1},"agent 11", 11);
    static Agent agent_12 = new Agent(new int[]{2,2,0,0},"agent 12", 12);

    // List of tasks
    static Task task_1 = new Task(new int[]{0,7,4,1}, "task 1");
    static Task task_2 = new Task(new int[]{5,0,4,7}, "task 2");
    static Task task_3 = new Task(new int[]{4,4,0,4}, "task 3");
//    static Task task_1 = new Task(new int[]{0,2,4,4}, "task 1");
//    static Task task_2 = new Task(new int[]{3,2,4,2}, "task 2");
//    static Task task_3 = new Task(new int[]{4,5,0,0}, "task 3");
//    static Task task_1 = new Task(new int[]{0,7,4,5}, "task 1");
//    static Task task_2 = new Task(new int[]{5,2,4,7}, "task 2");
//    static Task task_3 = new Task(new int[]{4,4,3,5}, "task 3");
//    static Task task_1 = new Task(new int[]{0,7,7,5}, "task 1");
//    static Task task_2 = new Task(new int[]{7,3,4,7}, "task 2");
//    static Task task_3 = new Task(new int[]{6,3,6,5}, "task 3");
//    static Task task_1 = new Task(new int[]{0,7,9,5}, "task 1");
//    static Task task_2 = new Task(new int[]{8,0,7,7}, "task 2");
//    static Task task_3 = new Task(new int[]{6,4,8,9}, "task 3");

    // Social Network
    public static final int[][] mapConnection = {
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
            ,{-1,-1,-1,1,4,-1,-1,9,-1,-1,1,1,4}
            ,{-1,-1,-1,-1,-1,2,3,-1,1,7,-1,3,3}
            ,{-1,1,-1,-1,3,-1,-1,6,-1,-1,4,1,1}
            ,{-1,4,-1,3,-1,-1,-1,8,4,-1,4,3,-1}
            ,{-1,-1,2,-1,-1,-1,1,-1,3,5,-1,2,1}
            ,{-1,-1,3,-1,-1,1,-1,-1,1,-1,1,1,5}
            ,{-1,9,-1,6,8,-1,-1,-1,-1,5,2,-1,4}
            ,{-1,-1,1,-1,4,3,1,-1,-1,-1,1,5,-1}
            ,{-1,-1,7,-1,-1,5,-1,5,-1,-1,1,3,1}
            ,{-1,1,-1,4,4,-1,1,2,1,1,-1,-1,1}
            ,{-1,1,3,1,3,2,1,-1,5,3,-1,-1,-1}
            ,{-1,4,3,1,-1,1,5,4,-1,1,1,-1,-1}
    };
    static List<Agent> agentContractorList = new ArrayList<>(List.of(new Agent[]{agent_1, agent_2, agent_3, agent_4,
            agent_5, agent_6, agent_7, agent_8, agent_9}));
    static List<Task> taskList = new ArrayList<>(List.of(new Task[]{task_1, task_2, task_3}));
    static List<Chromosome> result = new ArrayList<>();

    static HashMap<Task, Agent> managerList = new HashMap<>() {{
        put(task_1, agent_10);
        put(task_2, agent_11);
        put(task_3, agent_12);
    }};

    public static void main(String[] args) {

        List<Gen> genList = generateGens();
        List<Chromosome> chromosomeResult = new ArrayList<>();
        for(Gen gen: genList){
            chromosomeResult.add(gen.getChromosomeList().get(0));
        }

        System.out.println("\nFinal solution:");
        Chromosome chromosome = printResult(chromosomeResult);

        if(chromosome != null) {
            for (Gen gen: genList) {
                if (gen.getChromosomeList().get(0) == chromosome) {
                    System.out.println("This chromosome is in Gen: " + gen.getId());
                    break;
                }
            }
        }
    }
}