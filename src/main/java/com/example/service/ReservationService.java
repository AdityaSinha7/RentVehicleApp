package com.example.service;

import com.example.entity.Vehicle;
import com.example.enums.VehicleType;
import com.example.repository.BranchRepository;
import com.example.repository.ReservationRepository;
import com.example.repository.VehicleRepository;
import com.example.strategy.BookingChoiceStrategy;
import com.example.strategy.impl.DefaultStrategy;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
public class ReservationService {

    private static final ReservationService instance = new ReservationService();
    private static final PricingService pricingService = PricingService.getInstance();
    private static final BranchRepository branchRepository = BranchRepository.getInstance();
    private static final ReservationRepository reservationRepository = ReservationRepository.getInstance();
    private static final VehicleRepository vehicleRepository = VehicleRepository.getInstance();

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        return instance;
    }

    public int book(String branchName, String vehicleType, String startTime, String endTime) {
        return book(branchName, vehicleType, startTime, endTime, new DefaultStrategy());
    }

    public int book(String branchName, String vehicleType, String startTime, String endTime, BookingChoiceStrategy strategy) {
        int bookingCost = -1;
        Optional<VehicleType> type = VehicleType.fromName(vehicleType);
        int bookingStartTime = Integer.parseInt(startTime);
        int bookingEndTime = Integer.parseInt(endTime);
        if (type.isPresent() && bookingStartTime < bookingEndTime) {
            List<String> vehicleIds = branchRepository.getVehicleIdsByType(branchName, type.get());
            if (vehicleIds != null && !vehicleIds.isEmpty()) {
                vehicleIds = vehicleIds.stream()
                        .filter(id -> reservationRepository.checkAvailable(id, bookingStartTime, bookingEndTime))
                        .collect(Collectors.toList());
                List<Vehicle> vehicles = vehicleRepository.getAllVehiclesSortedBy(vehicleIds, strategy);

                if (vehicles != null && !vehicles.isEmpty()) {
                    int vehiclePerHourCost = vehicles.get(0).getPrice();
                    vehiclePerHourCost = pricingService.dynamicPriceHourBasis(branchName, vehiclePerHourCost);
                    bookingCost = vehiclePerHourCost * (bookingEndTime - bookingStartTime);
                    reservationRepository.makeReservation(vehicles.get(0), bookingStartTime, bookingEndTime, bookingCost);
                }
            }
        }
        return bookingCost;
    }
}
