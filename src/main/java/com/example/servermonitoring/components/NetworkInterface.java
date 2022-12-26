package com.example.servermonitoring.components;

public class NetworkInterface {
    private String name;
    private String status;
    private String downloadSpeed;
    private String uploadSpeed;

    public NetworkInterface(String name, String status, String downloadSpeed, String uploadSpeed) {
        this.name = name;
        this.status = status;
        this.downloadSpeed = downloadSpeed;
        this.uploadSpeed = uploadSpeed;
    }

    public NetworkInterface() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDownloadSpeed() {
        return downloadSpeed;
    }

    public void setDownloadSpeed(String downloadSpeed) {
        this.downloadSpeed = downloadSpeed;
    }

    public String getUploadSpeed() {
        return uploadSpeed;
    }

    public void setUploadSpeed(String uploadSpeed) {
        this.uploadSpeed = uploadSpeed;
    }
}