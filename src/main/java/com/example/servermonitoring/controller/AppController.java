package com.example.servermonitoring.controller;

import com.example.servermonitoring.components.OperatingSystem;
import com.example.servermonitoring.componentsService.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/hardware_usage")
public class AppController {
    private final Commands commands;

    @Autowired
    public AppController(Commands commands){
        this.commands=commands;
    }

    @GetMapping
    public OperatingSystem showHardwareUsage(){
        return commands.systemInfo();
    }
}