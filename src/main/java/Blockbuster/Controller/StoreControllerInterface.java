package Blockbuster.Controller;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;
import Blockbuster.Model.Rental;

import java.util.Map;

public interface StoreControllerInterface {
    void deleteAllMoviesInStock(Map<Integer, Movie> allMovies);
    void deleteCustomerHistoryRentals(Customer customer, Map<Integer, Rental> allRentals);
    void deleteStoreCustomers(Map<Integer,Customer> allCustomers);
}
