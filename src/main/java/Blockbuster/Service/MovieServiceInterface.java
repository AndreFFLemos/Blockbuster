package Blockbuster.Service;

import Blockbuster.Model.Movie;

public interface MovieServiceInterface {

    String createMovie(Movie movie);
    String findMovie(Movie movie);
    String updateMovie(Movie movie);
    String deleteMovie(Movie movie);
}
