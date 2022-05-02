/*
package com.example.service;

import com.example.enums.VehicleType;
import com.example.exception.ApplicationException;
import com.example.repository.BranchRepository;
import com.example.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

*/
/**
 *
 *//*

class BranchServiceTest {

    @InjectMocks
    private BranchService branchService;
    private VehicleService vehicleService;
    private BranchRepository branchRepository;
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setup() {
        branchService = BranchService.getInstance();
        vehicleService = Mockito.mock(VehicleService.class);
        branchRepository = Mockito.mock(BranchRepository.class);
        reservationRepository = Mockito.mock(ReservationRepository.class);
    }

    @Test
    void addBranchWithValidArgs() {
        String branchName = "B1";
        String vehicleType = "CAR,BIKE";
        Mockito.when(branchRepository.addNewBranch(Mockito.anyString(), Mockito.anyList())).thenReturn(true);
        Mockito.when(vehicleService.getVehicleTypesByName(Mockito.anyString())).thenReturn(new ArrayList<VehicleType>() {{
            add(VehicleType.BIKE);
            add(VehicleType.CAR);
        }});
        assertEquals(true, branchService.addBranch(branchName, vehicleType));
    }

    @Test
    void addBranchWithInvalidArgs() {
        String branchName = "";
        String vehicleType = "CAR,BIKE";
        assertThrows(ApplicationException.class, () -> branchService.addBranch(branchName, vehicleType), "Expected to throw exception");
    }

    @Test
    void getAvailableVehiclesWithInvalidArgs() {
        assertThrows(ApplicationException.class, () -> branchService.getAvailableVehicles("", "", ""), "Expected to throw exception");
    }

//    @Test
//    void getAvailableVehiclesWithValidArgs() {
//        List<String> vehicleIds = new ArrayList<String>(){{add("V1");}};
//        Mockito.when(branchRepository.getVehicleIds(Mockito.anyString())).thenReturn(vehicleIds);
//        Mockito.when(reservationRepository.checkAvailable(Mockito.anyString(),Mockito.anyByte(),Mockito.anyByte())).thenReturn(true);
//        List<String> output = branchService.getAvailableVehicles("B1", "1", "2");
//        assertEquals(1,output.size());
//        assertEquals("V1",output.get(0));
//    }

    @Test
    void addVehicle() {
    }
}*/
