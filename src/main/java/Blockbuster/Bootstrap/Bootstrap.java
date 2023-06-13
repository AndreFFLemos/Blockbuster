package Blockbuster.Bootstrap;

import org.springframework.boot.CommandLineRunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Bootstrap implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        Customer customer = new Customer(1,"Ana","Trees Street", 12345678,"ana@mail.com");
        Movie movie = new Movie(1,"Zombie",5,5);
        Movie movie1 =new Movie(2,"ET", 5,7);

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

        Store store= new Store(1,customers,moviesInStore,storeRentals);

    }
}
