package Blockbuster.Model;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;
import Blockbuster.Model.Rental;

import java.util.*;

public class Store {


    private int id;
    private Map <Integer,Customer> customers;
    private Map<Integer,Movie> movies;
    private Map<Integer,Movie> storeRentals;

    public Store(int id, Map<Integer, Customer> customers, Map<Integer, Movie> movies, Map<Integer,Movie>storeRentals) {
        this.id = id;
        this.customers = customers;
        this.movies = movies;
        this.storeRentals = storeRentals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Map<Integer, Customer> customers) {
        this.customers = customers;
    }

    public Map<Integer, Movie> getMovies() {
        return movies;
    }

    public void setMovies(Map<Integer, Movie> movies) {
        this.movies = movies;
    }

    public Map<Integer, Movie> getStoreRentals() {
        return storeRentals;
    }

    public void setStoreRentals(Map<Integer, Movie> storeRentals) {
        this.storeRentals = storeRentals;
    }
}




