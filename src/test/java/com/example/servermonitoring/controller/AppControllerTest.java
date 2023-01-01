package com.example.servermonitoring.controller;

import com.example.servermonitoring.componentsService.Commands;
import com.example.servermonitoring.controller.AppController;
import com.example.servermonitoring.domain.Server;
import com.example.servermonitoring.monitoringService.AppService;
import com.example.servermonitoring.repository.ServerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class) //@RunWith już nie jest wspierane z Junit5
class AppControllerTest {
    @Spy
    Commands commands;
    @Mock
    AppService appService;
    @InjectMocks
    AppController appController;
    @Test
    void showHardwareUsageShouldNotReturnNull() {
        assertNotNull(appController.showHardwareUsage());
    }
    @Test
    void showHardwareUsageHostnameShouldNotReturnNull() {
        assertNotNull(appController.showHardwareUsage().getHostname());
    }
    @Test
    void showHardwareUsageUptimeShouldNotReturnNull() {
        assertNotNull(appController.showHardwareUsage().getUptime());
    }
    @Test
    void showHardwareUsageCpuShouldNotReturnNull() {
        assertNotNull(appController.showHardwareUsage().getCpu());
    }
    @Test
    void showHardwareUsageRamShouldNotReturnNull() {
        assertNotNull(appController.showHardwareUsage().getRam());
    }
    @Test
    void showHardwareUsageDisksShouldNotReturnNull() {
        assertNotNull(appController.showHardwareUsage().getDisks());
    }
    @Test
    void showHardwareUsageNetworkInterfacesShouldNotReturnNull() {
        assertNotNull(appController.showHardwareUsage().getNetworkInterfaces());
    }

    @Test
    void showAllServersWithEmptyDatabaseShouldReturnEmptyList() {
        when(appService.getAllServers()).thenReturn(emptyDB());
        Assert.assertEquals(new ArrayList<>(),appController.showAllServers());
    }

    @Test
    void addServer() {
        given(appController.addServer(Mockito.any(String.class))).willReturn(new Server(1,"url1"));
        Server server = appController.addServer("url1");
        Assert.assertEquals("url1",server.getUrl());
    }

    private List<String> emptyDB(){
        return new ArrayList<>();
    }
}