package com.example.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class ReservationTest {

    @Test
    void getCost() {
        Reservation reservation = new Reservation("V1",1,2);
        reservation.setCost(300);
        assertEquals(300,reservation.getCost());
    }
}