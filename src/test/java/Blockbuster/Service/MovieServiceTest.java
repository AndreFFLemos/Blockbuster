package Blockbuster.Service;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;
import Blockbuster.Repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ComponentScan;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
    void findMovieByIdTest() {
        movie.setId(1);
    when(mr.findById(1)).thenReturn(Optional.of(movie));
    Optional<Movie> mockedM= ms.findMovieById(1);

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

    @Test
    void findMovieByTitle (){
        movie.setTitle("Rambo");
        Movie movie1= new Movie();
        Movie movie2= new Movie();
        movie1.setTitle("Rambo1");
        movie2.setTitle("Matrix");

        List <Movie> movies= new LinkedList<>();
        movies.add(movie);
        movies.add(movie1);
        movies.add(movie2);

        //check when movie is present
        when(mr.findMovieByTitle(anyString())).thenReturn(movies);
        List<Movie> mockedM= ms.findMovieByTitle("Rambo");
        assertTrue(mockedM.contains(movie));

        //check when movie is not present
        when(mr.findMovieByTitle(anyString())).thenReturn(Collections.emptyList());
        List<Movie> movieNotFound= ms.findMovieByTitle("ET");
        assertTrue(movieNotFound.isEmpty());

        verify(mr, times(2)).findMovieByTitle(anyString());
    }

    @Test
    void findMoviesByGenre(){
        movie.setGenre("Action");
        Movie m1=new Movie();
        m1.setGenre("Drama");;
        Movie m2=new Movie();
        m2.setGenre("Action");;

        List <Movie> movies= new LinkedList<>();
        movies.add(movie);
        movies.add(m1);
        movies.add(m2);

        //check when movie is present
        when(mr.findMoviesByGenre(anyString())).thenReturn(movies);
        List<Movie> mockedM= ms.findMoviesByGenre("Action");
        assertTrue(mockedM.contains(movie));

        //check when movie is not present
        when(mr.findMoviesByGenre(anyString())).thenReturn(Collections.emptyList());
        List<Movie> movieNotFound= ms.findMoviesByGenre("War");
        assertTrue(movieNotFound.isEmpty());

        verify(mr, times(2)).findMoviesByGenre(anyString());
    }

    @Test
    void findMovieByYear(){
        movie.setReleaseYear(1988);
        Movie m1=new Movie();
        m1.setReleaseYear(1900);
        Movie m2=new Movie();
        m2.setReleaseYear(2000);

        List <Movie> movies= new LinkedList<>();
        movies.add(movie);
        movies.add(m1);
        movies.add(m2);

        //check when movie is present
        when(mr.findMoviesByYear(anyInt())).thenReturn(movies);
        List<Movie> mockedM= ms.findMoviesByYear(1988);
        assertTrue(mockedM.contains(movie));

        //check when movie is not present
        when(mr.findMoviesByYear(anyInt())).thenReturn(Collections.emptyList());
        List<Movie> movieNotFound= ms.findMoviesByYear(1990);
        assertTrue(movieNotFound.isEmpty());

        verify(mr, times(2)).findMoviesByYear(anyInt());
    }

    @Test
    void findMovieByPrice(){
        movie.setPrice(2);
        Movie m1=new Movie();
        m1.setPrice(2);
        Movie m2=new Movie();
        m2.setPrice(1);

        List <Movie> movies= new LinkedList<>();
        movies.add(movie);
        movies.add(m1);
        movies.add(m2);

        //check when movie is present
        when(mr.findMoviesByPrice(anyInt())).thenReturn(movies);
        List<Movie> mockedM= ms.findMoviesByPrice(2);
        assertTrue(mockedM.contains(movie));

        //check when movie is not present
        when(mr.findMoviesByPrice(anyInt())).thenReturn(Collections.emptyList());
        List<Movie> movieNotFound= ms.findMoviesByPrice(1990);
        assertTrue(movieNotFound.isEmpty());

        verify(mr, times(2)).findMoviesByPrice(anyInt());
    }
}