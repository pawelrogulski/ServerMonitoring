package com.example.servermonitoring.monitoringService;

import com.example.servermonitoring.componentsService.Commands;
import com.example.servermonitoring.domain.Resources;
import com.example.servermonitoring.domain.Server;
import com.example.servermonitoring.repository.ResourcesRepository;
import com.example.servermonitoring.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AppService {
    private final ServerRepository serverRepository;
    private final ResourcesRepository resourcesRepository;
    private final Commands commands;

    public List<String> getAllServers(){
        List<Server> servers = serverRepository.findAll();
        return servers
                .stream()
                .map(Server::getUrl)
                .collect(Collectors.toList());
    }

    public Server saveServer(String serverURL){
        Server server = new Server();
        server.setUrl(serverURL);
        return serverRepository.save(server);
    }

    public void saveServerResources(Server server,Resources resources){
        resourcesRepository.save(resources);
    }

    @Scheduled(fixedRate=5000) //5 sekund
    public void saveToDatabaseInSchedule(){
        resourcesRepository.save(commands.systemInfo());
    }
}
