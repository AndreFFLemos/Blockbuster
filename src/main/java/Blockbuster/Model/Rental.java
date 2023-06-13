package Blockbuster.Model;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rental {
    private int id;
    private Movie movie;
    private LocalDate start;
    private Customer customer;

}