package Blockbuster.App.Service;

import Blockbuster.App.DTO.MovieDto;

import java.util.List;

public interface MovieServiceInterface {

    MovieDto createMovie(MovieDto movieDto);
    MovieDto findMovieById(int id);
    MovieDto updateMovie(MovieDto movieDto);
    void deleteMovieById(int id);
    MovieDto findMovieByTitle (String title);
    List<MovieDto> findMoviesByYear (int year);
    List<MovieDto> findMoviesByGenre (String genre);
    MovieDto movieBeingWatched (int customerId, int movieId);
    List<MovieDto> findAllMovies();


}
