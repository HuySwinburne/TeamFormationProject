package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.company.Main.*;

public class ReadFiles {
    public static List<Task> readTaskData() {
        List<Task> taskList = new ArrayList<>();
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File("C:\\Users\\bqhuy\\OneDrive\\Desktop\\Folders\\Semester2\\Experiment\\Data\\d6\\taskList.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (fileScanner != null) {
            int index = 1;
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                String numberOnly = data.replaceAll("[^0-9]", "");
                int[] res = new int[numberOnly.length()];
                for (int i = 0; i < numberOnly.length(); i++) {
                    res[i] = Integer.parseInt(String.valueOf(numberOnly.charAt(i)));
                }
                String name = "task " + index;
                Task task = new Task(res, name);
                taskList.add(task);
                index++;
            }
        }
        if (fileScanner != null) {
            fileScanner.close();
        }
        return taskList;
    }

    public static HashMap<Task, Agent> readManagerListData() {
        HashMap<Task, Agent> managerList = new HashMap<>();
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File("C:\\Users\\bqhuy\\OneDrive\\Desktop\\Folders\\Semester2\\Experiment\\Data\\d6\\managerList.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (fileScanner != null) {
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                String[] numbers = data.split("\\D+");
                int indexTask = Integer.parseInt(String.valueOf(numbers[0])) - 1;
                int indexAgent = Integer.parseInt(String.valueOf(numbers[1])) - 1;
                managerList.put(taskList.get(indexTask), agentContractorList.get(indexAgent));
            }
        }
        if (fileScanner != null) {
            fileScanner.close();
        }
        return managerList;
    }

    public static List<Agent> readAgentData() {
        List<Agent> agentList = new ArrayList<>();
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File("C:\\Users\\bqhuy\\OneDrive\\Desktop\\Folders\\Semester2\\Experiment\\Data\\d6\\agentList.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (fileScanner != null) {
            int index = 1;
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                String numberOnly = data.replaceAll("[^0-9]", "");
                int[] res = new int[numberOnly.length()];
                for (int i = 0; i < numberOnly.length(); i++) {
                    res[i] = Integer.parseInt(String.valueOf(numberOnly.charAt(i)));
                }
                String name = "agent " + index;
                Agent agent = new Agent(res, name, index);
                agentList.add(agent);
                index++;
            }
        }
        if (fileScanner != null) {
            fileScanner.close();
        }
        return agentList;
    }

    public static int[][] readMapConnectionData() {
        List<List<Integer>> mapValue = new ArrayList<>();
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File("C:\\Users\\bqhuy\\OneDrive\\Desktop\\Folders\\Semester2\\Experiment\\Data\\d6\\mapConnection.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (fileScanner != null) {
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                Pattern pattern = Pattern.compile("-?[0-9]+");
                Matcher matcher = pattern.matcher(data);
                List<Integer> values = new ArrayList<>();
                while (matcher.find()) {
                    int value = Integer.parseInt(matcher.group());
                    values.add(value);
                }
                mapValue.add(values);
            }
        }
        if (fileScanner != null) {
            fileScanner.close();
        }
        int[][] mapArray = new int[mapValue.get(0).size()][mapValue.size()];
        for (int i = 0; i < mapValue.size(); i++) {
            for (int j = 0; j < mapValue.get(i).size(); j++) {
                mapArray[i][j] = mapValue.get(i).get(j);
            }
        }
        return mapArray;
    }

}
