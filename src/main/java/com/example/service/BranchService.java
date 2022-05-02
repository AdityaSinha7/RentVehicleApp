package com.example.service;

import com.example.enums.VehicleType;
import com.example.exception.ApplicationException;
import com.example.repository.BranchRepository;
import com.example.repository.ReservationRepository;
import com.example.repository.VehicleRepository;
import com.example.utility.CoreUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class BranchService {

    private static final BranchService instance = new BranchService();
    private static final VehicleService vehicleService = VehicleService.getInstance();
    private static final BranchRepository branchRepository = BranchRepository.getInstance();
    private static final ReservationRepository reservationRepository = ReservationRepository.getInstance();
    private static final VehicleRepository vehicleRepository = VehicleRepository.getInstance();

    private BranchService() {
    }

    public static BranchService getInstance() {
        return instance;
    }

    public boolean addBranch(String branchName, String vehicleTypes) {
        if (CoreUtils.isStringEmpty(branchName)) {
            throw new ApplicationException(ApplicationException.Code.INVALID_INPUT, "Branch name cannot be empty");
        }
        List<VehicleType> vehicleTypeList = vehicleService.getVehicleTypesByName(vehicleTypes);
        if (vehicleTypeList != null && !vehicleTypeList.isEmpty()) {
            return branchRepository.addNewBranch(branchName, vehicleTypeList);
        }
        return false;
    }

    public List<String> getAvailableVehicles(String branchName, String startTime, String endTime) {
        if (CoreUtils.isStringEmpty(branchName) || CoreUtils.isStringEmpty(startTime) || CoreUtils.isStringEmpty(endTime)) {
            throw new ApplicationException(ApplicationException.Code.INVALID_INPUT, "Invalid input");
        }

        List<String> vehicleIds = branchRepository.getVehicleIds(branchName);

        vehicleIds = vehicleIds.stream()
                .filter(id -> reservationRepository.checkAvailable(id, Integer.parseInt(startTime), Integer.parseInt(endTime)))
                .collect(Collectors.toList());
        return vehicleIds;
    }

    public boolean addVehicle(String branchName, String vehicleType, String vehicleId, String price) {
        if (CoreUtils.isStringEmpty(branchName) || CoreUtils.isStringEmpty(vehicleType)
                || CoreUtils.isStringEmpty(vehicleId) || CoreUtils.isStringEmpty(price)) {
            throw new ApplicationException(ApplicationException.Code.INVALID_INPUT, "Invalid input");
        }
        try {
            VehicleType type = vehicleService.getVehicleTypeByName(vehicleType);
            boolean isAdded = vehicleRepository.addNewVehicle(branchName, vehicleId, Integer.parseInt(price), type);
            if (isAdded) {
                branchRepository.addVehicleInBranch(branchName, vehicleId);
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }
}
