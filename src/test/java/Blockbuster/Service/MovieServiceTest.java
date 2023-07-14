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
        modelMapper=new ModelMapper();
        ms= new MovieService(mr,modelMapper);
        movie= new Movie(0,"Rambo","Action",1982,7.5);
        movieDto=new MovieDto("Rambo","Action",1982,7.5);
        movies= new LinkedList<>();
        movies.add(movie);
        movieDtoSaved= modelMapper.map(movie, MovieDto.class);
    }

    @Test
    void createMovieTest() {

        Movie newMovie= new Movie(0,"ET","Adventure",1980,8.5);
        MovieDto newMovieDto= new MovieDto("ET","Adventure",1980,8.5);

        //test when movie doesn't exist in the DB
        when (mr.findMovieByTitle("ET")).thenReturn(Optional.empty());
        when(mr.save(any(Movie.class))).thenReturn(newMovie);
        Optional<MovieDto> mockedMovie= ms.createMovie(newMovieDto);

        assertEquals(Optional.of(newMovieDto),mockedMovie);

        //when movie exists
        when (mr.findMovieByTitle("Rambo")).thenReturn(Optional.of(movie));
        Optional <MovieDto> existingMovie= ms.createMovie(movieDto);
        assertTrue(existingMovie.isEmpty());

        verify(mr).save(newMovie);
        verify(mr).findMovieByTitle("ET");
        verify(mr).findMovieByTitle("Rambo");
    }

    @Test
    void findMovieByIdTest() {
        //when movie exists
        when(mr.findById(0)).thenReturn(Optional.of(movie));
        Optional<MovieDto> mockedM= ms.findMovieById(0);

        assertEquals(Optional.of(movieDtoSaved),mockedM);

        //when movie doesn't exist
        when (mr.findById(5)).thenReturn(Optional.empty());
        Optional <MovieDto> movieNotFound= ms.findMovieById(5);
        assertTrue(movieNotFound.isEmpty());

        verify(mr).findById(0);
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

        assertEquals("Rambo",mockedM.get().getTitle());
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

        //check when movie is present
        when(mr.findMovieByTitle("Rambo")).thenReturn(Optional.of(movie));
        Optional<MovieDto> mockedM= ms.findMovieByTitle("Rambo");
        assertEquals("Rambo",mockedM.get().getTitle());

        //check when movie is not present
        when(mr.findMovieByTitle("Matrix")).thenReturn(Optional.empty());
        Optional<MovieDto> movieNotFound= ms.findMovieByTitle("Matrix");
        assertTrue(movieNotFound.isEmpty());

        verify(mr).findMovieByTitle("Rambo");
        verify(mr).findMovieByTitle("Matrix");
    }

    @Test
    void findMoviesByGenre(){
        Movie m2=new Movie();
        m2.setGenre("Action");

        movies.add(m2);

        //check when movie is present
        when(mr.findMoviesByGenre("Action")).thenReturn(movies);
        List<MovieDto> genreFound= ms.findMoviesByGenre("Action");

        assertEquals(2,genreFound.size());

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

        //check when movie is present
        when(mr.findMoviesByYear(1988)).thenReturn(movies);
        List<MovieDto> yearFound= ms.findMoviesByYear(1988);
        assertEquals(1,yearFound.size());

        //check when movie is not present
        when(mr.findMoviesByYear(1950)).thenReturn(Collections.emptyList());
        List<MovieDto> yearNotFound= ms.findMoviesByYear(1950);
        assertTrue(yearNotFound.isEmpty());

        verify(mr).findMoviesByYear(1988);
        verify(mr).findMoviesByYear(1950);
    }
}