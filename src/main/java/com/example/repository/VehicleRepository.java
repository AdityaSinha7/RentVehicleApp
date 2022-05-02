package com.example.repository;

import com.example.dao.impl.VehicleDao;
import com.example.entity.Reservation;
import com.example.entity.Vehicle;
import com.example.enums.VehicleType;
import com.example.exception.ApplicationException;

import java.util.*;

/**
 *
 */
public class VehicleRepository {

    private static final VehicleDao vehicleDao = VehicleDao.getInstance();
    private static final VehicleRepository instance = new VehicleRepository();

    private VehicleRepository() {
    }

    public static VehicleRepository getInstance() {
        return instance;
    }

    public List<Vehicle> getAllVehiclesSortedBy(List<String> vehicleIds, Comparator<Vehicle> comparator) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (String id : vehicleIds) {
            Optional<Vehicle> vehicle = vehicleDao.findById(id);
            if (vehicle.isPresent()) {
                vehicles.add(vehicle.get());
            }
        }
        Collections.sort(vehicles, comparator);
        return vehicles;
    }

    public boolean addNewVehicle(String branchName, String name, int price, VehicleType type) throws ApplicationException {
        Vehicle vehicle = buildVehicle(branchName, name, price, type, null);
        vehicleDao.save(vehicle);
        return true;
    }

    private Vehicle buildVehicle(String branchName, String name, int price, VehicleType type, List<Reservation> reservationList) {
        Vehicle vehicle = new Vehicle(name);
        vehicle.setBranchName(branchName);
        vehicle.setPrice(price);
        vehicle.setType(type);
        vehicle.setReservations(reservationList);
        return vehicle;
    }
}
