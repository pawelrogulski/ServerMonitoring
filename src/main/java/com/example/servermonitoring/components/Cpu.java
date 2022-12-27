package com.example.servermonitoring.components;
/** konstruktory, metody get oraz metody set zostały wpisane ręcznie przez konflikt biblioteki Lombok z mapowaniem obiektów w JSON'em **/
public class Cpu {
    private String name;
    private String usage;

    public Cpu(String name, String usage) {
        this.name = name;
        this.usage = usage;
    }

    public Cpu() {
    }

    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }
}