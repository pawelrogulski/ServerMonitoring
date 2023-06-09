package com.example.servermonitoring.domain;
/** konstruktory, metody get oraz metody set zostały wpisane ręcznie przez konflikt biblioteki Lombok z mapowaniem obiektów w JSON'em **/
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