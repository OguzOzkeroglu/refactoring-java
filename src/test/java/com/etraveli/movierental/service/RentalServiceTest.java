package com.etraveli.movierental.service;

import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.MovieRental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RentalServiceTest {
    private RentalService rentalService;

    @BeforeEach
    void setUp() {
        rentalService = new RentalService();
    }

    @Test
    void testRentalStatementForRegularMovie() {
        Customer customer = new Customer("Oguz", List.of(new MovieRental("F002", 3)));
        String expected = "Rental Record for Oguz\n\tMatrix\t3.5\nAmount owed is 3.5\nYou earned 1 frequent points\n";
        assertEquals(expected, rentalService.createRentalStatement(customer));
    }

    @Test
    void testRentalStatementForNewRelease() {
        Customer customer = new Customer("Oguz", List.of(new MovieRental("F004", 1)));
        String expected = "Rental Record for Oguz\n\tFast & Furious X\t3.0\nAmount owed is 3.0\nYou earned 1 frequent points\n";
        assertEquals(expected, rentalService.createRentalStatement(customer));
    }

    @Test
    void testRentalStatementForChildrenMovie() {
        Customer customer = new Customer("Oguz", List.of(new MovieRental("F003", 4)));
        String expected = "Rental Record for Oguz\n\tCars\t3.0\nAmount owed is 3.0\nYou earned 1 frequent points\n";
        assertEquals(expected, rentalService.createRentalStatement(customer));
    }

    @Test
    void testRentalStatementWithMultipleMovies() {
        Customer customer = new Customer("Oguz", List.of(
                new MovieRental("F001", 3),
                new MovieRental("F002", 1),
                new MovieRental("F003", 4),
                new MovieRental("F004", 2)
        ));
        String expected = "Rental Record for Oguz\n" +
                "\tYou've Got Mail\t3.5\n" +
                "\tMatrix\t2.0\n" +
                "\tCars\t3.0\n" +
                "\tFast & Furious X\t6.0\n" +
                "Amount owed is 14.5\n" +
                "You earned 5 frequent points\n";
        assertEquals(expected, rentalService.createRentalStatement(customer));
    }

    @Test
    void testRentalStatementForCustomerWithNoRentals() {
        Customer customer = new Customer("Oguz", List.of());
        String expected = "Rental Record for Oguz\nAmount owed is 0.0\nYou earned 0 frequent points\n";
        assertEquals(expected, rentalService.createRentalStatement(customer));
    }

    @Test
    void testNewReleaseBonusPointsCondition() {
        Customer customer = new Customer("Oguz", List.of(new MovieRental("F004", 2))); // F004 is a New Release
        String expected = "Rental Record for Oguz\n\tFast & Furious X\t6.0\nAmount owed is 6.0\nYou earned 2 frequent points\n"; // 1 base point + 1 bonus point
        assertEquals(expected, rentalService.createRentalStatement(customer));
    }

    @Test
    void testBoundaryConditionsForAdditionalCharges() {
        Customer customer = new Customer("Oguz", List.of(
                new MovieRental("F002", 2), // F002 is Regular, no extra charge for 2 days
                new MovieRental("F003", 3)  // F003 is Children's, no extra charge for 3 days
        ));
        String expected = "Rental Record for Oguz\n" +
                "\tMatrix\t2.0\n" + // Base charge only
                "\tCars\t1.5\n" +  // Base charge only
                "Amount owed is 3.5\n" +
                "You earned 2 frequent points\n"; // 1 point for each movie
        assertEquals(expected, rentalService.createRentalStatement(customer));
    }
}