package com.example.restoTable.controller;

import com.example.restoTable.model.OrderItem;
import com.example.restoTable.model.Table;
import com.example.restoTable.model.TableReserv;
import com.example.restoTable.repository.TableReservRepository;
import com.example.restoTable.repository.TableRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TableReservController {
    private final TableReservRepository repo;

    TableReservController(TableReservRepository repo, TableRepository tableRepo) {
        this.repo = repo;
    }

    @GetMapping("/table-reservs")
    ResponseEntity<List<TableReserv>> getAllReservs() {
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<List<TableReserv>>(repo.findAll(), respHeaders, HttpStatus.OK);
    }

    @GetMapping("/table-reservs/{tableNumber}")
    ResponseEntity<TableReserv> getReservs(@PathVariable Integer tableNumber) {
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<TableReserv>(repo.findReservbyTableNumber(tableNumber), respHeaders, HttpStatus.OK);
    }

    @PostMapping("/table-reservs")
    ResponseEntity<TableReserv> newTableReserv(@RequestBody TableReserv newReserv) {
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.APPLICATION_JSON);

        if(newReserv.getOccupants() < 1) {
            return new ResponseEntity<TableReserv>(null, respHeaders, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<TableReserv>(repo.save(newReserv), respHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/table-reservs")
    ResponseEntity<TableReserv> putTableReserv(@RequestBody TableReserv newReserv, @PathVariable Integer tableNumber) {
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.APPLICATION_JSON);

        Optional<TableReserv> t = repo.findById(tableNumber);

        if(t.isPresent()) {
            List<OrderItem> newOrders = t.get().getOrders();
            newOrders.addAll(newReserv.getOrders());
            t.get().setOrders(newOrders);

            return new ResponseEntity<TableReserv>(repo.save(t.get()), respHeaders, HttpStatus.OK);
        } else {
            return new ResponseEntity<TableReserv>(null, respHeaders, HttpStatus.OK);
        }
    }
    
    @DeleteMapping("/table-reservs/{tableNumber}")
    void deleteTableReserv(@PathVariable Integer tableNumber) {
        repo.deleteById(tableNumber);
    }
}
