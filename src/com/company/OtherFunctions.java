package com.company;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.company.Main.*;
import static com.company.GAConfig.*;

public class OtherFunctions {

    // Generate initial population at random
    public static List<Team> genChromosome(List<Task> lstTask, List<Agent> agentList) {
        List<Task> taskChromosomeList = new ArrayList<>();
        List<Task> tempTaskList = new ArrayList<>(lstTask);
        for (int i = 0; i < agentList.size(); i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(-1, tempTaskList.size());
                if(randomIndex != -1) {
                    Task taskSelected = tempTaskList.get(randomIndex);
                    int occurrences = Collections.frequency(taskChromosomeList, taskSelected);
                    if (occurrences == numberOfAgents - 1) {
                        tempTaskList.remove(taskSelected);
                    }
                    taskChromosomeList.add(taskSelected);
                } else taskChromosomeList.add(null);
        }
        Collections.shuffle(taskChromosomeList);

        List<Team> population = new ArrayList<>();
        for(Task task : managerList.keySet()) {
            Team team = new Team();
            List<Agent> teamList = new ArrayList<>();
            teamList.add(managerList.get(task));
            for(int i = 0; i< taskChromosomeList.size(); i++) {
                if(taskChromosomeList.get(i)!= null) {
                    if (task.getName().equals(taskChromosomeList.get(i).getName())) {
                        teamList.add(agentList.get(i));
                    }
                }
            }
            team.setLstAgent(teamList);
            team.setTask(task);
            population.add(team);
        }
        return population;
    }

    public static boolean checkInvalidChromosome(HashMap<Agent,Task> chromosome, int max) {
        for (Task task : taskList) {
            if (Collections.frequency(chromosome.values(), task) > max) {
                return false;
            }
        }
        return true;
    }

    public static Chromosome findParentChromosome(List<Chromosome> chromosomeList) {
        int randomIndex1 = ThreadLocalRandom.current().nextInt(0, chromosomeList.size());
        int randomIndex2 = ThreadLocalRandom.current().nextInt(0, chromosomeList.size());
        return chromosomeList.get(randomIndex1).getFitness() > chromosomeList.get(randomIndex2).getFitness()
                ? chromosomeList.get(randomIndex1) : chromosomeList.get(randomIndex2);
    }
}