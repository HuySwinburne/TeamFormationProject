package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class FunctionGA {
    /**
     *
     * @param numberOfItems - number agent of team except manager
     * @param agentContractors - list agent contructor
     * @param managerList - task map agent manager
     */
    public static void randomTeam(int numberOfItems, List<Agent> agentContractors
            , HashMap<Task,Agent> managerList){

        /*
         * Population
         * Key is task
         * Item is team handle task
         */
        HashMap<Task,Team> population = new HashMap<>();

        /*
         * Random team handle task
         */
        for(Task task : managerList.keySet()) {
            if(agentContractors.size() > 0) {
                Team team = new Team();
                List<Agent> teamList = new ArrayList<>();
                teamList.add(managerList.get(task));
                teamList.addAll(getRandomElement(agentContractors, numberOfItems));
                team.setLstAgent(teamList);
                population.put(task, team);
            }
        }

        System.out.println(population);
    }


    public static int calculateU(Task task, Team team) {
        if(checkDoneTask(task,team)) {
            return task.getTotalRes();
        } else {

            return 0;
        }
    }

    public static int calculateC(Task task, Team team) {
        return 0;
    }

    private static boolean checkDoneTask(Task task, Team team) {
        return false;
    }

    public static List<Agent> getRandomElement(List<Agent> list, int numberOfItems)
    {
        Random rand = new Random();

        // create a temporary list for storing selected element
        List<Agent> newList = new ArrayList<>();
        for (int i = 0; i < numberOfItems; i++) {
            // case size of list < totalItem then return new list
            if(list.size() < numberOfItems) {
                newList.addAll(list);
                list.clear();
                break;
            }
            // take a random index between 0 to size of given List
            int randomIndex = rand.nextInt(list.size());

            // add element in temporary list
            newList.add(list.get(randomIndex));

            // Remove selected element from original list
            list.remove(list.get(randomIndex));
        }
        return newList;
    }
}
