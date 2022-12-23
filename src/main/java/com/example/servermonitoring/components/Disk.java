package com.example.servermonitoring.components;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Disk {
    private String name;
    private String totalSpace;
    private String usedSpace;
}