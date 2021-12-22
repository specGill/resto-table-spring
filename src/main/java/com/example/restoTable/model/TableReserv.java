package com.example.restoTable.model;

import com.mongodb.internal.connection.Time;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("TableReservs")
public class TableReserv {
    @Id
    private Integer tableNumber;

    private String reservName;
    private int occupants;
    private List<OrderItem> orders;
    private Date time;

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getReservName() {
        return reservName;
    }

    public void setReservName(String reservName) {
        this.reservName = reservName;
    }

    public int getOccupants() {
        return occupants;
    }

    public void setOccupants(int occupants) {
        this.occupants = occupants;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public TableReserv(int tableNumber, String reservName, int occupants, List<OrderItem> orders, Date time) {
        super();
        this.tableNumber = tableNumber;
        this.reservName = reservName;
        this.orders = orders;
        this.time = time;
    }
}

