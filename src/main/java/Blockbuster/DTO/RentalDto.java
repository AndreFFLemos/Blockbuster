package Blockbuster.DTO;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;

import java.time.LocalDate;

public class RentalDto {
    private Movie movie;
    private Customer customer;
    private LocalDate rentalDate;

    public RentalDto(Movie movie, Customer customer, LocalDate rentalDate) {
        this.movie = movie;
        this.customer = customer;
        this.rentalDate = rentalDate;
    }
    public RentalDto(){

    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }
}