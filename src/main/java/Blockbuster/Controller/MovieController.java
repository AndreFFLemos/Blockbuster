package Blockbuster.Controller;

import Blockbuster.Model.Movie;
import Blockbuster.Service.MovieServiceInterface;
import Blockbuster.View.MovieView;

public class MovieController implements MovieControllerInterface{
   private MovieView mv;
   private MovieServiceInterface msi;

    // Movie Controller acts as an intermediary between movie view and movie service. It doesn't interact directly with list or DB

    public String createMovie(Movie movie) {
        return msi.createMovie(movie);
    }

    public String findMovie(Movie movie) {
        return msi.findMovie(movie);
    }
    public String updateMovie(Movie movie) {
         return msi.updateMovie(movie);
    }
    /*
    An iterator was declared and initialized in order
     to remove the movie while iterating through the list
     */
    public String deleteMovie(Movie movie) {

          return msi.deleteMovie(movie);
    }
}