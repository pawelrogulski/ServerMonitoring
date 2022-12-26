package com.example.servermonitoring.components;

public class Ram {
    private String totalMemory;
    private String inUse;

    public Ram(String totalMemory, String inUse) {
        this.totalMemory = totalMemory;
        this.inUse = inUse;
    }

    public Ram() {
    }

    public String getTotalMemory() {
        return totalMemory;
    }

    public String getInUse() {
        return inUse;
    }
}