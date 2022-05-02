package com.example.repository;

import com.example.dao.impl.ReservationDao;
import com.example.entity.Reservation;
import com.example.entity.Vehicle;
import com.example.utility.CoreUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class ReservationRepository {

    private static final ReservationDao reservationDao = ReservationDao.getInstance();
    private static final ReservationRepository instance = new ReservationRepository();

    private ReservationRepository() {
    }

    public static ReservationRepository getInstance() {
        return instance;
    }

    public boolean checkAvailable(String vehicleId, int startTime, int endTime) {
        Collection<Reservation> reservations = getAllReservations(vehicleId);
        for (Reservation reservation : reservations) {
            if (isReservationOverlapping(reservation, startTime, endTime)) {
                return false;
            }
        }
        return true;
    }

    public void makeReservation(Vehicle vehicle, int startTime, int endTime, int cost) {
        Reservation reservation = buildReservation(vehicle.getId(), startTime, endTime, cost);
        reservationDao.save(reservation);
    }

    public List<Reservation> getAllReservations(String vehicleId) {
        if (!CoreUtils.isStringEmpty(vehicleId)) {
            Collection<Reservation> reservations = reservationDao.getAll();
            return reservations.stream().filter(res -> res.getVehicleId().equals(vehicleId)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    boolean isReservationOverlapping(Reservation reservation, int startTime, int endTime) {
        if (reservation.getEndTime() > startTime && reservation.getEndTime() < endTime) {
            return true;
        } else if (reservation.getStartTime() > startTime && reservation.getStartTime() < endTime) {
            return true;
        } else if (reservation.getStartTime() == startTime && reservation.getEndTime() == endTime) {
            return true;
        }
        return false;
    }

    private Reservation buildReservation(String vehicleId, int startTime, int endTime, int totalCost) {
        Reservation reservation = new Reservation(vehicleId, startTime, endTime);
        reservation.setCost(totalCost);
        return reservation;
    }
}
