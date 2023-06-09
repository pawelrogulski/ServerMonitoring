package com.example.servermonitoring.componentsService;

import com.example.servermonitoring.domain.NetworkInterface;

import java.math.BigDecimal;

import static com.example.servermonitoring.componentsService.Commands.getCommandOutput;

public class MultithreadSpeedTest extends Thread {
    String trafficDirection; //tx (transmitted) - ruch wychodzący, lub rx (received) - ruch przychodzący
    NetworkInterface networkInterface; // nazwa interfejsu sieciowego

    MultithreadSpeedTest(NetworkInterface networkInterface, String trafficDirection) {
        this.networkInterface = networkInterface;
        this.trafficDirection = trafficDirection;
    }

    public void run() {
        if(!(trafficDirection.equals("rx")||trafficDirection.equals("tx"))){
            System.out.println("trafficDirection must by equals to 'rx' or 'tx' values");
            return;
        }
        //pomiar nr 1
        float output1 = Float.parseFloat(getCommandOutput("cat /sys/class/net/" + networkInterface.getName() + "/statistics/" + trafficDirection + "_bytes").get(0));
        try {
            Thread.sleep(1000); //odczekanie sekundy pomiędzy pomiarami
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
            Thread.currentThread().interrupt();
        }
        //pomiar nr 2
        float output2 = Float.parseFloat(getCommandOutput("cat /sys/class/net/" + networkInterface.getName() + "/statistics/" + trafficDirection + "_bytes").get(0));
        //różnica między pomiarami wyrażona w megabajtach
        String outputsDifference = new BigDecimal((output2 - output1) / 1024 / 1024 * 8 + "0").toPlainString();
        outputsDifference = outputsDifference.substring(0, outputsDifference.indexOf(".") + 3) + " Mb";
        if(trafficDirection.equals("tx")){
            networkInterface.setUploadSpeed(outputsDifference);
        }
        if(trafficDirection.equals("rx")){
            networkInterface.setDownloadSpeed(outputsDifference);
        }
    }
}