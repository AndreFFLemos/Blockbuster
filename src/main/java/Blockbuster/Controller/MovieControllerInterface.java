package Blockbuster.Controller;

import Blockbuster.Model.Movie;

public interface MovieControllerInterface {

    String createMovie(Movie movie);
    String findMovie(Movie movie); //check movie information
    String updateMovie(Movie movie);
    String deleteMovie(Movie movie);

}
