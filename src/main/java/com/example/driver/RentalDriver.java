package com.example.driver;

import com.example.enums.SupportedCommand;
import com.example.exception.ApplicationException;
import com.example.service.BranchService;
import com.example.service.ReservationService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RentalDriver {

    private static final RentalDriver instance = new RentalDriver();
    private static final BranchService branchService = BranchService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();

    private RentalDriver() {
    }

    public static RentalDriver getInstance() {
        return instance;
    }

    public List<String> runCommandsFromFile(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String inputLine;
        List<String> responses = new ArrayList<>();
        while ((inputLine = br.readLine()) != null) {
            responses.add(processLine(inputLine));
        }
        return responses;
    }

    public String processLine(String line) {
        String[] command = line.split(" ");
        SupportedCommand supportedCommand = SupportedCommand.valueOf(command[0]);
        switch (supportedCommand) {
            case ADD_BRANCH:
                return String.valueOf(branchService.addBranch(command[1], command[2])).toUpperCase();
            case ADD_VEHICLE:
                return String.valueOf(branchService.addVehicle(command[1], command[2], command[3], command[4])).toUpperCase();
            case BOOK:
                return String.valueOf(reservationService.book(command[1], command[2], command[3], command[4]));
            case DISPLAY_VEHICLES:
                List<String> ids = branchService.getAvailableVehicles(command[1], command[2], command[3]);
                return String.join(",", ids);
            default:
                throw new ApplicationException(ApplicationException.Code.INVALID_INPUT, "Invalid Command " + command + " supplied");
        }
    }
}