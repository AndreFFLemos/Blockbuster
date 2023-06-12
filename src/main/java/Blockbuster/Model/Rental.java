package Blockbuster.Model;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Rental {
    private int id;
    private Movie movie;
    private LocalDate start;
    private LocalDate end;
    private double fineAmount;
    private Customer customer;

    public Rental(int id, Movie movie, LocalDate start) {
        this.id = id;
        this.movie = movie;
        this.start = start;
        this.end = end;
        this.fineAmount = fineAmount;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return id == rental.id && Double.compare(rental.fineAmount, fineAmount) == 0 && movie.equals(rental.movie) && start.equals(rental.start) && end.equals(rental.end) && Objects.equals(customer, rental.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, start, end, fineAmount, customer);
    }
}

