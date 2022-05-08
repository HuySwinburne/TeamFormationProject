package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.company.Main.allAgentContractor;

public class FunctionGA {
    public static void randomTeam(int numberOfItems, Agent agentManager){
        List<Integer> lstConnection = new ArrayList<>();
        int[] arrayConnection = agentManager.getConnection();

        for(int i = 0; i< arrayConnection.length; i++) {
//            for (Agent value : allAgentContractor) {
                if (arrayConnection[i] != -1) {
                    lstConnection.add(i + 1);
                }
//            }
        }
        System.out.println(lstConnection);
        System.out.println("");
        while (lstConnection.size() > 0) {
            System.out.println(getRandomElement(lstConnection,numberOfItems));
            System.out.println("");
        }
    }

    public static List<Integer> getRandomElement(List<Integer> list, int numberOfItems)
    {
        Random rand = new Random();

        // create a temporary list for storing selected element
        List<Integer> newList = new ArrayList<>();
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
            list.remove(randomIndex);
        }
        return newList;
    }
}
