package com.company;

import java.util.List;

public class Team implements Comparable<Team>{
    Task task;
    List<Agent> lstAgent;

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

    public int getTotalResOfTeam(){
        int total = 0;
        for(Agent agent : lstAgent){
            total+= agent.getTotalRes();
        }
        return total;
    }

    public int getFitness() {
        return getUValue() - getCValue();
    }

    public int getUValue() {
        if(checkDoneTask()) {
            return task.getTotalRes();
        } else {
            return (int) (task.getTotalRes()*(1/Math.exp(((double) getTotalResOfTeam()/task.getTotalRes()))));
        }
    }

    public int getCValue() {
        int cValue = 0;
        for(int i = 1; i< lstAgent.size();i++) {
            cValue += Dijkstra.findBestPath(6,Main.mapConnection,lstAgent.get(0).id, lstAgent.get(i).id);
        }
        return cValue;
    }

    public  boolean checkDoneTask() {
        for(int i = 0; i < task.getResource().length ; i++) {
            int totalResTeam = 0;
            for(Agent agent : lstAgent)
                totalResTeam+= agent.getResource()[i];
            if(totalResTeam < task.getResource()[i]) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Team{" +
                "List=" + lstAgent +
                ", U=" + getUValue() +
                ", C=" + getCValue() +
                ", fitness=" + getFitness() +
                '}';
    }

    @Override
    public int compareTo(Team o) {
        int fitness = o.getFitness();
        return this.getFitness() - fitness;
    }
}