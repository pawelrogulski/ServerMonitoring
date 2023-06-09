package com.example.servermonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServerMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerMonitoringApplication.class, args);
    }

}
