package Blockbuster.Service;

import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;
import Blockbuster.Repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
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
    private List <Movie> movies;
    private MovieDto movieDto;
    private MovieDto movieDtoSaved;
    ModelMapper modelMapper;


    @BeforeEach
    void setup (){
        movie= new Movie();
        movieDto=new MovieDto();
        movies= new LinkedList<>();
        movies.add(movie);
        modelMapper=new ModelMapper();
        movieDtoSaved= modelMapper.map(movie,MovieDto.class);
    }

    @Test
    void createMovieTest() {

        //test when movie doesn't exist in the DB
        when (mr.findById(4)).thenReturn(Optional.of(movie));
        when(mr.save(any(Movie.class))).thenReturn(movie);
        Optional<MovieDto> mockedMovie= ms.createMovie(movieDto);

        assertEquals(mockedMovie,movieDtoSaved);

        //when movie exists
        movie.setId(1);
        when (mr.findById(1)).thenReturn(Optional.of(movie));
        Optional <MovieDto> existingMovie= ms.createMovie(movieDto);
        assertTrue(existingMovie.isEmpty());
        verify(mr,times(1)).save(movie);
    }

    @Test
    void findMovieByIdTest() {
        //when movie exists
        movie.setId(1);
        when(mr.findById(1)).thenReturn(Optional.of(movie));
        Optional<MovieDto> mockedM= ms.findMovieById(1);

        assertEquals(mockedM,movieDtoSaved);

        //when movie doesn't exist
        when (mr.findById(5)).thenReturn(Optional.empty());
        Optional <MovieDto> movieNotFound= ms.findMovieById(5);
        assertTrue(movieNotFound.isEmpty());

        verify(mr).findById(1);
        verify(mr).findById(5);
    }

    @Test
    void findAllMoviesTest(){
        Movie movie1=new Movie();
        Movie movie2=new Movie();

        movies.add(movie1);
        movies.add(movie2);

        when(mr.findAll()).thenReturn(movies);
        List <MovieDto> mockedList= ms.findAllMovies();

        assertEquals(mockedList.size(),3);
        verify(mr).findAll();
    }

    @Test
    void updateMovieTest() {
        movie.setId(1);
        movie.setTitle("Rambo");
        movie.setGenre("Action");

        when(mr.findById(1)).thenReturn(Optional.of(movie)); //when findById gets used then it returns
        when(mr.save(movie)).thenReturn(movie); //when a movie is saved by the repo then return that movie

        Optional<MovieDto> mockedM= ms.updateMovie(movieDto);

        assertEquals(mockedM.get().getTitle(),"Rambo");
        verify(mr).save(movie);
    }

    @Test
    void deleteMovieByIdTest() {
        movie.setId(1);
        when(mr.findById(1)).thenReturn(Optional.of(movie));
        doNothing().when(mr).deleteById(1);

        ms.deleteMovieById(1);
        verify(mr).deleteById(1);
    }

    @Test
    void findMovieByTitleTest (){
        movie.setTitle("Rambo");

        //check when movie is present
        when(mr.findMovieByTitle("Rambo")).thenReturn(Optional.of(movie));
        Optional<MovieDto> mockedM= ms.findMovieByTitle("Rambo");
        assertTrue(mockedM.get().getTitle()=="Rambo");

        //check when movie is not present
        when(mr.findMovieByTitle("Matrix")).thenReturn(Optional.empty());
        Optional<MovieDto> movieNotFound= ms.findMovieByTitle("Matrix");
        assertTrue(movieNotFound.isEmpty());

        verify(mr).findMovieByTitle("Rambo");
        verify(mr).findMovieByTitle("Matrix");
    }

    @Test
    void findMoviesByGenre(){
        movie.setGenre("Action");
        Movie m2=new Movie();
        m2.setGenre("Action");;

        movies.add(movie);
        movies.add(m2);

        //check when movie is present
        when(mr.findMoviesByGenre("Action")).thenReturn(movies);
        List<MovieDto> genreFound= ms.findMoviesByGenre("Action");

        assertEquals(genreFound.size(),2);

        //check when movie is not present
        when(mr.findMoviesByGenre("Drama")).thenReturn(Collections.emptyList());
        List<MovieDto> genreNotFound= ms.findMoviesByGenre("Drama");
        assertTrue(genreNotFound.isEmpty());

        verify(mr).findMoviesByGenre("Action");
        verify(mr).findMoviesByGenre("Drama");
    }

    @Test
    void findMovieByYear(){
        movie.setReleaseYear(1988);
        Movie m1=new Movie();
        m1.setReleaseYear(1900);
        Movie m2=new Movie();
        m2.setReleaseYear(2000);

        movies.add(m1);
        movies.add(m2);

        //check when movie is present
        when(mr.findMoviesByYear(1998)).thenReturn(movies);
        List<MovieDto> yearFound= ms.findMoviesByYear(1998);
        assertEquals(yearFound.size(),1);

        //check when movie is not present
        when(mr.findMoviesByYear(1950)).thenReturn(Collections.emptyList());
        List<MovieDto> yearNotFound= ms.findMoviesByYear(1950);
        assertTrue(yearNotFound.isEmpty());

        verify(mr).findMoviesByYear(1998);
        verify(mr).findMoviesByYear(1950);
    }

    @Test
    void findMovieByPrice(){
        movie.setPrice(2);
        Movie m1=new Movie();
        m1.setPrice(2);
        movies.add(m1);

        //check when movie is present
        when(mr.findMoviesByPrice(2)).thenReturn(movies);
        List<MovieDto> movieFoundByPrice= ms.findMoviesByPrice(2);
        assertEquals(movieFoundByPrice.size(),2);

        //check when movie is not present
        when(mr.findMoviesByPrice(4)).thenReturn(Collections.emptyList());
        List<MovieDto> movieNotFound= ms.findMoviesByPrice(4);
        assertTrue(movieNotFound.isEmpty());

        verify(mr).findMoviesByPrice(2);
        verify(mr).findMoviesByPrice(4);
    }
}