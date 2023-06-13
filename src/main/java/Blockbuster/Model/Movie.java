package Blockbuster.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {

    private int id;
    private String title;
    private String genre;
    private int releaseYear;
    private float rating;
    private double moviePrice;

}