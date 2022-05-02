package com.example.entity;

import com.example.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Branch {

    private String name;
    private List<VehicleType> vehicleTypes;
    private List<Vehicle> vehicles;

    public Branch(String name) {
        this.name = name;
        this.vehicles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VehicleType> getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(List<VehicleType> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
