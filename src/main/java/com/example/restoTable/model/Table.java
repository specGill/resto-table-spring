package com.example.restoTable.model;

import org.springframework.data.annotation.Id;

public class Table {
    @Id
    private Integer tableNumber;
    private int capacity;
    private boolean isOccupied;

    public Table(Integer tableNumber, int capacity, boolean isOccupied) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.isOccupied = isOccupied;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
