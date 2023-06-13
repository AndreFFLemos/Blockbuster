package Blockbuster.Controller;

import Blockbuster.Model.Movie;

public interface MovieControllerInterface {

    Movie createMovie(Movie movie);
    Movie findMovieById(int id); //check movie information
    Movie updateMovie(Movie movie);
    void deleteMovie(Movie movie);

}
