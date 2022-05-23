package com.company;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.company.Main.*;
import static com.company.OtherFunctions.*;

public class FunctionGA {
    /**
     *
     * @param managerList - task map agent managers
     */
    public static List<Chromosome> initPopulation(int max, HashMap<Task, Agent> managerList) {

        List<Chromosome> chromosomeList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Chromosome chromosome = new Chromosome();
            chromosome.setTeamList(genChromosome(taskList,agentContractorList,max,managerList));
            chromosomeList.add(chromosome);
        }
        return chromosomeList;

    }

    public static boolean checkInvalidChromosome(Chromosome chromosome, int max) {
        for (Task task : taskList) {
            if (!chromosome.getChartChromosome().containsValue(task)) {
                return false;
            } else if (Collections.frequency(chromosome.getChartChromosome().values(), task) > max)
                return false;
        }
        return true;
    }

    public static List<Team> genChromosome(List<Task> lstTask, List<Agent> agentList, int max, HashMap<Task, Agent> managerList) {
        Random rand = new Random();
        List<Task> taskChroList = new ArrayList<>();
        List<Task> tempTaskList1 = new ArrayList<>(lstTask);
        List<Task> tempTaskList2 = new ArrayList<>();
        for (int i = 0; i < agentList.size(); i++) {
            if (i < lstTask.size()) {
                int randomIndex = rand.nextInt(tempTaskList1.size());
                Task taskSelected = tempTaskList1.get(randomIndex);
                taskChroList.add(taskSelected);
                tempTaskList2.add(taskSelected);
                tempTaskList1.remove(taskSelected);
            } else {
                int randomIndex = ThreadLocalRandom.current().nextInt(-1, tempTaskList2.size() - 1);
                if(randomIndex != -1) {
                    Task taskSelected = tempTaskList2.get(randomIndex);
                    int occurrences = Collections.frequency(taskChroList, taskSelected);
                    if (occurrences == max - 1) {
                        tempTaskList2.remove(taskSelected);
                    }
                    taskChroList.add(taskSelected);
                } else taskChroList.add(null);
            }
        }
        Collections.shuffle(taskChroList);

        List<Team> population = new ArrayList<>();
        for(Task task : managerList.keySet()) {
            Team team = new Team();
            List<Agent> teamList = new ArrayList<>();
            teamList.add(managerList.get(task));
            for(int i = 0; i< taskChroList.size(); i++) {
                if(taskChroList.get(i)!= null) {
                    if (task.getName().equals(taskChroList.get(i).getName())) {
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
}