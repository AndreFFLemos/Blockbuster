package Blockbuster.Model;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name="movie")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
    @Column(name="rentaldate")
    private LocalDate rentalDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer")
    private Customer customer;

}