package com.company;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.company.Main.agentContractors;
import static com.company.OtherFunctions.*;

public class FunctionGA {
    /**
     *
     * @param numberOfItems - number agent of team except manager
     * @param agentContractors - list agent contractors
     * @param managerList - task map agent managers
     */
    public static List<Team> initPopulation(int numberOfItems, List<Agent> agentContractors
            , HashMap<Task, Agent> managerList) {

        /*
         * Population
         * Key is task
         * Item is team handle task
         */
        List<Team> population = new ArrayList<>();

        /*
         * Random team handle task
         */
        for (Task task : managerList.keySet()) {
            if (agentContractors.size() > 0) {
                Team team = new Team();
                List<Agent> teamList = new ArrayList<>();
                teamList.add(managerList.get(task));
                teamList.addAll(getRandomElement(agentContractors, numberOfItems));
                team.setLstAgent(teamList);
                team.setTask(task);
                population.add(team);
            }
        }
        Collections.sort(population);
        System.out.println(population);
        return population;
    }

    public static Team select(List<Team> teamList){
        int random = ThreadLocalRandom.current().nextInt(0, 3);
        System.out.println("\nstep 2 : select and crossover");
        System.out.println("2 team were selected :");
        Team team1 = teamList.get(0);
        System.out.println(team1);
        int randomIndexInList = getRandomIndexInList(teamList);
        Team team2 = teamList.get(randomIndexInList);
        System.out.println(team2);

        System.out.println("crossover");
        exchangeItem(random, team1, team2);

        System.out.println("result : ");
        System.out.println(team1);
        System.out.println(team2);

        return team1.getFitness() < team2.getFitness() ? team1 : team2;
    }

    public static void mutationRemoveAgent(Team team){
        System.out.println("\nstep 3 : mutation remove");
        List<Agent> agentList = team.getLstAgent();
        int u = team.getUValue();
        if(agentList.size() > 1) {
            int random = agentList.size() > 2 ? ThreadLocalRandom.current().nextInt(1, agentList.size() - 1) : 1;
            Agent agent = agentList.get(random);
            agentList.remove(random);
            if(team.getUValue() != u) {
                agentList.add(random,agent);
            } else {
                agentContractors.add(agent);
            }
        }
        System.out.println(team);
    }

    public static void mutationAddAgent(int numberOfItems, Team team){
        System.out.println("\nstep 3 : mutation add");
        List<Agent> agentList = team.getLstAgent();
        int u = team.getUValue();
        if(agentList.size() < numberOfItems && !team.checkDoneTask() && agentContractors.size() != 0){
            int random = ThreadLocalRandom.current().nextInt(0, agentContractors.size() - 1);
            Agent agent = agentContractors.get(random);
            agentList.add(agent);
            if(team.getUValue() == u) {
                agentList.remove(agent);
            }
        }
    }

}