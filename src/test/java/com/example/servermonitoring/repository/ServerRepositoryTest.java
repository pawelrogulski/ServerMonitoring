package com.example.servermonitoring.repository;

import com.example.servermonitoring.domain.Server;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServerRepositoryTest {
    @Test
    void checkIfListSizeIsCorrect(){
       ServerRepository serverRepositoryTest = mock(ServerRepository.class);
       when(serverRepositoryTest.findAll()).thenReturn(mockDB());
       Assert.assertThat(serverRepositoryTest.findAll(), Matchers.hasSize(2));
    }

    private List<Server> mockDB(){
        List<Server> servers= new ArrayList<>();
        servers.add(new Server(1,"url1"));
        servers.add(new Server(2,"url2"));
        return servers;
    }

}