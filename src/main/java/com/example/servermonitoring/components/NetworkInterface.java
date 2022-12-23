package com.example.servermonitoring.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NetworkInterface {
    private String name;
    private String status;
    private String downloadSpeed;
    private String uploadSpeed;
}