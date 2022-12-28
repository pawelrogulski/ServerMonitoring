package com.example.servermonitoring.repository;

import com.example.servermonitoring.domain.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends JpaRepository<Server,Integer> {
}
