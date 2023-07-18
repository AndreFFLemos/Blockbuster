package Blockbuster.Service;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieServiceInterface {

    MovieDto createMovie(MovieDto movieDto);
    MovieDto findMovieById(int id);
    MovieDto updateMovie(MovieDto movieDto);
    void deleteMovieById(int id);
    MovieDto findMovieByTitle (String title);
    List<MovieDto> findMoviesByYear (int year);
    List<MovieDto> findMoviesByGenre (String genre);
    MovieDto watchMovie (CustomerDto customerDto,MovieDto movieDto);


}
