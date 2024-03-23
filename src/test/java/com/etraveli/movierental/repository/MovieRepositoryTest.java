package com.etraveli.movierental.repository;

import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.enums.MovieType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MovieRepositoryTest {
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        movieRepository = new MovieRepository();
    }

    @Test
    void testGetMovieByIdReturnsCorrectMovie() {
        // Test retrieval of each predefined movie
        assertAll("movies",
                () -> verifyMovie("F001", "You've Got Mail", MovieType.REGULAR),
                () -> verifyMovie("F002", "Matrix", MovieType.REGULAR),
                () -> verifyMovie("F003", "Cars", MovieType.CHILDREN),
                () -> verifyMovie("F004", "Fast & Furious X", MovieType.NEW)
        );
    }

    @Test
    void testGetMovieByIdWithNonexistentId() {
        assertNull(movieRepository.getMovieById("nonexistent"), "Querying a non-existent ID should return null.");
    }

    @Test
    void testRepositoryInitialization() {
        assertEquals(4, movieRepository.getAllMovies().size(), "The repository should be initialized with 4 movies.");
    }

    @Test
    void testMovieIdUniqueness() {
        Set<String> uniqueIds = new HashSet<>();
        movieRepository.getAllMovies().forEach(movie -> assertTrue(uniqueIds.add(movie.getTitle()), "Movie IDs should be unique"));
    }

    private void verifyMovie(String id, String expectedTitle, MovieType expectedType) {
        Movie movie = movieRepository.getMovieById(id);
        assertAll("movie properties",
                () -> assertEquals(expectedTitle, movie.getTitle(), "Title should match"),
                () -> assertEquals(expectedType, movie.getType(), "Type should match")
        );
    }
}