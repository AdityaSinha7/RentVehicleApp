package com.example;

import com.example.driver.RentalDriver;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public class Geektrust {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("No input file.");
            System.out.println("Please provide input text file path in the command line argument");
            return;
        }

        RentalDriver rentalDriver = RentalDriver.getInstance();
        try {
            List<String> output = rentalDriver.runCommandsFromFile(args[0]);
            display(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void display(List<String> outputs) {
        outputs.stream().filter(Objects::nonNull).forEach(System.out::println);
    }
}
