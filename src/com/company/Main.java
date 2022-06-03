package com.company;

import java.util.*;

import static com.company.FunctionGA.*;
import static com.company.ReadFiles.*;

public class Main {

    public static List<Agent> agentContractorList = new ArrayList<>();
    public static List<Task> taskList = new ArrayList<>();
    public static int[][] mapConnection;
    public static HashMap<Task, Agent> managerList;
    public static int numberAgent;
    public static void main(String[] args) {

        taskList.addAll(readTaskData());
        agentContractorList.addAll(readAgentData());
        numberAgent = agentContractorList.size();
        mapConnection = readMapConnectionData();
        managerList = readManagerListData();
        for (Task task : managerList.keySet()) {
            agentContractorList.remove(managerList.get(task));
        }

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