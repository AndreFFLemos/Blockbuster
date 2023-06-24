package Blockbuster.Repository;

import Blockbuster.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    List<Movie> findMovieByTitle (String title);
    List <Movie> findMoviesByYear (int year);
    List <Movie> findMoviesByGenre (String genre);
    List<Movie> findMoviesByPrice (int price);

}
