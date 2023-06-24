package Blockbuster.Controller;

import Blockbuster.Model.Movie;
import Blockbuster.Service.MovieServiceInterface;
import org.springframework.stereotype.Controller;

@Controller
public abstract class MovieController implements MovieControllerInterface{
   private MovieServiceInterface msi;

    // Movie Controller acts as an intermediary between movie view and movie service. It doesn't interact directly with list or DB

    public Movie createMovie(Movie movie) {
        return msi.createMovie(movie);
    }

    /*public Movie findMovieById(int id) {
        return msi.findMovieById(id);
    }*/
    public Movie updateMovie(Movie movie) {
         return msi.updateMovie(movie);
    }

    public void deleteMovie(Movie movie) {


    }
}