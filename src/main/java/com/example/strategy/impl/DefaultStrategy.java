package com.example.strategy.impl;

import com.example.entity.Vehicle;
import com.example.strategy.BookingChoiceStrategy;

/**
 *
 */
public class DefaultStrategy implements BookingChoiceStrategy {

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return o1.getPrice()-o2.getPrice();
    }
}
