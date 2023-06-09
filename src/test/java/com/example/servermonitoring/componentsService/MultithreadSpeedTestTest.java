package com.example.servermonitoring.componentsService;

import com.example.servermonitoring.domain.NetworkInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultithreadSpeedTestTest {

    @Test
    void trafficDirectionOtherThatRxOrTxShouldNotChangeDownloadSpeed() throws InterruptedException {
        NetworkInterface networkInterface = new NetworkInterface("lo","loopback",null,null);
        MultithreadSpeedTest multithreadSpeedTest = new MultithreadSpeedTest(networkInterface,"rtx");
        multithreadSpeedTest.run();
        multithreadSpeedTest.join();
        assertNull(networkInterface.getDownloadSpeed());
    }
    @Test
    void trafficDirectionOtherThatRxOrTxShouldNotChangeUploadSpeed() throws InterruptedException {
        NetworkInterface networkInterface = new NetworkInterface("lo","loopback",null,null);
        MultithreadSpeedTest multithreadSpeedTest = new MultithreadSpeedTest(networkInterface,"rtx");
        multithreadSpeedTest.run();
        multithreadSpeedTest.join();
        assertNull(networkInterface.getUploadSpeed());
    }
}