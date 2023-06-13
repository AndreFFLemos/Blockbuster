package Blockbuster.Service;

import Blockbuster.Model.*;
import Blockbuster.Repository.CustomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class CustomerRepositoryTest {

    private Customer c1;
    private Customer c2;
    private Map<Integer, Customer> customers;
    private Movie m1;
    private Movie m2;
    private Movie m3;
    private Rental rental1;
    private Rental rental2;
    private Map<Integer, Rental> customer1Rentals;
    private Map<Integer, Rental> customer2Rentals;
    private Map<Integer,Movie> movies;
    private CustomerRepository cr;

    @BeforeAll
    public void setup() {

        c1 = new Customer();
        c2 = new Customer();

        customers = new HashMap<>();
        customers.put(1, c1);
        customers.put(2, c2);

        m1 = new Movie();
        m2 = new Movie();
        m3 = new Movie();
        LocalDate dateS = LocalDate.of(2023, 05, 10);

        rental1 = new Rental();
        rental2 = new Rental();
        Rental rental3 = new Rental();
        customer1Rentals=new HashMap<>();
        customer2Rentals=new HashMap<>();
        customer1Rentals.put(1, rental1);
        customer1Rentals.put(2, rental2);
        customer2Rentals.put(1, rental3);


    }

    @Test
    public void checkIfCustomerHasPersonalFieldsFilled() {
        //scene
        Customer c3 = new Customer();
        customers.put(3, c3);

        //action
        String name = c3.getFName();


        String phone = c3.getPhone();
        String email = c3.getEmail();

        //verification

    }

    @Test
    public void testCreateCustomer() {
        //scene
        Customer c3 = new Customer();

        //action


        //verification

    }

    @Test
    public void testFindCustomer() {
        //scene

        //action
        Customer result ;

        //verification

    }

    @Test
    public void testDeleteCustomer() {
        // Scene
        Customer c3 = new Customer();

        // Action

        // Verification

    }

    @Test
    public void testUpdateCustomer() {
        //Scene
        Customer updatedC1 = new Customer();
        Customer nonExistent = new Customer();

        //Action


        // Verification


    }

    //There seems to be an issue with the 'expected' annotation, it's making the tests block
    @Test   // Tested a lot of methods in this test method to compare with unit testing per se
    public void testRepositoryGetMethods() {
        //Scene
        Customer c4 = new Customer();

        boolean hasFines = true;


        //Action and Verification


    }

    @Test
    public void test() {
        //Scene
        Customer c4 = new Customer();

        boolean hasFines = true;


        //Action and Verification


    }

    @Test
    public void testGetCustomerRentals() {
        //Scene
        Customer c4 = new Customer();

        //Action


        //Verification


    }

}