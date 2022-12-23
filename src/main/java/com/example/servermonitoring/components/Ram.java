package com.example.servermonitoring.components;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Ram {
    private String totalMemory;
    private String inUse;
}