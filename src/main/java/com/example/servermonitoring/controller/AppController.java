package com.example.servermonitoring.controller;

import com.example.servermonitoring.components.OperatingSystem;
import com.example.servermonitoring.componentsService.Commands;
import com.example.servermonitoring.monitoringService.AppService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class AppController {
    private final Commands commands;
    private final AppService appService;

    @GetMapping(path = "/hardware_usage")
    public OperatingSystem showHardwareUsage(){
        return commands.systemInfo();
    }

    @GetMapping("servers")
    public List<OperatingSystem> showAllServers(){
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> serverURIs = appService.getAllServers();
        List<OperatingSystem> servers = new ArrayList<>();
        for(String uri : serverURIs){
            try{
                RestTemplate restTemplate = new RestTemplate();
                String server = restTemplate.getForObject(uri, String.class);
//                OperatingSystem operatingSystem = objectMapper.readValue(server, OperatingSystem.class);
                OperatingSystem operatingSystem = appService.convertToOS(server);
                servers.add(operatingSystem);
            }catch (Exception e){}
        }
        return servers;
    }
}