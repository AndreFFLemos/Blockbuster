package Blockbuster.Controller;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Movie;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MovieControllerInterface {

    ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto);
    ResponseEntity<MovieDto>  findMovieById(@RequestParam ("id") int id);
    ResponseEntity<MovieDto>  getMovie(@PathVariable int id);
    ResponseEntity<List<MovieDto>> findAllMovies();
    ResponseEntity<List<MovieDto>> findMoviesByReleaseYear(@Valid @RequestParam ("year") int year);
    ResponseEntity<List<MovieDto>> findMoviesByGenre(@Valid @RequestParam ("genre") String genre);
    ResponseEntity<MovieDto>  updateMovie(@PathVariable int id,@Valid @RequestBody MovieDto movieDto);
    ResponseEntity <Void> deleteMovie(@PathVariable int id);
    ResponseEntity <MovieDto> movieBeingWatched(@Valid @RequestBody CustomerDto customerDto, @RequestBody MovieDto movieDto);

}
