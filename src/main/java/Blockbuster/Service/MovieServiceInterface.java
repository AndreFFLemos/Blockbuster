package Blockbuster.Service;

import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieServiceInterface {

    Optional<MovieDto> createMovie(MovieDto movieDto);
    Optional <MovieDto> findMovieById(int id);
    Optional<MovieDto> updateMovie(MovieDto movieDto);
    void deleteMovieById(int id);
    Optional<MovieDto> findMovieByTitle (String title);
    List <MovieDto> findMoviesByYear (int year);
    List <MovieDto> findMoviesByGenre (String genre);
    List<MovieDto> findMoviesByPrice (int price);

}
