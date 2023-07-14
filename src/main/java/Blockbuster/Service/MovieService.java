package Blockbuster.Service;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;
import Blockbuster.Repository.CustomerRepository;
import Blockbuster.Repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieService implements MovieServiceInterface{

    private MovieRepository movieRepository;
    private CustomerRepository customerRepository;
    ModelMapper modelMapper;

    @Autowired
    public MovieService(MovieRepository movieRepository, CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.customerRepository=customerRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<MovieDto> createMovie(MovieDto movieDto) {
        //if the movie is in the DB then the method will return an empty container meaning no saved movie
        Optional <Movie> existingMovie= movieRepository.findMovieByTitle(movieDto.getTitle());
        if (existingMovie.isPresent()) {
            return Optional.empty();
        }

        //convert the customerDto instance to a POJO instance and save the latter to the customer variable
        Movie movie= modelMapper.map(movieDto, Movie.class);
        //tell the repository to persist the customer instance and save that instance on the customer variable
        movie= movieRepository.save(movie);

        //convert that persisted instance back in to a DTO object
        MovieDto movieDto1= modelMapper.map(movie,MovieDto.class);

        return  Optional.of(movieDto1);
    }

    public Optional<MovieDto> findMovieById(int id) {
        Optional <Movie> movie= movieRepository.findById(id);

        if (movie.isEmpty()){
            return Optional.empty();
        }
        MovieDto movieDto= modelMapper.map(movie,MovieDto.class);
        return Optional.of(movieDto);
    }

    public List<MovieDto> findAllMovies(){
        List <Movie>movies = movieRepository.findAll();

        return movies.stream()
                .map(movie -> modelMapper.map(movies,MovieDto.class))
                .collect(Collectors.toList());
    }

    public Optional<MovieDto> updateMovie(MovieDto movieDto) {
           Optional <Movie> existingMovie= movieRepository.findMovieByTitle(movieDto.getTitle());

           if (existingMovie.isEmpty()){
               return Optional.empty();
           }

           //because the movie exists in the DB, repo delete it
            movieRepository.deleteByTitle(movieDto.getTitle());
           //convert the movieDto to movie and then repo persist it
            Movie movie= modelMapper.map(movieDto,Movie.class);
            movie= movieRepository.save(movie);
            //convert that persisted movie to movie Dto and return it
            MovieDto movieDto1=modelMapper.map(movie,MovieDto.class);

            return Optional.of(movieDto1);
    }

    public void deleteMovieById(int id) {
        Optional<Movie> existingMovie= movieRepository.findById(id);
        if (existingMovie.isEmpty()){
            System.out.println("Movie doesn't exist");
        }

        movieRepository.deleteById(existingMovie.get().getId());
    }

    @Override
    public Optional<MovieDto> findMovieByTitle(String title) {
        Optional <Movie> movie= movieRepository.findMovieByTitle(title);
        if (movie.isEmpty()) {
            return Optional.empty();
        }

        MovieDto movieDto= modelMapper.map(movie,MovieDto.class);

        return Optional.of(movieDto);

    }

    @Override
    public List<MovieDto> findMoviesByYear(int year) {
        List <Movie> movies= movieRepository.findMoviesByYear(year);
        if (movies.isEmpty()){
            return Collections.emptyList();
        }


        return movies.stream()
                .map(movie -> modelMapper.map(movie,MovieDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> findMoviesByGenre(String genre) {
        List <Movie> movies= movieRepository.findMoviesByGenre(genre);
        if (movies.isEmpty()){
            return Collections.emptyList();
        }
        return movies.stream()
                .map(movie -> modelMapper.map(movie,MovieDto.class))
                .collect(Collectors.toList());    }

    @Override
    public Optional<MovieDto> watchMovie(CustomerDto customerDto, MovieDto movieDto) {

            Optional<Movie> existingMovie = movieRepository.findMovieByTitle(movieDto.getTitle());
            Optional<Customer> existingCustomer = customerRepository.findByPhone(customerDto.getPhone());

            if (existingMovie.isPresent()) {

                Movie movie = existingMovie.get(); //get the movie out of the optional
                Customer customer = existingCustomer.get(); //get the customer out of the optional

                // Add movie to the list of movies watched by a customer
                customer.getWatchedMovies().add(movie);

                // Add the customer to the list of customers that watched the movie
                movie.getViewers().add(customer);

                // Save the updated entities (their lists) back to the database
                movieRepository.save(movie);
                customerRepository.save(customer);

                MovieDto movieToMovieDto= modelMapper.map(movie, MovieDto.class);
                return Optional.of(movieToMovieDto);
            } else {
                return Optional.empty();
            }
    }

}
