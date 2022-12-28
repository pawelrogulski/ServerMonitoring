package com.example.servermonitoring.monitoringService;

import com.example.servermonitoring.domain.Server;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AppServiceTest {

    @Test
    void shouldReturnAllServers() {
        AppService appServiceTest = mock(AppService.class);
        when(appServiceTest.getAllServers()).thenReturn(mockDB());
        List<String> serversURLs = new ArrayList<>();
        serversURLs.add("url1");
        serversURLs.add("url2");
        Assertions.assertEquals(appServiceTest.getAllServers(),serversURLs);
    }

    @Test
    void checkValueOfServer() {
        AppService appServiceTest = mock(AppService.class);
        given(appServiceTest.saveServer(Mockito.any(String.class))).willReturn(new Server("url3"));
        Server server = appServiceTest.saveServer("url3");
        Assert.assertEquals(server.getUrl(),"url3");
    }

    private List<String> mockDB(){
        List<String> serversURLs = new ArrayList<>();
        serversURLs.add("url1");
        serversURLs.add("url2");
        return serversURLs;
    }
}