package com.example.servermonitoring.componentsService;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandsTest {
    Commands commands = new Commands();
    @Test
    void cpuNameShouldNotBeEmpty() {
        Assert.assertNotEquals("",commands.cpuName());
    }

    @Test
    void cpuUsageShoundBeBetween0And1() {
        String output = commands.cpuUsage();
        float beforePoint = Float.parseFloat(output.substring(0,output.indexOf(',')));
        float afterPoint = Float.parseFloat(output.substring(output.indexOf(',')+1,output.length()));
        Float usage = beforePoint+afterPoint/100;
        Assertions.assertTrue(usage>=0 && usage<=100);
    }

    @Test
    void checkIfThereIsNotMoreOrEqual60Minutes() {
        String output = commands.uptime();
        int minutes;
        if(output.length()==2){//system uruchomiony do 9 minut
            minutes = Integer.parseInt(output.substring(0,1));
        }
        else if (output.length()==3) {//system uruchomiony do 59 minut
            minutes = Integer.parseInt(output.substring(0,2));
        }
        else{//system uruchomiony ponad godzinę
            minutes = Integer.parseInt(output.substring(output.indexOf('m')-2,output.indexOf('m')));
        }
        Assertions.assertTrue(minutes<60);
    }

    @Test
    void hostnameShouldNotBeEmpty() {
        assertNotEquals("",commands.hostname());
    }

    @Test
    void networkInterfaceNameShouldNotBeEmpty() {
        if(commands.networkInterfaces().size()>0){
            assertNotEquals("",commands.networkInterfaces().get(0).getName());
        }
    }
    @Test
    void networkInterfaceStatusShouldNotBeEmpty() {
        if(commands.networkInterfaces().size()>0){
            assertNotEquals("",commands.networkInterfaces().get(0).getStatus());
        }
    }
    @Test
    void networkInterfaceDownloadShouldNotBeNull() {
        if(commands.networkInterfaces().size()>0){
            assertNotNull(commands.networkInterfaces().get(0).getDownloadSpeed());
        }
    }
    @Test
    void networkInterfaceUploadShouldNotBeNull() {
        if(commands.networkInterfaces().size()>0){
            assertNotNull(commands.networkInterfaces().get(0).getUploadSpeed());
        }
    }

    @Test
    void diskNameShouldNotBeEmpty() {
        if(commands.disks().size()>0){
            assertNotEquals("",commands.disks().get(0).getName());
        }
    }
    @Test
    void diskSizeShouldNotBeEmpty() {
        if(commands.disks().size()>0){
            assertNotEquals("",commands.disks().get(0).getTotalSpace());
        }
    }
    @Test
    void diskUsedSpaceShouldNotBeEmpty() {
        if(commands.disks().size()>0){
            assertNotEquals("",commands.disks().get(0).getUsedSpace());
        }
    }
}