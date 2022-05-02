package com.example.entity;

import java.util.UUID;

/**
 *
 */
public class Reservation {

    private UUID id;
    private String vehicleId;
    private int startTime;
    private int endTime;
    private int cost;

    public Reservation(String vehicleId, int startTime, int endTime) {
        this.id = UUID.randomUUID();
        this.vehicleId = vehicleId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
