package com.example.driver;

import com.example.service.BranchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class RentalDriverTest {

    private RentalDriver rentalDriver;
    private BranchService branchService = Mockito.mock(BranchService.class);

    @BeforeEach
    public void setup() {
        rentalDriver = RentalDriver.getInstance();
    }

    @Test
    void testRunCommandsFromFileWithInvalidFile() {
        assertThrows(IOException.class, () -> rentalDriver.runCommandsFromFile(""), "Expected to throw exception");
    }

    @Test
    void testRunCommandsFromFileWithValidFile() throws IOException {
        String inputFile =
                Objects.requireNonNull(this.getClass().getClassLoader().getResource("input"))
                        .getFile();
        String outputFile =
                Objects.requireNonNull(this.getClass().getClassLoader().getResource("output"))
                        .getFile();

        List<String> output = rentalDriver.runCommandsFromFile(inputFile);

        BufferedReader br = new BufferedReader(new FileReader(outputFile));
        String inputLine;
        List<String> responses = new ArrayList<>();
        while ((inputLine = br.readLine()) != null) {
            responses.add(inputLine);
        }
        String expectedResult = responses.stream().map(String::trim).collect(Collectors.joining(";"));
        String result =
                output.stream()
                        .filter(Objects::nonNull)
                        .map(String::trim)
                        .collect(Collectors.joining(";"));
        assertEquals(expectedResult, result);
    }

    @Test
    void processLineWithInvalidCommand() {
        String command = "INVALID_COMMAND B1 CAR,BIKE,VAN";
        assertThrows(Exception.class, () -> rentalDriver.processLine(command), "Expected to throw exception");
    }

}