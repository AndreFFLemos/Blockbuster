package Blockbuster.Service;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;
import Blockbuster.Model.Rental;

import java.util.Map;

public class StoreService implements StoreServiceInterface {
    @Override
    public void deleteAllMoviesInStock(Map<Integer, Movie> allMovies) {

    }

    @Override
    public void deleteCustomerHistoryRentals(Customer customer, Map<Integer, Rental> allRentals) {

    }

    @Override
    public void deleteStoreCustomers(Map<Integer, Customer> allCustomers) {

    }
}
