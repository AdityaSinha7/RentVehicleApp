package com.example.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 *
 */
public enum VehicleType {
    CAR, BIKE, VAN;

    public static Optional<VehicleType> fromName(String name) {
        return Arrays.stream(VehicleType.values())
                .filter(type -> type.name().equalsIgnoreCase(name)).findFirst();
    }
}
