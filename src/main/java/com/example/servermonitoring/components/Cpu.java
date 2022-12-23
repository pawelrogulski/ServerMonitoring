package com.example.servermonitoring.components;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Cpu {
    private String name;
    private String usage;
}