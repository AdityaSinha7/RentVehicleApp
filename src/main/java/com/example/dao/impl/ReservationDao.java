package com.example.dao.impl;

import com.example.dao.Dao;
import com.example.entity.Reservation;
import com.example.exception.ApplicationException;

import java.util.*;

/**
 *
 */
public class ReservationDao implements Dao<Reservation, UUID> {


    private static final Map<UUID, Reservation> reservationMap = new HashMap<>();
    private static final ReservationDao instance = new ReservationDao();

    private ReservationDao() {
    }

    public static ReservationDao getInstance() {
        return instance;
    }

    @Override
    public Optional<Reservation> findById(UUID uuid) {
        return reservationMap.containsKey(uuid) ? Optional.of(reservationMap.get(uuid)) : Optional.empty();
    }

    @Override
    public Reservation save(Reservation entity) {
        if (reservationMap.containsKey(entity.getId())) {
            throw new ApplicationException(ApplicationException.Code.CONFLICT, "This reservation id already exist in the database.");
        }
        return reservationMap.put(entity.getId(), entity);
    }

    @Override
    public Reservation update(Reservation entity) {
        return reservationMap.put(entity.getId(), entity);
    }

    @Override
    public Collection<Reservation> getAll() {
        return reservationMap.values();
    }
}
