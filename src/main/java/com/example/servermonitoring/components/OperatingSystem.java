package com.example.servermonitoring.components;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class OperatingSystem {
    private String hostname;
    private String uptime;
    private Cpu cpu;
    private Ram ram;
    private List<Disk> disks;
    private List<NetworkInterface> networkInterfaces;
}