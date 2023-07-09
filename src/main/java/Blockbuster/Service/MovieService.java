package Blockbuster.Service;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;
import Blockbuster.Repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieService implements MovieServiceInterface{

    private MovieRepository mr;
    ModelMapper modelMapper;

    @Autowired
    public MovieService(MovieRepository mr, ModelMapper modelMapper) {
        this.mr = mr;
        this.modelMapper = modelMapper;
    }

    public Optional<MovieDto> createMovie(MovieDto movieDto) {
        //if the movie is in the DB then the method will return an empty container meaning no saved movie
        Optional <Movie> movie= mr.findMovieByTitle(movieDto.getTitle());
        if (movie.isPresent()) {
            return Optional.empty();
        }

        //convert the customerDto instance to a POJO instance and save the latter to the customer variable
        Movie movie1= modelMapper.map(movieDto, Movie.class);
        //tell the repository to persist the customer instance and save that instance on the customer variable
        movie1= mr.save(movie1);

        //convert that persisted instance back in to a DTO object
        MovieDto movieDto1= modelMapper.map(movie1,MovieDto.class);

        return  Optional.of(movieDto1);
    }

    public Optional<MovieDto> findMovieById(int id) {
        Optional <Movie> movie= mr.findById(id);

        if (movie.isEmpty()){
            return Optional.empty();
        }
        MovieDto movieDto= modelMapper.map(movie,MovieDto.class);
        return Optional.of(movieDto);
    }

    public List<MovieDto> findAllMovies(){
        List <Movie>movies = mr.findAll();

        return movies.stream()
                .map(movie -> modelMapper.map(movies,MovieDto.class))
                .collect(Collectors.toList());
    }

    public Optional<MovieDto> updateMovie(MovieDto movieDto) {
           Optional <Movie> existingMovie= mr.findMovieByTitle(movieDto.getTitle());

           if (existingMovie.isEmpty()){
               return Optional.empty();
           }

           //because the movie exists in the DB, repo delete it
            mr.deleteByTitle(movieDto.getTitle());
           //convert the movieDto to movie and then repo persist it
            Movie movie= modelMapper.map(movieDto,Movie.class);
            movie=mr.save(movie);
            //convert that persisted movie to movie Dto and return it
            MovieDto movieDto1=modelMapper.map(movie,MovieDto.class);

            return Optional.of(movieDto1);
    }

    public void deleteMovieById(int id) {
        Optional<Movie> existingMovie= mr.findById(id);
        if (existingMovie.isEmpty()){
            System.out.println("Movie doesn't exist");
        }

        mr.deleteById(existingMovie.get().getId());
    }

    @Override
    public Optional<MovieDto> findMovieByTitle(String title) {
        Optional <Movie> movie= mr.findMovieByTitle(title);
        if (movie.isEmpty()) {
            return Optional.empty();
        }

        MovieDto movieDto= modelMapper.map(movie,MovieDto.class);

        return Optional.of(movieDto);

    }

    @Override
    public List<MovieDto> findMoviesByYear(int year) {
        List <Movie> movies= mr.findMoviesByYear(year);
        if (movies.isEmpty()){
            return Collections.emptyList();
        }


        return movies.stream()
                .map(movie -> modelMapper.map(movie,MovieDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> findMoviesByGenre(String genre) {
        List <Movie> movies= mr.findMoviesByGenre(genre);
        if (movies.isEmpty()){
            return Collections.emptyList();
        }
        return movies.stream()
                .map(movie -> modelMapper.map(movie,MovieDto.class))
                .collect(Collectors.toList());    }

    @Override
    public List<MovieDto> findMoviesByPrice(int price) {
        List <Movie> movies= mr.findMoviesByPrice(price);
        if (movies.isEmpty()){
            return Collections.emptyList();
        }
        return movies.stream()
                .map(movie -> modelMapper.map(movie,MovieDto.class))
                .collect(Collectors.toList());
    }
}
