package Blockbuster.Controller;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Movie;
import Blockbuster.Service.MovieServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/movie")
public class MovieController implements MovieControllerInterface{
   private MovieServiceInterface movieServiceInterface;

    @Override
    @PostMapping(value = "/new")
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieServiceInterface.createMovie(movieDto));
    }

    @Override
    @GetMapping(value = "/findbyid")
    public ResponseEntity<MovieDto> findMovieById(@Valid @RequestParam ("id") int id) {
        return ResponseEntity.ok(movieServiceInterface.findMovieById(id));
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDto> getMovie(@Valid @PathVariable int id) {
        return ResponseEntity.ok(movieServiceInterface.findMovieById(id));
    }

    @Override
    @GetMapping(value = "/all")
    public ResponseEntity<List<MovieDto>> findAllMovies() {
        return ResponseEntity.ok(movieServiceInterface.findAllMovies());
    }

    @Override
    @GetMapping(value = "/findbyyear")
    public ResponseEntity<List<MovieDto>> findMoviesByReleaseYear(@Valid @RequestParam ("year") int year) {
        return ResponseEntity.ok(movieServiceInterface.findMoviesByYear(year));
    }

    @Override
    @GetMapping(value = "/findbygenre")
    public ResponseEntity<List<MovieDto>> findMoviesByGenre(@Valid @RequestParam ("genre") String genre) {
        return ResponseEntity.ok(movieServiceInterface.findMoviesByGenre(genre));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable int id, @Valid @RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieServiceInterface.updateMovie(movieDto));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) {

        movieServiceInterface.deleteMovieById(id);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<MovieDto> movieBeingWatched(@Valid @RequestBody CustomerDto customerDto,@Valid @RequestBody MovieDto movieDto) {
        MovieDto movieBeingWatched= movieServiceInterface.movieBeingWatched(customerDto,movieDto);

        if (movieBeingWatched==null){
            return new ResponseEntity<>(movieBeingWatched, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(movieBeingWatched);
    }

}