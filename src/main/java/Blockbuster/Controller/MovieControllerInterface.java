package Blockbuster.Controller;

import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Movie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieControllerInterface {

    ResponseEntity<MovieDto> createMovie(Movie movie);
    ResponseEntity<MovieDto>  findMovieById(int id);
    ResponseEntity<List<MovieDto>> findAllMovies();
    ResponseEntity<List<MovieDto>> findMoviesByPrice(int price);
    ResponseEntity<List<MovieDto>> findMoviesByReleaseYear(int year);
    ResponseEntity<List<MovieDto>> findMoviesByGenre(String genre);
    ResponseEntity<MovieDto>  updateMovie(Movie movie);
    void deleteMovie(Movie movie);

}
