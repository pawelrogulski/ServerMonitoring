package com.example.servermonitoring.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "servers")
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String url;

    public Server() {
    }

    public Server(String url) {
        this.url = url;
    }

    public Server(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
