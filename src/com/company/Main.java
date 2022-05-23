package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.company.FunctionGA.*;

public class Main {

    // List of agents
    static Agent agent_1 = new Agent(new int[]{2, 1, 1}, new int[]{-1, 2, 4, 2, -1, -1}, "agent 1", 1);
    static Agent agent_2 = new Agent(new int[]{2, 1, 3}, new int[]{2, -1, 3, -1, -1}, "agent 2", 2);
    static Agent agent_3 = new Agent(new int[]{2, 1, 1}, new int[]{4, 3, -1, -1, 2, 2}, "agent 3", 3);
    static Agent agent_4 = new Agent(new int[]{2, 1, 1}, new int[]{2, -1, 6, -1, 3, -1}, "agent 4", 4);
    static Agent agent_5 = new Agent(new int[]{1, 1, 2}, new int[]{-1, -1, 2, 3, -1, -1}, "agent 5", 5);
    static Agent agent_6 = new Agent(new int[]{2, 1, 2}, new int[]{-1, -1, 2, -1, -1, -1}, "agent 6", 6);
    static List<Agent> agentContractorList = new ArrayList<>(List.of(new Agent[]{agent_1, agent_2, agent_5, agent_6}));

    // List of tasks
    static Task task_1 = new Task(new int[]{4, 2, 4}, "task 1");
    static Task task_2 = new Task(new int[]{3, 3, 3}, "task 2");
    static List<Task> taskList = new ArrayList<>(List.of(new Task[]{task_1, task_2}));
    static HashMap<Task, Agent> managerList = new HashMap<>();
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

    public static void main(String[] args) {
        managerList.put(task_1, agent_3);
        managerList.put(task_2, agent_4);
        List<Chromosome> chromosomeList = initPopulation(3);
        List<Chromosome> matingPool = new ArrayList<>();
//        System.out.println(chromosomeList);
        System.out.println("Initial Population");
        System.out.println("Chromosome with highest fitness score: \n");
        Collections.sort(chromosomeList);
        System.out.println(chromosomeList.get(0));

//        Chromosome[] selectChromosome = select(chromosomeList);
//        System.out.println(crossOver(selectChromosome));

        // Generate new population in the mating pool
        do {
            Chromosome[] selectChromosomes = select(chromosomeList);
            matingPool.addAll(crossOver(selectChromosomes));
        }
        while (matingPool.size() < (populationSize - 1));

//        System.out.println(matingPool);

        List<Chromosome> newPopulation = new ArrayList<>();
        newPopulation.addAll(chromosomeList);
        newPopulation.addAll(matingPool);
        Collections.sort(newPopulation);

        System.out.println("New generation");
        System.out.println("Chromosome with highest fitness score: \n");
        System.out.println(newPopulation.get(0));
    }
}