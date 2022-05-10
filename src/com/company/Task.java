package com.company;

public class Task {
    private int[] resource;
    private String name;

    public Task(int[] resource, String name) {
        this.resource = resource;
        this.name = name;
    }

    public int[] getResource() {
        return resource;
    }

    public void setResource(int[] resource) {
        this.resource = resource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalRes(){
        int total = 0;
        for (int res : resource){
            total += res;
        }
        return total;
    }

    @Override
    public String toString(){
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }
}
