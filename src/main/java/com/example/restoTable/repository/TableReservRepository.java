package com.example.restoTable.repository;

import com.example.restoTable.model.TableReserv;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TableReservRepository extends MongoRepository<TableReserv, Integer> {
    @Query("{tableNumber: '?0'}")
    TableReserv findReservbyTableNumber(Integer tableNumber);
}
