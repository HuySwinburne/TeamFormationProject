package com.company;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static com.company.GAConfig.*;
import static com.company.Main.*;

public class Team implements Comparable<Team> {
    private Task task;
    private List<Agent> lstAgent;

    public List<Agent> getLstAgent() {
        return lstAgent;
    }

    public void setLstAgent(List<Agent> lstAgent) {
        this.lstAgent = lstAgent;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getTotalResOfTeam() {
        int total = 0;
        for (Agent agent : lstAgent) {
            total += agent.getTotalRes();
        }
        return total;
    }

    public double getFitness() {
        return ((1-parameter)* getUValue() - parameter * getCValue());
    }

    public double getUValue() {
        if(checkDoneTask()) {
            return task.getTotalRes();
        } else {
            return (task.getTotalRes() / Math.exp((double) task.getTotalRes()/getTotalResOfTeam()));
        }
    }

    public double getCValue() {
        int cValue = 0;
        for(int i = 1; i< lstAgent.size();i++) {
            cValue += Dijkstra.findBestPath(numberAgent, Main.mapConnection, lstAgent.get(0).id, lstAgent.get(i).id);
        }
        return cValue;
    }

    public boolean checkDoneTask() {
        for (int i = 0; i < task.getResource().length; i++) {
            int totalResTeam = 0;
            for (Agent agent : lstAgent)
                totalResTeam += agent.getResource()[i];
            if (totalResTeam < task.getResource()[i]) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Team{" +
                "Task=" + task +
                ", List=" + lstAgent +
                ", U=" + getUValue() +
                ", C=" + getCValue() +
                ", f=" + getFitness() +
                '}';
    }

    @Override
    public int compareTo(Team o) {
        double fitness = o.getFitness();
        return Double.compare(this.getFitness(), fitness);
    }

    // Check if 2 selected teams are the same
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(task, team.getTask())
                && new HashSet<>(lstAgent).containsAll(team.getLstAgent())
                && new HashSet<>(team.getLstAgent()).containsAll(lstAgent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, lstAgent);
    }
}