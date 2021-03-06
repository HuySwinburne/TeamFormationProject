package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static com.company.Main.*;

public class Chromosome implements Comparable<Chromosome> {
    private int id;
    private List<Team> teamList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public double getFitness(){
        double total = 0;
        for (Team t: teamList) {
            total+= t.getFitness();
        }
        return total;
    }

    public HashMap<Agent,Task> getChartChromosome(){
        HashMap<Agent,Task> hashMapChromosome = new HashMap<>();
        for (Agent agent: agentContractorList) {
            for (Team team: teamList) {
                if(team.getLstAgent().contains(agent)){
                    hashMapChromosome.put(agent,team.getTask());
                    break;
                }
            }
        }
        return hashMapChromosome;
    }

    public Chromosome() {
        this.id = FunctionGA.id;
        FunctionGA.id++;
    }

    public Chromosome(int id) {
        this.id = id;
    }

    public void setChromosomeInfo(HashMap<Agent, Task> hashMap) {
        List<Team> teamList = new ArrayList<>();
        List<Task> taskList = new ArrayList<>(Main.taskList);

        if(taskList.size() > 0) {
            for (Task task : taskList) {
                Team team = new Team();
                team.setTask(task);
                List<Agent> agentList = new ArrayList<>();
                for (Task task1 : managerList.keySet()) {
                    if(task1 == task){
                        agentList.add(managerList.get(task1));
                    }
                }
                for(Agent agent : hashMap.keySet()) {
                    if(hashMap.get(agent) == task) {
                        agentList.add(agent);
                    }
                }
                team.setLstAgent(agentList);
                teamList.add(team);
            }
        }
        this.teamList = teamList;
    }

    @Override
    public String toString() {
        return "Chromosome "+ id + " = " + teamList +
                "\n\nFitness Score = " + getFitness() +
                "\n";
    }

    @Override
    public int compareTo(Chromosome o) {
        double fitness = o.getFitness();
        return Double.compare(fitness, this.getFitness());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chromosome that = (Chromosome) o;
        return new HashSet<>(teamList).containsAll(that.getTeamList()) && new HashSet<>(that.getTeamList()).containsAll(teamList) && id == that.id;
    }
}
