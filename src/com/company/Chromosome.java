package com.company;

import java.util.HashMap;
import java.util.List;
import static com.company.Main.agentContractorList;

public class Chromosome implements Comparable<Chromosome> {
    List<Team> teamList;

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public int getFitness(){
        int total = 0;
        for (Team t:
                teamList) {
            total+= t.getFitness();
        }
        return total;
    }

    public HashMap<Agent,Task> getChartChromosome(){
        HashMap<Agent,Task> hashMapChromo = new HashMap<>();
        for (Agent agent: agentContractorList) {
            for (Team team: teamList) {
                if(team.getLstAgent().contains(agent)){
                    hashMapChromo.put(agent,team.getTask());
                    break;
                }
            }
        }
        return hashMapChromo;
    }

    @Override
    public String toString() {
        return "Chromosome{" + teamList +
                " fitness=" + getFitness() +
                "}\n";
    }

    @Override
    public int compareTo(Chromosome o) {
        int fitness = o.getFitness();
        return fitness - this.getFitness();
    }
}