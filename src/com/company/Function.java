package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.company.Main.allAgent;

public class Function {

    public static HashMap<String,List<Agent>> resultHashMap = new HashMap<>();

    public static void result(List<Task> tasks, List<Agent> agents){

        // Select task by sum of resources
        Task taskSelected;
        if(tasks.size() == 2) {
            taskSelected = taskSelected(tasks.get(0), tasks.get(1));
        } else if(tasks.size() == 1) {
            taskSelected = tasks.get(0);
        } else return;

        System.out.println("\nTask to solve: " + taskSelected.getName());

        // Select agent manager
        Agent value = agentSelected(taskSelected, agents);
        if (value != null) {
            System.out.println("Agent to handle " + taskSelected.getName() + ": " + value.getName());
            agents.removeIf(agent -> agent.getName().equals(value.getName()));
            tasks.removeIf(task -> task.getName().equals(taskSelected.getName()));
            System.out.println("\n--Find team for " + value.getName());
            findTeam(taskSelected, value);
            result(tasks, agents);
        }
    }

    private static void findTeam(Task task, Agent agent) {

        // Agent manager searches for agent contractors
        List<Agent> agentListConnection = new ArrayList<>();
        for (int i = 0; i < agent.getTotalConnection(); i++) {
            for (Agent value : allAgent) {
                if (agent.getConnection()[i] == value.id) {
                    agentListConnection.add(value);
                }
            }
        }

        List<Agent> teamSelected = new ArrayList<>();

        // Check if all required resources are provided
        for (int i = 0; i < agent.getTotalConnection(); i++) {
            if (checkAgent(task, agentListConnection.get(i))) {
                if (teamSelected != null) {
                    teamSelected.add(agentListConnection.get(i));
                }
            }

            if (checkDoneTask(task)) {
                System.out.println(Arrays.toString(task.getResource()));
                System.out.println(task.getName() + " completed");
                System.out.println("--------------------------------");
                break;
            }

            if (i == agent.getTotalConnection() - 1 && !checkDoneTask(task)) {
                System.out.println("\n" + task.getName() + " incomplete");
                teamSelected = null;
            }
        }

        if (teamSelected != null) {
            resultHashMap.put(task.getName(), teamSelected);
        }
    }

    public static void printAllAgentInList(String nameTask, List<Agent> agentList) {
        System.out.println("\nTEAM handle " + nameTask);
        for (Agent value : agentList) {
            System.out.println(value.getName());
        }
    }

    private static boolean checkDoneTask(Task task) {
        for (int i = 0; i < task.getResource().length; i++) {
            if (task.getResource()[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkAgent(Task task, Agent agent) {

        // Check required resources of selected task and available resources by agents
        System.out.println("\nChecking " + agent.getName());
        System.out.println("Required Resources of " + task.getName() + ": " + Arrays.toString(task.getResource()));
        System.out.println("Available Resources by " + agent.getName() + ": " + Arrays.toString(agent.getResource()));
        if (agent.getResource()[0] == 0 && agent.getResource()[1] == 0 && agent.getResource()[2] == 0) {
            return false;
        }
        int[] resOfTask = task.getResource();
        int[] resOfAgent = agent.getResource();
        int count = 0;
        for (int i = 0; i < resOfTask.length; i++) {
            if (resOfTask[i] != 0 && resOfAgent[i] != 0) {
                if (resOfTask[i] >= resOfAgent[i]) {
                    resOfTask[i] = resOfTask[i] - resOfAgent[i];
                    resOfAgent[i] = 0;
                } else {
                    resOfAgent[i] = resOfAgent[i] - resOfTask[i];
                    resOfTask[i] = 0;
                }
            } else count++;
        }

        // Let agent to handle the task if having enough required resources
        if(count == 3) {
            return false;
        }
        else {
            System.out.println(agent.getName() + " is handling");
            System.out.println("Required Resources of " + task.getName() + " after " + agent.getName() +  " handled: " + Arrays.toString(task.getResource()));
            System.out.println("Available Resources by " + agent.getName() + " after handling " + task.getName() +": " + Arrays.toString(agent.getResource()));
            return true;
        }
    }

    public static Agent agentSelected(Task task, List<Agent> agents) {

        // Find task-agent suitability
        double maxRatio = agents.get(0).getTotalRes() * 1.0 / task.getTotalRes();
        for (int i = 1; i < agents.size(); i++) {
            double ratio = agents.get(i).getTotalRes() * 1.0 / task.getTotalRes();
            if (maxRatio < ratio) {
                maxRatio = ratio;
            }
        }

        List<Agent> agentList = new ArrayList<>();
        for (Agent agent : agents) {
            double ratio = agent.getTotalRes() * 1.0 / task.getTotalRes();
            if (maxRatio == ratio) {
                agentList.add(agent);
            }
        }

        /*
            Return Error code if no agent managers found
            Return exact agent if only one agent manager found
            Return the agent with maxRatio
         */
        if (agentList.size() == 0) {
            System.out.println("Error code");
        } else if (agentList.size() == 1) {
            return agentList.get(0);
        } else {
            return agentSelectedByConnection(agentList);
        }
        return null;
    }

    public static Agent agentSelectedByConnection(List<Agent> agents) {

        // Find agent with max num of connections
        double maxConnection = agents.get(0).getTotalConnection();
        for (int i = 1; i < agents.size(); i++) {
            if (maxConnection < agents.get(i).getTotalConnection()) {
                maxConnection = agents.get(i).getTotalConnection();
            }
        }

        for (Agent agent : agents) {
            if (maxConnection == agent.getTotalConnection()) {
                return agent;
            }
        }
        return null;
    }

    //Find task with max sum of resources
    public static Task taskSelected(Task a, Task b) {
        return a.getTotalRes() >= b.getTotalRes() ? a : b;
    }

}
