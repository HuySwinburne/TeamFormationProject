package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.company.Main.*;
import static com.company.ReadFiles.*;

public class Backtracking {
    static int numberOfTask;
    static int numberOfAgent;
    static int[] agent;
    static List<List<Integer>> resultList = new ArrayList<>();

    public static void main(String[] args) {
        readData();
        numberOfAgent = agentContractorList.size();
        numberOfTask = taskList.size();
        agent = new int[numberOfAgent];
        Try(0);
        resultList.removeIf(integers -> !checkConfiguration(integers));
        List<Chromosome> chromosomeList = genListChromosome(resultList);
        Collections.sort(chromosomeList);

        System.out.println("\nResult:");
        System.out.println("There are " + chromosomeList.size() + " solutions. The best solution is: \n");
        System.out.println(chromosomeList.get(0));
    }

    public static List<Chromosome> genListChromosome (List<List<Integer>> lists) {
        List<Chromosome> chromosomeList = new ArrayList<>();
        int id = 0;
        for (List<Integer> integers : lists) {
            chromosomeList.add(genChromosome(id++, integers));
        }
        return chromosomeList;
    }

    public static Chromosome genChromosome(int id,List<Integer> chart) {
        HashMap<Agent, Task> hashMap = new HashMap<>();
        for (int i = 0; i < chart.size(); i++) {
            Agent agent = agentContractorList.get(i);
            Task task;
            if (chart.get(i) == 0) {
                task = null;
            } else {
                task = taskList.get(chart.get(i) - 1);
            }
            hashMap.put(agent, task);
        }
        Chromosome chromosome = new Chromosome(id);
        chromosome.setChromosomeInfo(hashMap);
        return chromosome;
    }

    public static void print() {
        List<Integer> configuration = new ArrayList<>();
        for (int i = 0; i < numberOfAgent; i++) {
            configuration.add(agent[i]);
        }
        resultList.add(configuration);
    }

    public static void Try(int i) {
        for (int j = 0; j <= numberOfTask; j++) {
            agent[i] = j;
            if (i == numberOfAgent - 1) {
                print();
            } else {
                Try(i + 1);
            }
        }
    }

    public static boolean checkConfiguration(List<Integer> configuration) {
        boolean check = true;
        for (int j = 1; j <= numberOfTask; j++) {
            int count = Collections.frequency(configuration, j);
            if (count > 3) {
                check = false;
                break;
            }
        }
        return check;
    }
}
