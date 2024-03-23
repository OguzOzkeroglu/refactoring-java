package com.etraveli.movierental.service;

import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.model.MovieRental;
import com.etraveli.movierental.enums.MovieType;
import com.etraveli.movierental.repository.MovieRepository;

public class RentalService {
    private final MovieRepository movieRepository = new MovieRepository();

    public String createRentalStatement(Customer customer) {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

        for (MovieRental rental : customer.getRentals()) {
            Movie movie = movieRepository.getMovieById(rental.getMovieId());
            double rentalCharge = calculateAmountFor(rental, movie);

            frequentRenterPoints += calculateFrequentRenterPoints(rental, movie);
            result.append("\t").append(movie.getTitle()).append("\t").append(rentalCharge).append("\n");
            totalAmount += rentalCharge;
        }

        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent points\n");

        return result.toString();
    }

    private double calculateAmountFor(MovieRental rental, Movie movie) {
        double amount = 0;
        switch (movie.getType()) {
            case REGULAR -> {
                amount += 2;
                if (rental.getDays() > 2) {
                    amount += (rental.getDays() - 2) * 1.5;
                }
            }
            case NEW -> amount = rental.getDays() * 3;
            case CHILDREN -> {
                amount += 1.5;
                if (rental.getDays() > 3) {
                    amount += (rental.getDays() - 3) * 1.5;
                }
            }
        }
        return amount;
    }

    private int calculateFrequentRenterPoints(MovieRental rental, Movie movie) {
        int points = 1; // Default point for renting any movie
        if (movie.getType() == MovieType.NEW && rental.getDays() > 1) {
            points += 1; // Additional bonus point for new release rented for more than 1 day
        }
        return points;
    }
}
