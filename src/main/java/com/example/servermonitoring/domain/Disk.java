package com.example.servermonitoring.domain;
/** konstruktory, metody get oraz metody set zostały wpisane ręcznie przez konflikt biblioteki Lombok z mapowaniem obiektów w JSON'em **/
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