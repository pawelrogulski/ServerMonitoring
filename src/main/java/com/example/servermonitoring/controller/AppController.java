package com.example.servermonitoring.controller;

import com.example.servermonitoring.components.OperatingSystem;
import com.example.servermonitoring.componentsService.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class AppController {
    private final Commands commands;

    @Autowired
    public AppController(Commands commands){
        this.commands=commands;
    }

    @GetMapping(path = "/hardware_usage")
    public OperatingSystem showHardwareUsage(){
        return commands.systemInfo();
    }

    @GetMapping
    public List<OperatingSystem> showAllServers(){
        return null;
    }
}