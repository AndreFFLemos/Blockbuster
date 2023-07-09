package Blockbuster.Controller;

import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Movie;
import Blockbuster.Model.Rental;
import Blockbuster.Service.MovieServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/movie")
public class MovieController implements MovieControllerInterface{
   private MovieServiceInterface msi;

    @Override
    public ResponseEntity<MovieDto> createMovie(Movie movie) {
        return null;
    }

    @Override
    public ResponseEntity<MovieDto> findMovieById(int id) {
        return null;
    }

    @Override
    public ResponseEntity<List<MovieDto>> findAllMovies() {
        return null;
    }

    @Override
    public ResponseEntity<List<MovieDto>> findMoviesByPrice(int price) {
        return null;
    }

    @Override
    public ResponseEntity<List<MovieDto>> findMoviesByReleaseYear(int year) {
        return null;
    }

    @Override
    public ResponseEntity<List<MovieDto>> findMoviesByGenre(String genre) {
        return null;
    }

    @Override
    public ResponseEntity<MovieDto> updateMovie(Movie movie) {
        return null;
    }

    @Override
    public void deleteMovie(Movie movie) {

    }

    @Override
    public ResponseEntity<List<Rental>> findRentalByMovie(Movie movie) {
        return null;
    }
}