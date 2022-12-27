package com.example.servermonitoring.components;
import java.util.List;
/** konstruktory, metody get oraz metody set zostały wpisane ręcznie przez konflikt biblioteki Lombok z mapowaniem obiektów w JSON'em **/
public class OperatingSystem {
    private String hostname;
    private String uptime;
    private Cpu cpu;
    private Ram ram;
    private List<Disk> disks;
    private List<NetworkInterface> networkInterfaces;

    public OperatingSystem(String hostname, String uptime, Cpu cpu, Ram ram, List<Disk> disks, List<NetworkInterface> networkInterfaces) {
        this.hostname = hostname;
        this.uptime = uptime;
        this.cpu = cpu;
        this.ram = ram;
        this.disks = disks;
        this.networkInterfaces = networkInterfaces;
    }

    public OperatingSystem() {
    }

    public String getHostname() {
        return hostname;
    }

    public String getUptime() {
        return uptime;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public Ram getRam() {
        return ram;
    }

    public List<Disk> getDisks() {
        return disks;
    }

    public List<NetworkInterface> getNetworkInterfaces() {
        return networkInterfaces;
    }
}