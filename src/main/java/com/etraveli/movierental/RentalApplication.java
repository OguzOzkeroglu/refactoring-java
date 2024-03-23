package com.etraveli.movierental;

import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.MovieRental;
import com.etraveli.movierental.service.RentalService;

import java.util.Arrays;
import java.util.List;

public class RentalApplication {

    public static void main(String[] args) {
        Customer testCustomer = createTestCustomer();

        String expected = "Rental Record for C. U. Stomer\n" +
                "\tYou've Got Mail\t3.5\n" +
                "\tMatrix\t2.0\n" +
                "Amount owed is 5.5\n" +
                "You earned 2 frequent points\n";

        String result = new RentalService().statement(testCustomer);

        assertExpectedResult(expected, result);
    }

    private static Customer createTestCustomer() {
        List<MovieRental> rentals = Arrays.asList(
                new MovieRental("F001", 3),
                new MovieRental("F002", 1)
        );
        return new Customer("C. U. Stomer", rentals);
    }

    private static void assertExpectedResult(String expected, String result) {
        if (!result.equals(expected)) {
            System.err.println("Assertion Failed.");
            System.err.println("Expected: " + System.lineSeparator() + expected);
            System.err.println("Got: " + System.lineSeparator() + result);
        } else {
            System.out.println("Success");
        }
    }
}