package Blockbuster.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="genre")
    private String genre;
    @Column(name="releaseyear")
    private int releaseYear;
    @Column (name="rating")
    private double rating;
    //the mappedBy attribute indicates that the relationship is managed by the customer side
    @ManyToMany(mappedBy = "watchedMovies")
    private List<Customer> viewers= new ArrayList<>();

    public Movie(int id, String title, String genre, int releaseYear, double rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }
}