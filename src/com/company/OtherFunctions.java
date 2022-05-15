package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class OtherFunctions {
    public static List<Agent> getRandomElement(List<Agent> list, int numberOfItems) {
        Random rand = new Random();

        // create a temporary list for storing selected element
        List<Agent> newList = new ArrayList<>();
        for (int i = 0; i < numberOfItems; i++) {
            // case size of list < totalItem then return new list
            if (list.size() < numberOfItems) {
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

    public static int getRandomIndexInList(List<Team> teamList) {
        if (teamList.size() - 1 > 1) {
            return ThreadLocalRandom.current().nextInt(1, teamList.size() - 1);
        } else return 1;
    }

    public static void exchangeItem(int n, Team team1, Team team2) {
        List<Agent> lstAgent1 = team1.getLstAgent();
        int team1Size = lstAgent1.size();
        List<Agent> lstAgent2 = team2.getLstAgent();
        int team2Size = lstAgent2.size();
        int minSize = Math.min(team1Size, team2Size);
        if (n <= minSize - 1) {
            while (n != 0) {
                team1Size--;
                team2Size--;
                Agent temp = lstAgent1.get(team1Size);
                lstAgent1.remove(team1Size);
                lstAgent1.add(team1Size, lstAgent2.get(team2Size));
                lstAgent2.remove(team2Size);
                lstAgent2.add(team2Size, temp);
                n--;
            }
        }

    }
}
