package com.example.restoTable.repository;

import com.example.restoTable.model.Table;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TableRepository extends MongoRepository<Table, Integer> {
    @Query("{tableNumber: '?0'}")
    Table findTablebyNumber(Integer tableNumber);
}
