package Blockbuster.Service;

import Blockbuster.Controller.MovieControllerInterface;
import Blockbuster.Model.Movie;
import Blockbuster.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Optional <Movie> findMovieById(int id) {
        Optional <Movie> movie= mr.findById(id);

        if (movie.isEmpty()){
            return Optional.empty();
        }

        return movie;
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
            existingMovie.setPrice(movie.getPrice());

            return mr.save(existingMovie);
    }

    public void deleteMovieById(int id) {
        Movie existingMovie= mr.findById(id).orElseThrow(()-> new IllegalArgumentException("Movie not found"));
        mr.deleteById(existingMovie.getId());
    }

    @Override
    public List<Movie> findMovieByTitle(String title) {
        List <Movie> movies= mr.findMovieByTitle(title);
        if (movies.isEmpty()){
            return Collections.emptyList(); // if no movie is found, it returns an empty Collection
        }
        return movies;
    }

    @Override
    public List<Movie> findMoviesByYear(int year) {
        List <Movie> movies= mr.findMoviesByYear(year);
        if (movies.isEmpty()){
            return Collections.emptyList();
        }

        return movies;
    }

    @Override
    public List<Movie> findMoviesByGenre(String genre) {
        List <Movie> movies= mr.findMoviesByGenre(genre);
        if (movies.isEmpty()){
            return Collections.emptyList();
        }
        return movies;
    }

    @Override
    public List<Movie> findMoviesByPrice(int price) {
        List <Movie> movies= mr.findMoviesByPrice(price);
        if (movies.isEmpty()){
            return Collections.emptyList();
        }
        return movies;
    }
}
