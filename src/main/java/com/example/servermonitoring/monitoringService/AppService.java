package com.example.servermonitoring.monitoringService;

import com.example.servermonitoring.components.*;
import com.example.servermonitoring.domain.Server;
import com.example.servermonitoring.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AppService {
    private final ServerRepository serverRepository;

    public List<String> getAllServers(){
        List<Server> servers = serverRepository.findAll();
        return servers
                .stream()
                .map(Server::getUrl)
                .collect(Collectors.toList());
    }
}
