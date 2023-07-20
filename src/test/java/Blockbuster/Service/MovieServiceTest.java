package Blockbuster.Service;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;
import Blockbuster.Repository.CustomerRepository;
import Blockbuster.Repository.MovieRepository;
import Blockbuster.Service.MovieService;
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
        movie= new Movie(0,"Rambo","Action",1982,7);
        movieDto=new MovieDto("Rambo","Action",1982,7);
        movies= new LinkedList<>();
        movies.add(movie);
        movieDtoSaved= modelMapper.map(movie, MovieDto.class);
        customer= new Customer(0,"Ana", "Lemos","AL","ola",1234,"a@l",movies);
        customerDto= new CustomerDto("Ana", "Lemos","AL","a@l",1234);
    }

    @Test
    void createMovieTest() {

        Movie newMovie= new Movie(0,"ET","Adventure",1980,8);
        MovieDto newMovieDto= new MovieDto("ET","Adventure",1980,8);

        //test when movie doesn't exist in the DB
        when (movieRepository.findMovieByTitle("ET")).thenReturn(Optional.empty());
        when(movieRepository.save(any(Movie.class))).thenReturn(newMovie);
        MovieDto mockedMovie= ms.createMovie(newMovieDto);

        assertEquals(newMovieDto,mockedMovie);

        //when movie exists
        when (movieRepository.findMovieByTitle("Rambo")).thenReturn(Optional.of(movie));
        MovieDto existingMovie= ms.createMovie(movieDto);
        assertNull(existingMovie);

        verify(movieRepository).save(newMovie);
        verify(movieRepository).findMovieByTitle("ET");
        verify(movieRepository).findMovieByTitle("Rambo");
    }

    @Test
    void findMovieByIdTest() {
        //when movie exists
        when(movieRepository.findById(0)).thenReturn(Optional.of(movie));
        MovieDto mockedM= ms.findMovieById(0);

        assertEquals(movieDtoSaved,mockedM);

        //when movie doesn't exist
        when (movieRepository.findById(5)).thenReturn(Optional.empty());
        MovieDto movieNotFound= ms.findMovieById(5);
        assertNull(movieNotFound);

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
        Movie updatedMovie= new Movie(0,"Matrix","Action",1999,8);
        MovieDto updatedMovieDto= new MovieDto("Matrix","Action",1999,8);

        //when the movie exists
        when(movieRepository.findMovieByTitle("Matrix")).thenReturn(Optional.of(updatedMovie)); //when findById gets used then it returns
        when(movieRepository.save(updatedMovie)).thenReturn(updatedMovie); //when a movie is saved by the repo then return that movie
        MovieDto mockedM= ms.updateMovie(updatedMovieDto);
        assertEquals("Matrix",mockedM.getTitle());

        //when the movie doesn't exist
        when (movieRepository.findMovieByTitle("ET")).thenReturn(Optional.empty());
        MovieDto nonExistingMovie=ms.updateMovie(new MovieDto("ET","adventure",1980,8));

        assertNull(nonExistingMovie);

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
        MovieDto mockedM= ms.findMovieByTitle("Rambo");
        assertEquals("Rambo",mockedM.getTitle());

        //check when movie is not present
        when(movieRepository.findMovieByTitle("Matrix")).thenReturn(Optional.empty());
        MovieDto movieNotFound= ms.findMovieByTitle("Matrix");
        assertNull(movieNotFound);

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
        when(movieRepository.findMoviesByReleaseYear(1988)).thenReturn(movies);
        List<MovieDto> yearFound= ms.findMoviesByYear(1988);
        assertEquals(1,yearFound.size());

        //check when movie is not present
        when(movieRepository.findMoviesByReleaseYear(1950)).thenReturn(Collections.emptyList());
        List<MovieDto> yearNotFound= ms.findMoviesByYear(1950);
        assertTrue(yearNotFound.isEmpty());

        verify(movieRepository).findMoviesByReleaseYear(1988);
        verify(movieRepository).findMoviesByReleaseYear(1950);
    }

    @Test
    public void watchMovieTest(){
        //if movie exists
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        MovieDto watchedMovie= ms.movieBeingWatched(1,1);
        assertEquals(movieDto,watchedMovie);

        //if movie doesn't exist

        when(movieRepository.findById(1)).thenReturn(Optional.empty());
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        MovieDto nonExistentMovie= ms.movieBeingWatched(1,1);
        assertNull(nonExistentMovie);

        verify(customerRepository, times(2)).findById(1);
        verify(movieRepository,times(2)).findById(1);
        verify(movieRepository).save(movie);
        verify(customerRepository).save(customer);
    }
}