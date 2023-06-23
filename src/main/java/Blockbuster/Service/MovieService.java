package Blockbuster.Service;

import Blockbuster.Controller.MovieControllerInterface;
import Blockbuster.Model.Movie;
import Blockbuster.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class MovieService implements MovieServiceInterface{
    private MovieControllerInterface mci;
    private MovieRepository mr;
    private Movie movie;

    @Autowired
    public MovieService(MovieControllerInterface mci, MovieRepository mr, Movie movie) {
        this.mci = mci;
        this.mr = mr;
        this.movie = movie;
    }

    public Movie createMovie(Movie movie) {
        if (mr.findById(movie.getId()).isPresent()){
            throw new IllegalArgumentException("Movie already present");
        }
        return mr.save(movie);
    }

    public Movie findMovieById(int id) {
       return mr.findById(id).orElseThrow(()->new NoSuchElementException("Movie not found"));
    }

    public List<Movie> findAllMovies(){
        return mr.findAll();
    }

    public Movie updateMovie(Movie movie) {
            Movie existingMovie= mr.findById(movie.getId()).orElseThrow(()-> new NoSuchElementException("Movie not found"));

            existingMovie.setTitle(movie.getTitle());
            existingMovie.setGenre(movie.getGenre());
            existingMovie.setRating(movie.getRating());
            existingMovie.setReleaseYear(movie.getReleaseYear());
            existingMovie.setCost(movie.getCost());

            return mr.save(existingMovie);
    }

    public void deleteMovieById(int id) {
        Movie existingMovie= mr.findById(id).orElseThrow(()-> new IllegalArgumentException("Movie not found"));
        mr.deleteById(existingMovie.getId());
    }
}
