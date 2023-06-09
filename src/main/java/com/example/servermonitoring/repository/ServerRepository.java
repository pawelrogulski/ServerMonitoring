package com.example.servermonitoring.repository;

import com.example.servermonitoring.domain.Server;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends MongoRepository<Server,Integer> {
}
