package com.etraveli.movierental.services;

import com.etraveli.movierental.models.Customer;
import com.etraveli.movierental.models.Movie;
import com.etraveli.movierental.models.MovieRental;
import com.etraveli.movierental.models.MovieType;
import com.etraveli.movierental.repositories.MovieRepository;

public class RentalService {
  private final MovieRepository movieRepository = new MovieRepository();

  public String statement(Customer customer) {
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

    for (MovieRental rental : customer.getRentals()) {
      Movie movie = movieRepository.getMovieById(rental.getMovieId());
      double thisAmount = calculateAmountFor(rental, movie);

      frequentRenterPoints += calculateFrequentRenterPoints(rental, movie);
      result.append("\t").append(movie.getTitle()).append("\t").append(thisAmount).append("\n");
      totalAmount += thisAmount;
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
    if (movie.getType() == MovieType.NEW && rental.getDays() > 2) {
      return 2;
    }
    return 1;
  }
}
