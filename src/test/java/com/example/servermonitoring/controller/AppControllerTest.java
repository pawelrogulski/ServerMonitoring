package com.example.servermonitoring.controller;

import com.example.servermonitoring.componentsService.Commands;
import com.example.servermonitoring.domain.Server;
import com.example.servermonitoring.monitoringService.AppService;
import com.example.servermonitoring.repository.ServerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AppControllerTest {
    @Test
    void showHardwareUsageShouldNotReturnNull() {
        Commands commands = new Commands();
        AppController appController = new AppController(commands,null);//nie potrzebujemy AppService dla tej metody
        assertNotNull(appController.showHardwareUsage());
    }
    @Test
    void showHardwareUsageHostnameShouldNotReturnNull() {
        Commands commands = new Commands();
        AppController appController = new AppController(commands,null);//nie potrzebujemy AppService dla tej metody
        assertNotNull(appController.showHardwareUsage().getHostname());
    }
    @Test
    void showHardwareUsageUptimeShouldNotReturnNull() {
        Commands commands = new Commands();
        AppController appController = new AppController(commands,null);//nie potrzebujemy AppService dla tej metody
        assertNotNull(appController.showHardwareUsage().getUptime());
    }
    @Test
    void showHardwareUsageCpuShouldNotReturnNull() {
        Commands commands = new Commands();
        AppController appController = new AppController(commands,null);//nie potrzebujemy AppService dla tej metody
        assertNotNull(appController.showHardwareUsage().getCpu());
    }
    @Test
    void showHardwareUsageRamShouldNotReturnNull() {
        Commands commands = new Commands();
        AppController appController = new AppController(commands,null);//nie potrzebujemy AppService dla tej metody
        assertNotNull(appController.showHardwareUsage().getRam());
    }
    @Test
    void showHardwareUsageDisksShouldNotReturnNull() {
        Commands commands = new Commands();
        AppController appController = new AppController(commands,null);//nie potrzebujemy AppService dla tej metody
        assertNotNull(appController.showHardwareUsage().getDisks());
    }
    @Test
    void showHardwareUsageNetworkInterfacesShouldNotReturnNull() {
        Commands commands = new Commands();
        AppController appController = new AppController(commands,null);//nie potrzebujemy AppService dla tej metody
        assertNotNull(appController.showHardwareUsage().getNetworkInterfaces());
    }

    @Test
    void showAllServersWithEmptyDatabaseShouldReturnEmptyList() {
        Commands commands = new Commands();
        AppService appService = mock(AppService.class);
        AppController appController = new AppController(commands,appService);
        when(appService.getAllServers()).thenReturn(emptyDB());
        Assert.assertEquals(new ArrayList<>(),appController.showAllServers());
    }

    @Test
    void addServer() {
        Commands commands = new Commands();
        AppService appService = mock(AppService.class);
        AppController appController = new AppController(commands,appService);
        when(appService.getAllServers()).thenReturn(mockDB());
        given(appController.addServer(Mockito.any(String.class))).willReturn(new Server(2,"url2"));
        Server server = appController.addServer("url2");
        Assert.assertEquals("url2",server.getUrl());
    }

    private List<String> emptyDB(){
        return new ArrayList<>();
    }
    private List<String> mockDB(){
        List<String> servers = new ArrayList<>();
        servers.add("url1");
        return servers;
    }
}