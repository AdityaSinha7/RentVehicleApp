package com.example.repository;

import com.example.dao.impl.BranchDao;
import com.example.dao.impl.VehicleDao;
import com.example.entity.Branch;
import com.example.entity.Vehicle;
import com.example.enums.VehicleType;
import com.example.exception.ApplicationException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
public class BranchRepository {

    private static final BranchDao branchDao = BranchDao.getInstance();
    private static final BranchRepository instance = new BranchRepository();

    private BranchRepository() {
    }

    public static BranchRepository getInstance() {
        return instance;
    }

    public List<String> getVehicleIds(String branchName) {
        Optional<Branch> branch = branchDao.findById(branchName);
        if (branch.isPresent()) {
            List<String> vehicleIds = branch.get().getVehicles().stream().map(Vehicle::getId).collect(Collectors.toList());
            return vehicleIds;
        }
        return Collections.emptyList();
    }

    public List<String> getVehicleIdsByType(String branchName, VehicleType type) {
        Optional<Branch> branch = branchDao.findById(branchName);
        if (branch.isPresent()) {
            List<String> vehicleIds = branch.get().getVehicles().stream()
                    .filter(vh -> vh.getType().equals(type)).map(Vehicle::getId).collect(Collectors.toList());
            return vehicleIds;
        }
        return Collections.emptyList();
    }

    public boolean addNewBranch(String branchName, List<VehicleType> vehicleTypeList) throws ApplicationException {
        Branch branch = buildBranch(branchName, vehicleTypeList);
        branchDao.save(branch);
        return true;
    }

    public boolean addVehicleInBranch(String branchId, String vehicleId) {
        VehicleDao vehicleDao = VehicleDao.getInstance();
        Optional<Vehicle> vehicle = vehicleDao.findById(vehicleId);
        Optional<Branch> branch = branchDao.findById(branchId);
        if (vehicle.isPresent() && branch.isPresent()) {
            List<Vehicle> existingVehicles = branch.get().getVehicles();
            existingVehicles.add(vehicle.get());
            existingVehicles = existingVehicles.stream().distinct().collect(Collectors.toList());
            branch.get().setVehicles(existingVehicles);
            branchDao.update(branch.get());
            return true;
        }
        return false;
    }

    private Branch buildBranch(String branchName, List<VehicleType> vehicleTypeList) {
        Branch branch = new Branch(branchName);
        branch.setVehicleTypes(vehicleTypeList);
        return branch;
    }
}
