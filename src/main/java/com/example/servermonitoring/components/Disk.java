package com.example.servermonitoring.components;

public class Disk {
    private String name;
    private String totalSpace;
    private String usedSpace;

    public Disk(String name, String totalSpace, String usedSpace) {
        this.name = name;
        this.totalSpace = totalSpace;
        this.usedSpace = usedSpace;
    }

    public Disk() {
    }

    public String getName() {
        return name;
    }

    public String getTotalSpace() {
        return totalSpace;
    }

    public String getUsedSpace() {
        return usedSpace;
    }
}