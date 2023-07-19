package Blockbuster.Controller;

import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Movie;
import Blockbuster.Service.MovieServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/movie")
public class MovieController implements MovieControllerInterface{
   private MovieServiceInterface movieServiceInterface;

    @Override
    @PostMapping(value = "/new")
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieServiceInterface.createMovie(movieDto));
    }

    @Override
    @GetMapping(value = "/findbyid")
    public ResponseEntity<MovieDto> findMovieById(@RequestParam ("id") int id) {
        return ResponseEntity.ok(movieServiceInterface.findMovieById(id));
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable int id) {
        return ResponseEntity.ok(movieServiceInterface.findMovieById(id));
    }

    @Override
    @GetMapping(value = "/all")
    public ResponseEntity<List<MovieDto>> findAllMovies() {
        return ResponseEntity.ok(movieServiceInterface.findAllMovies());
    }

    @Override
    @GetMapping(value = "/findbyyear")
    public ResponseEntity<List<MovieDto>> findMoviesByReleaseYear(@RequestParam ("year") int year) {
        return ResponseEntity.ok(movieServiceInterface.findMoviesByYear(year));
    }

    @Override
    @GetMapping(value = "/findbygenre")
    public ResponseEntity<List<MovieDto>> findMoviesByGenre(@RequestParam ("genre") String genre) {
        return ResponseEntity.ok(movieServiceInterface.findMoviesByGenre(genre));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable int id,@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieServiceInterface.updateMovie(movieDto));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void deleteMovie(@PathVariable int id,@RequestBody MovieDto movieDto) {

        movieServiceInterface.deleteMovieById(id);
    }

}