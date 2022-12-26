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

    public OperatingSystem convertToOS(String json){
        String hostname = json.substring(json.indexOf("hostname")+11, json.indexOf("uptime")-4);
        String uptime = json.substring(json.indexOf("uptime")+9, json.indexOf("cpu")-3);
        String cpu = json.substring(json.indexOf("cpu")+6, json.indexOf("ram")-4);
        String cpuName = cpu.substring(cpu.indexOf("name")+7,cpu.indexOf("usage")-3);
        String cpuUsage = cpu.substring(cpu.indexOf("usage")+8,cpu.length());
        Cpu cpuObject = new Cpu(cpuName,cpuUsage);
        String ram = json.substring(json.indexOf("ram")+6, json.indexOf("disks")-4);
        String ramTotalMemory = ram.substring(ram.indexOf("totalMemory")+14, ram.indexOf("inUse")-3);
        String ramInUse = ram.substring(ram.indexOf("inUse")+8, ram.length());
        Ram ramObject = new Ram(ramTotalMemory,ramInUse);
        String disks = json.substring(json.indexOf("disks")+7, json.indexOf("networkInterfaces")-4);
        List<Disk> disksList= new ArrayList<>();
        if (!disks.equals("")){
        }
        List<NetworkInterface> networkInterfacesList = new ArrayList<>();
        String networkInterfaces = json.substring(json.indexOf("networkInterfaces")+20, json.length()-2);
        if (!networkInterfaces.equals("")){
        }

        return new OperatingSystem(hostname,uptime,cpuObject,ramObject,disksList,networkInterfacesList);
    }
}
