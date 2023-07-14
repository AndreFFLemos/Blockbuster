package Blockbuster.Service;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;
import Blockbuster.Repository.CustomerRepository;
import Blockbuster.Repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    private Movie movie;
    private Customer customer;
    private CustomerDto customerDto;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private MovieService ms;
    private List <Movie> movies;
    private MovieDto movieDto;
    private MovieDto movieDtoSaved;
    ModelMapper modelMapper;


    @BeforeEach
    void setup (){
        modelMapper=new ModelMapper();
        ms= new MovieService(movieRepository,customerRepository,modelMapper);
        movie= new Movie(0,"Rambo","Action",1982,7.5);
        movieDto=new MovieDto("Rambo","Action",1982,7.5);
        movies= new LinkedList<>();
        movies.add(movie);
        movieDtoSaved= modelMapper.map(movie, MovieDto.class);
        customer= new Customer(0,"Ana", "Lemos","AL","ola",1234,"a@l",movies);
        customerDto= new CustomerDto("Ana", "Lemos","AL","a@l",1234);
    }

    @Test
    void createMovieTest() {

        Movie newMovie= new Movie(0,"ET","Adventure",1980,8.5);
        MovieDto newMovieDto= new MovieDto("ET","Adventure",1980,8.5);

        //test when movie doesn't exist in the DB
        when (movieRepository.findMovieByTitle("ET")).thenReturn(Optional.empty());
        when(movieRepository.save(any(Movie.class))).thenReturn(newMovie);
        Optional<MovieDto> mockedMovie= ms.createMovie(newMovieDto);

        assertEquals(Optional.of(newMovieDto),mockedMovie);

        //when movie exists
        when (movieRepository.findMovieByTitle("Rambo")).thenReturn(Optional.of(movie));
        Optional <MovieDto> existingMovie= ms.createMovie(movieDto);
        assertTrue(existingMovie.isEmpty());

        verify(movieRepository).save(newMovie);
        verify(movieRepository).findMovieByTitle("ET");
        verify(movieRepository).findMovieByTitle("Rambo");
    }

    @Test
    void findMovieByIdTest() {
        //when movie exists
        when(movieRepository.findById(0)).thenReturn(Optional.of(movie));
        Optional<MovieDto> mockedM= ms.findMovieById(0);

        assertEquals(Optional.of(movieDtoSaved),mockedM);

        //when movie doesn't exist
        when (movieRepository.findById(5)).thenReturn(Optional.empty());
        Optional <MovieDto> movieNotFound= ms.findMovieById(5);
        assertTrue(movieNotFound.isEmpty());

        verify(movieRepository).findById(0);
        verify(movieRepository).findById(5);
    }

    @Test
    void findAllMoviesTest(){
        Movie movie1=new Movie();
        Movie movie2=new Movie();

        movies.add(movie1);
        movies.add(movie2);

        when(movieRepository.findAll()).thenReturn(movies);
        List <MovieDto> mockedList= ms.findAllMovies();

        assertEquals(mockedList.size(),3);
        verify(movieRepository).findAll();
    }

    @Test
    void updateMovieTest() {
        Movie updatedMovie= new Movie(0,"Matrix","Action",1999,8.0);
        MovieDto updatedMovieDto= new MovieDto("Matrix","Action",1999,8.0);

        //when the movie exists
        when(movieRepository.findMovieByTitle("Matrix")).thenReturn(Optional.of(updatedMovie)); //when findById gets used then it returns
        when(movieRepository.save(updatedMovie)).thenReturn(updatedMovie); //when a movie is saved by the repo then return that movie
        Optional<MovieDto> mockedM= ms.updateMovie(updatedMovieDto);
        assertEquals("Matrix",mockedM.get().getTitle());

        //when the movie doesn't exist
        when (movieRepository.findMovieByTitle("ET")).thenReturn(Optional.empty());
        ms.updateMovie(new MovieDto("ET","adventure",1980,8.0));

        verify(movieRepository).save(updatedMovie);
        verify(movieRepository).deleteByTitle("Matrix");
        verify(movieRepository).findMovieByTitle("Matrix");
        verify(movieRepository).findMovieByTitle("ET");

    }

    @Test
    void deleteMovieByIdTest() {
        when(movieRepository.findById(0)).thenReturn(Optional.of(movie));
        doNothing().when(movieRepository).deleteById(0);

        ms.deleteMovieById(0);
        verify(movieRepository).deleteById(0);
    }

    @Test
    void findMovieByTitleTest (){

        //check when movie is present
        when(movieRepository.findMovieByTitle("Rambo")).thenReturn(Optional.of(movie));
        Optional<MovieDto> mockedM= ms.findMovieByTitle("Rambo");
        assertEquals("Rambo",mockedM.get().getTitle());

        //check when movie is not present
        when(movieRepository.findMovieByTitle("Matrix")).thenReturn(Optional.empty());
        Optional<MovieDto> movieNotFound= ms.findMovieByTitle("Matrix");
        assertTrue(movieNotFound.isEmpty());

        verify(movieRepository).findMovieByTitle("Rambo");
        verify(movieRepository).findMovieByTitle("Matrix");
    }

    @Test
    void findMoviesByGenre(){
        Movie m2=new Movie();
        m2.setGenre("Action");

        movies.add(m2);

        //check when movie is present
        when(movieRepository.findMoviesByGenre("Action")).thenReturn(movies);
        List<MovieDto> genreFound= ms.findMoviesByGenre("Action");

        assertEquals(2,genreFound.size());

        //check when movie is not present
        when(movieRepository.findMoviesByGenre("Drama")).thenReturn(Collections.emptyList());
        List<MovieDto> genreNotFound= ms.findMoviesByGenre("Drama");
        assertTrue(genreNotFound.isEmpty());

        verify(movieRepository).findMoviesByGenre("Action");
        verify(movieRepository).findMoviesByGenre("Drama");
    }

    @Test
    void findMovieByYear(){
        movie.setReleaseYear(1988);

        //check when movie is present
        when(movieRepository.findMoviesByYear(1988)).thenReturn(movies);
        List<MovieDto> yearFound= ms.findMoviesByYear(1988);
        assertEquals(1,yearFound.size());

        //check when movie is not present
        when(movieRepository.findMoviesByYear(1950)).thenReturn(Collections.emptyList());
        List<MovieDto> yearNotFound= ms.findMoviesByYear(1950);
        assertTrue(yearNotFound.isEmpty());

        verify(movieRepository).findMoviesByYear(1988);
        verify(movieRepository).findMoviesByYear(1950);
    }

    @Test
    public void watchMovieTest(){
        //if movie exists
        when(movieRepository.findMovieByTitle("Rambo")).thenReturn(Optional.of(movie));
        when(customerRepository.findByPhone(1234)).thenReturn(Optional.of(customer));
        Optional <MovieDto> watchedMovie= ms.watchMovie(customerDto,movieDto);
        assertEquals(Optional.of(movieDto),watchedMovie);

        //if movie doesn't exist
        movieDto.setTitle("ET");
        when(movieRepository.findMovieByTitle("ET")).thenReturn(Optional.empty());
        Optional <MovieDto> nonExistentMovie= ms.watchMovie(customerDto,movieDto);
        assertTrue(nonExistentMovie.isEmpty());

        verify(customerRepository,times(2)).findByPhone(1234);
        verify(movieRepository).findMovieByTitle("Rambo");
        verify(movieRepository).findMovieByTitle("ET");
        verify(movieRepository).save(movie);
        verify(customerRepository).save(customer);
    }
}