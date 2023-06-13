package Blockbuster.Bootstrap;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;
import Blockbuster.Model.Rental;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Component
public class Bootstrap implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        Customer customer = new Customer();
        Movie movie = new Movie();
        Movie movie1 =new Movie();

        Map<Integer,Customer> customers=new HashMap<>();
        customers.put(1,customer);
        Map<Integer, Rental> customerRentals = new HashMap<>(); //the list of titles rented by each customer
        Map<Integer,Movie> moviesInStore=new HashMap<>();
        moviesInStore.put(1,movie);
        moviesInStore.put(2,movie1);

        List<Movie> moviesToRent= new LinkedList<>();
        moviesToRent.add(movie1);


        Map<Integer,Movie> storeRentals=new HashMap<>();
        storeRentals.put(1,movie1);


    }
}
