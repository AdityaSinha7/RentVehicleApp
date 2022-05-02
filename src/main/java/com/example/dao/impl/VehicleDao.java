package com.example.dao.impl;

import com.example.dao.Dao;
import com.example.entity.Vehicle;
import com.example.exception.ApplicationException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 */
public class VehicleDao implements Dao<Vehicle, String> {

    private static final Map<String, Vehicle> vehicleMap = new HashMap<>();
    private static final VehicleDao instance = new VehicleDao();

    private VehicleDao() {
    }

    public static VehicleDao getInstance() {
        return instance;
    }


    @Override
    public Optional<Vehicle> findById(String id) {
        return vehicleMap.containsKey(id) ? Optional.of(vehicleMap.get(id)) : Optional.empty();
    }

    @Override
    public Vehicle save(Vehicle entity) {
        if (vehicleMap.containsKey(entity.getId())) {
            throw new ApplicationException(ApplicationException.Code.CONFLICT, "This Vehicle id already exist in the database.");
        }
        return vehicleMap.put(entity.getId(), entity);
    }

    @Override
    public Vehicle update(Vehicle entity) {
        return vehicleMap.put(entity.getId(), entity);
    }

    @Override
    public Collection<Vehicle> getAll() {
        return vehicleMap.values();
    }
}
