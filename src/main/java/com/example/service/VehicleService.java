package com.example.service;

import com.example.enums.VehicleType;
import com.example.exception.ApplicationException;
import com.example.utility.CoreUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
public class VehicleService {

    private static final VehicleService instance = new VehicleService();

    private VehicleService() {
    }

    public static VehicleService getInstance() {
        return instance;
    }

    public List<VehicleType> getVehicleTypesByName(String names) throws ApplicationException {
        List<String> vehicleTypeList = CoreUtils.split(names, ",");
        if (vehicleTypeList != null && !vehicleTypeList.isEmpty()) {
            List<VehicleType> response = new ArrayList<>();
            for (String type : vehicleTypeList) {
                Optional<VehicleType> vehicleType = VehicleType.fromName(type);
                if (vehicleType.isPresent()) {
                    response.add(vehicleType.get());
                } else {
                    throw new ApplicationException(ApplicationException.Code.INVALID_INPUT, "Unknown vehicle type - " + type);
                }
            }
            response = response.stream().distinct().collect(Collectors.toList());
            return response;
        }
        return null;
    }

    public VehicleType getVehicleTypeByName(String name) {
        if (!CoreUtils.isStringEmpty(name)) {
            Optional<VehicleType> type = VehicleType.fromName(name);
            if (type.isPresent()) {
                return type.get();
            }
        }
        throw new ApplicationException(ApplicationException.Code.INVALID_INPUT, "Unknown vehicle type - " + name);
    }
}
