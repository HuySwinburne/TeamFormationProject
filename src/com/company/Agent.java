package com.company;

public class Agent {
    public final int id;
    private int[] resource;
    private String name;

    public Agent(int[] resource, String name, int id) {
        this.resource = resource;
        this.name = name;
        this.id = id;
    }

    public int[] getResource() { return resource; }

    public void setResource(int[] resource) { this.resource = resource; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getTotalRes(){
        int total = 0;
        for (int res : resource) {
            total += res;
        }
        return total;
    }

    @Override
    public String toString() {
        return name;
    }
}