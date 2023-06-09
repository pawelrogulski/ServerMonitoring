package com.example.servermonitoring.repository;

import com.example.servermonitoring.domain.Resources;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourcesRepository extends MongoRepository<Resources,Integer> {
}