package com.company;

import java.util.List;

public class Gen {
    private int id;
    private List<Chromosome> chromosomeList;

    public Gen(int id, List<Chromosome> chromosomeList) {
        this.id = id;
        this.chromosomeList = chromosomeList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Chromosome> getChromosomeList() {
        return chromosomeList;
    }

    public void setChromosomeList(List<Chromosome> chromosomeList) {
        this.chromosomeList = chromosomeList;
    }

    @Override
    public String toString() {
        return "Gen{" +
                "id=" + id +
                ", chromosomeList=" + chromosomeList +
                '}';
    }
}
