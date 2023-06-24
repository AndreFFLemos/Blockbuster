package Blockbuster.Service;

import Blockbuster.Model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieServiceInterface {

    Movie createMovie(Movie movie);
    Optional <Movie> findMovieById(int id);
    Movie updateMovie(Movie movie);
    void deleteMovieById(int id);
    List<Movie> findMovieByTitle (String title);
    List <Movie> findMoviesByYear (int year);
    List <Movie> findMoviesByGenre (String genre);
    List<Movie> findMoviesByPrice (int price);

}
