package com.example.service;

/**
 *
 */
public class PricingService {

    private static final PricingService instance = new PricingService();

    private PricingService() {
    }

    public static PricingService getInstance() {
        return instance;
    }

    public int dynamicPriceHourBasis(String branchName, int vehiclePerHourCost) {
        return vehiclePerHourCost;
    }
}
