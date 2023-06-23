package Blockbuster.Service;

import Blockbuster.Model.Movie;
import Blockbuster.Repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ComponentScan;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ComponentScan(basePackages = "Blockbuster")
class MovieServiceTest {

    private Movie movie;
    @Mock
    private MovieRepository mr;
    @InjectMocks
    private MovieService ms;

    @BeforeEach
    void setup (){
        movie= new Movie();
    }

    @Test
    void createMovie() {
        when(mr.save(any(Movie.class))).thenReturn(movie);

        Movie mockedMovie= ms.createMovie(movie);
        assertEquals(mockedMovie,movie);
        verify(mr).save(any(Movie.class));

    }

    @Test
    void findMovieById() {
        movie.setId(1);
    when(mr.findById(1)).thenReturn(Optional.of(movie));
    Movie mockedM= ms.findMovieById(1);

    assertEquals(mockedM,movie);
    verify(mr).findById(anyInt());
    }

    @Test
    void findAllMoviesTest(){
        Movie movie1=new Movie();
        Movie movie2=new Movie();
        List<Movie> movies= new LinkedList<>();
        movies.add(movie);
        movies.add(movie1);
        movies.add(movie2);
        when(mr.findAll()).thenReturn(movies);
        List <Movie> mockedList= ms.findAllMovies();

        assertEquals(3,mockedList.size());
        verify(mr).findAll();
    }

    @Test
    void updateMovie() {
        movie.setId(1);
        movie.setTitle("Rambo");
        movie.setGenre("Action");

        when(mr.findById(1)).thenReturn(Optional.of(movie)); //when findById gets used then it returns
        when(mr.save(any(Movie.class))).thenReturn(movie); //when a movie is saved by the repo then return that movie

        Movie mockedM= ms.updateMovie(movie);
        assertEquals("Rambo",mockedM.getTitle());
        verify(mr).save(any(Movie.class));
    }

    @Test
    void deleteMovieById() {
        movie.setId(1);
        when(mr.findById(1)).thenReturn(Optional.of(movie));
        doNothing().when(mr).deleteById(1);

        ms.deleteMovieById(1);
        verify(mr).deleteById(anyInt());
    }
}