package com.company;

import java.util.*;

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

    public static void select(List<Team> teamList){
        System.out.println("2 team were selected :");
        System.out.println(teamList.get(0));
        int randomIndexInList = getRandomIndexInList(teamList);
        System.out.println(teamList.get(randomIndexInList));

        System.out.println("\ncrossover");
        exchangeItem(2,teamList.get(0),teamList.get(randomIndexInList));

        System.out.println("\nresult : ");
        System.out.println(teamList);
        System.out.println(teamList.get(0));
        System.out.println(teamList.get(randomIndexInList));
    }

}