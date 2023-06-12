package Blockbuster.Service;

import Blockbuster.Controller.MovieControllerInterface;
import Blockbuster.Model.Movie;
import Blockbuster.Repository.MovieRepositoryInterface;

public class MovieService implements MovieServiceInterface{
    private MovieControllerInterface mci;
    private MovieRepositoryInterface mri;

    public String createMovie(Movie movie) {
        if (mri.createMovie(movie)==true){
            return "Movie added to the stock!";
        }
        return "Movie already exists in stock!";
    }

    public String findMovie(Movie movie) {
        if (mri.findMovie(movie).equals(movie)) {
            return movie.toString();
        }
        return "We don't have that movie";
    }

    public String updateMovie(Movie movie) {
        if(mri.updateMovie(movie)==true){
            return "Movie updated!";
        }
        return "";
    }

    public String deleteMovie(Movie movie) {
        if (mri.deleteMovie(movie.getId())){
            return "Movie eliminated from the DB";
        }
        return "Movie doesn't exist";
    }
}
