package com.company;

import java.util.List;

public class Team {
    List<Agent> lstAgent;
    int uValue;
    int cValue;

    public List<Agent> getLstAgent() {
        return lstAgent;
    }

    public void setLstAgent(List<Agent> lstAgent) {
        this.lstAgent = lstAgent;
    }

    public int getUValue() {
        return uValue;
    }

    public void setUValue(int uValue) {
        this.uValue = uValue;
    }

    public int getCValue() {
        return cValue;
    }

    public void setCValue(int cValue) {
        this.cValue = cValue;
    }

    @Override
    public String toString() {
        return "Team{" +
                "lstAgent=" + lstAgent +
                ", uValue=" + uValue +
                ", cValue=" + cValue +
                '}';
    }
}
