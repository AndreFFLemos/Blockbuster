package Blockbuster.Service;

import Blockbuster.Model.Movie;

public interface MovieServiceInterface {

    Movie createMovie(Movie movie);
    Movie findMovieById(int id);
    Movie updateMovie(Movie movie);
    void deleteMovieById(int id);
}
