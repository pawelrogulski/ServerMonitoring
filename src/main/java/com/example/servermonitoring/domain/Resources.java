package com.example.servermonitoring.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.Timestamp;
import java.util.List;
/** konstruktory, metody get oraz metody set zostały wpisane ręcznie przez konflikt biblioteki Lombok z mapowaniem obiektów w JSON'em **/
@Document(collection = "resources")
public class Resources {
    @Id
    private int id;
    private Timestamp timestamp;
    private String hostname;
    private String uptime;
    private Cpu cpu;
    private Ram ram;
    private List<Disk> disks;
    private List<NetworkInterface> networkInterfaces;

    public Resources(String hostname, String uptime, Cpu cpu, Ram ram, List<Disk> disks, List<NetworkInterface> networkInterfaces) {
        this.hostname = hostname;
        this.uptime = uptime;
        this.cpu = cpu;
        this.ram = ram;
        this.disks = disks;
        this.networkInterfaces = networkInterfaces;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Resources() {
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