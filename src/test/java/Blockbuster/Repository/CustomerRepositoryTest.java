package Blockbuster.Repository;

import Blockbuster.Model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import java.time.LocalDate;
import java.util.NoSuchElementException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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

    @Before
    public void setup() {

        c1 = new Customer(1, "Ana", "Trees Street", 12345678, "ana@gmail.com");
        c2 = new Customer(2, "Ze", "Shrub street", 11111111, "ze@gmail.com");

        customers = new HashMap<>();
        customers.put(1, c1);
        customers.put(2, c2);

        m1 = new Movie(1, "Matrix", 4, 5.0);
        m2 = new Movie(2, "Rambo", 4, 4.0);
        m3 = new Movie(3, "ET", 3, 7.0);
        LocalDate dateS = LocalDate.of(2023, 05, 10);

        rental1 = new Rental(1, m1, dateS);
        rental2 = new Rental(2, m3, dateS);
        Rental rental3 = new Rental(3, m2, dateS);
        customer1Rentals=new HashMap<>();
        customer2Rentals=new HashMap<>();
        customer1Rentals.put(1, rental1);
        customer1Rentals.put(2, rental2);
        customer2Rentals.put(1, rental3);

        customers.get(1).setCustomerRentals(customer1Rentals);
        customers.get(2).setCustomerRentals(customer2Rentals);

        cr = new CustomerRepository(customers);

    }

    @Test
    public void checkIfCustomerHasPersonalFieldsFilled() {
        //scene
        Customer c3 = new Customer(3, "Luis", "Vine street", 11111113, "luis@gmail.com");
        customers.put(3, c3);

        //action
        String name = c3.getName();
        String address = c3.getAddress();
        int id = c3.getId();
        int contact = c3.getContact();
        String email = c3.getEmail();

        //verification
        Assert.assertTrue(name.equals("Luis"));
        Assert.assertTrue(address.equals("Vine street"));
        Assert.assertTrue(contact == 11111113);
        Assert.assertTrue(id == 3);
        Assert.assertTrue(email.equals("luis@gmail.com"));
    }

    @Test
    public void testCreateCustomer() {
        //scene
        Customer c3 = new Customer(3, "Luis", "Vine street", 11111113, "luis@gmail.com");

        //action
        boolean result = cr.createCustomer(c3);

        //verification
        assertTrue(result);
        assertEquals(customers.get(c3.getId()).getName(), "Luis");
        assertTrue(customers.containsValue(c3));
    }

    @Test
    public void testFindCustomer() {
        //scene

        //action
        Customer result = cr.findCustomer(c1);

        //verification
        assertEquals(c1.getName(), result.getName());
    }

    @Test
    public void testDeleteCustomer() {
        // Scene
        Customer c3 = new Customer(3, "Pedro", "Pedro street", 11111114, "pedro@gmail.com");

        // Action
        boolean result = cr.deleteCustomer(c2);
        // Verification
        assertTrue(result);

        assertNull(customers.get(c2.getId())); // Ensure that the customer with ID 2 is removed from the map
        assertEquals(1, customers.size()); // Ensure that the map size is now 1
    }

    @Test
    public void testUpdateCustomer() {
        //Scene
        Customer updatedC1 = new Customer(1, "Duarte", "Vine Street", 11111115, "duarte@gmail.com");
        Customer nonExistent = new Customer(4, "Vasco", "Avenue", 11111115, "vasco@gmail.com");

        //Action and Verification
        boolean result = cr.updateCustomer(updatedC1);
        assertTrue(result);
        assertEquals("Duarte", cr.getCustomerById(1).getName());

        try {
            cr.updateCustomer(nonExistent);
            fail("The expected NoSuchElementException was not thrown");
        } catch (NoSuchElementException e) {
            //if the exception was thrown, check if the message was the expected
            assertEquals("Customer doesn't exist!", e.getMessage());
        }
    }

    //There seems to be an issue with the 'expected' annotation, it's making the tests block
    @Test   // Tested a lot of methods in this test method to compare with unit testing per se
    public void testRepositoryGetMethods() {
        //Scene
        Customer c4 = new Customer(4, "Vasco", "Avenue", 11111114, "vasco@gmail.com");

        boolean hasFines = true;
        c2.setCustomerHasFines(hasFines);

        //Action and Verification
        String theName = cr.getCustomerName(c1);
        String theAddress = cr.getCustomerAddress(c1);
        int theContact = cr.getCustomerContact(c1);
        cr.getCustomerEmail(c1);
        cr.getIfCustomerHasFines(c1);
        cr.getIfCustomerHasFines(c2);
        assertEquals("ana@gmail.com", cr.getCustomerEmail(customers.get(1)));
        assertEquals(12345678, theContact);
        assertEquals("Trees Street", theAddress);
        assertEquals("Ana", theName);
        assertFalse(cr.getIfCustomerHasFines(c1));
        assertTrue(cr.getIfCustomerHasFines(c2));

        try {
            cr.ifCustomerDoesntExist(c4);
            fail("Was expecting a NoelementException but it wasn't thrown");
        } catch (NoSuchElementException e) {
            assertEquals("Customer doesn't exist!", e.getMessage());
        }

    }

    @Test
    public void test() {
        //Scene
        Customer c4 = new Customer(4, "Vasco", "Avenue", 11111114, "vasco@gmail.com");

        boolean hasFines = true;
        c2.setCustomerHasFines(hasFines);

        //Action and Verification

        cr.getIfCustomerHasFines(c1);
        cr.getIfCustomerHasFines(c2);

        assertFalse(cr.getIfCustomerHasFines(c1));
        assertTrue(cr.getIfCustomerHasFines(c2));

        try {
            cr.ifCustomerDoesntExist(c4);
            fail("Was expecting a NoelementException but it wasn't thrown");
        } catch (NoSuchElementException e) {
            assertEquals("Customer doesn't exist!", e.getMessage());
        }
    }

    @Test
    public void testGetCustomerRentals() {
        //Scene
        Customer c4 = new Customer(4, "Vasco", "Avenue", 11111114, "vasco@gmail.com");

        //Action
        cr.getCustomerRentals(c1);
        cr.getCustomerRentals(c4);

        //Verification
        assertEquals(c1.getCustomerRentals(), cr.getCustomerRentals(c1));

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            cr.getCustomerRentals(c4);
        });
        assertEquals("Customer doesn't exist!", exception.getMessage());
    }

}