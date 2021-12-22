package com.example.restoTable.controller;

import com.example.restoTable.model.Table;
import com.example.restoTable.repository.TableRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TableController {
    private final TableRepository repo;

    TableController(TableRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/tables")
    ResponseEntity<List<Table>> getAllTables() {
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<List<Table>>(repo.findAll(), respHeaders, HttpStatus.OK);
    }

    @GetMapping("/tables/{tableNumber}")
    ResponseEntity<Table> getTable(@PathVariable Integer tableNumber) {
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(repo.findTablebyNumber(tableNumber), respHeaders, HttpStatus.OK);
    }

    @PostMapping("/tables")
    ResponseEntity<Table> newTable(@RequestBody Table newTable) {
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.APPLICATION_JSON);

        if(newTable.getCapacity() < 1 )
            return new ResponseEntity<>(null, respHeaders, HttpStatus.BAD_REQUEST);

        repo.save(newTable);

        return new ResponseEntity<Table>(newTable, respHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/tables/{tableNumber}")
    ResponseEntity<Table> putTable(@RequestBody Table newTable, @PathVariable Integer tableNumber) {
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.APPLICATION_JSON);

        Table oldTable = repo.findTablebyNumber(tableNumber);

        if(oldTable != null) {
            oldTable.setCapacity(newTable.getCapacity());
            oldTable.setOccupied(newTable.isOccupied());

            return new ResponseEntity<Table>(repo.save(oldTable), respHeaders, HttpStatus.OK);
        } else {
            return new ResponseEntity<Table>(null, respHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tables/{tableNumber}")
    void deleteTable(@PathVariable Integer tableNumber) {
        repo.deleteById(tableNumber);
    }
}
