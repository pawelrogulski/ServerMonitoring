package com.example.servermonitoring.controller;

import com.example.servermonitoring.components.OperatingSystem;
import com.example.servermonitoring.componentsService.Commands;
import com.example.servermonitoring.domain.Server;
import com.example.servermonitoring.monitoringService.AppService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class AppController {
    private final Commands commands;
    private final AppService appService;

    @GetMapping(path = "/hardware_usage")
    public OperatingSystem showHardwareUsage(){return commands.systemInfo();}

    @GetMapping("/servers")
    public List<OperatingSystem> showAllServers(){
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> serverURLs = appService.getAllServers();
        List<OperatingSystem> servers = new ArrayList<>();
        for(String url : serverURLs){
            try{
                RestTemplate restTemplate = new RestTemplate();
                String server = restTemplate.getForObject(url, String.class);
                OperatingSystem operatingSystem = objectMapper.readValue(server, OperatingSystem.class);
                servers.add(operatingSystem);
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return servers;
    }

    @PostMapping("/servers")
    public Server addServer(@RequestBody String serverURL){
        return appService.saveServer(serverURL);
    }
}