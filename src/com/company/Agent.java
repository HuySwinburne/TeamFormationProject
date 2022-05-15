package com.company;

public class Agent {
    public final int id;
    private int[] resource;
    private int[] connection;
    private String name;

    public Agent(int[] resource, int[] connection, String name, int id) {
        this.resource = resource;
        this.connection = connection;
        this.name = name;
        this.id = id;
    }

    public int[] getResource() { return resource; }

    public void setResource(int[] resource) { this.resource = resource; }

    public int[] getConnection() { return connection; }

    public void setConnection(int[] connection) { this.connection = connection; }

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
        return "Agent{" +
                "name='" + name + '\'' +
                '}';
    }
}
