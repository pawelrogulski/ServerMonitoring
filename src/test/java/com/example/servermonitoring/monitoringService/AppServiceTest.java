package com.example.servermonitoring.monitoringService;

import com.example.servermonitoring.domain.Server;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //@RunWith już nie jest wspierane z Junit5
class AppServiceTest {

    @Mock
    AppService appService;
    @Test
    void shouldReturnAllServers() {
        when(appService.getAllServers()).thenReturn(mockDB());
        List<String> serversURLs = new ArrayList<>();
        serversURLs.add("url1");
        serversURLs.add("url2");
        Assertions.assertEquals(appService.getAllServers(),serversURLs);
    }

    @Test
    void checkValueOfServer() {
        given(appService.saveServer(Mockito.any(String.class))).willReturn(new Server("url3"));
        Server server = appService.saveServer("url3");
        Assert.assertEquals(server.getUrl(),"url3");
    }

    private List<String> mockDB(){
        List<String> serversURLs = new ArrayList<>();
        serversURLs.add("url1");
        serversURLs.add("url2");
        return serversURLs;
    }
}