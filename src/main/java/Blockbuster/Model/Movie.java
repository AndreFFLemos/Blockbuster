package Blockbuster.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

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
    private float rating;
    @Column(name="cost")
    private double cost;
    //mappedBy indica que o atributo movie da classe rental é a foreignkey e o cascade indica que uma
    // alteração em dado filme irá terrepercussoes em todos os rentals em que
    // esse filme estiver associado
    @OneToMany(mappedBy = "movie",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Rental> rentals;

}