package Blockbuster.Service;

import Blockbuster.Model.*;
import Blockbuster.Model.CustomerTest;
import Blockbuster.Model.MovieTest;
import Blockbuster.Model.StoreTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class RentalServiceTest {

    private CustomerTest customerTest;
    private List <CustomerTest> customerTests;
    private MovieTest movieTest;
    private List<MovieTest> movieTests;
    private List <MovieTest> moviesToRent;
    private LocalDate startRental;
    private LocalDate endRental;
    private Fine customerFine;
    private List <Fine> customerFines;
    private RentalTest rental;
    private List <RentalTest> rentals;
    private StoreTest storeTest;

    @Before
    public void setup(){
        customerTest = new CustomerTest(1,"Ana","Trees Street", 12345678);
        customerFine=new Fine(1, LocalDate.now(),4.0);
        customerFines= new LinkedList<>();
        customerFines.add(customerFine);

        customerTests = new LinkedList<>();
        customerTests.add(customerTest);

        movieTest = new MovieTest(1,"Zombie",4,5.0);
        moviesToRent = new LinkedList<>();
        moviesToRent.add(movieTest);
        startRental= LocalDate.now();
        endRental=LocalDate.now().plusDays(1);
        rental=new RentalTest();

        storeTest =new StoreTest(customerTests, movieTests);
    }

    @Test
    public void rentalTest(){
        //scene
        MovieTest movieTest1 =new MovieTest(2,"ET",5,6.0);

        //action
        double m= rental.renting(customerTest, movieTest1,startRental);

        //verification
        assertTrue(m==6);
        assertTrue(movieTest1.getnMovieCopies()==4);
        assertTrue(customerTest.getCustomerRentals().contains(movieTest1));
    }
    @Test
    public void rentalDeliveryTest(){
        //scene
        customerTest.setCustomerRentals(moviesToRent);

        //action
        double price= rental.rentalDelivery(customerTest, movieTest,startRental,endRental);

        //verification
        assertTrue(price==0);
        assertTrue(movieTest.getnMovieCopies()==5);
        assertTrue(customerTest.getNumberOfMoviesDelivered()==1);

    }
@Test
public void cantDeliverMovieNotRented(){
      //scene
    MovieTest m4=new MovieTest(4,"Die Hard",3,5.0);

    try {
        //action
        double error = rental.rentalDelivery(customerTest, m4, startRental, endRental);
        fail("Expected an Illegal Argument to be thrown");
    } catch (IllegalArgumentException e){
        //verification
        assertEquals("Movie wasn't rented by the customer",e.getMessage());
    }
}
    @Test(expected = IllegalArgumentException.class)
    public void numberDaysBetweenStartandEndTest(){
        // action
        int days= rental.daysBetweenStartandDelivery(startRental,endRental);
        int reverse=rental.daysBetweenStartandDelivery(endRental,startRental);

        //verification
        assertEquals(1,days);
        assertEquals("Return date before start",reverse);
    }

    @Test
    public void rentalFineTest(){
        //scene
        endRental=LocalDate.now().plusDays(4);

        //action
        double f= rental.rentalFine(startRental,endRental, movieTest);

        //verification
        assertTrue(f==20);
    }
@Test
    public void moreThanOneMovieRentalTest(){
        //scene
        MovieTest movieTest1 =new MovieTest(7,"T2",3,5.0);
        moviesToRent.add(movieTest1);

        //action
        double totalCost= rental.moreThanOneMoviePerRental(customerTest,moviesToRent,startRental);

        //verification
        List <MovieTest> check= customerTest.getCustomerRentals();
        assertTrue(10.0==totalCost);
        assertEquals(2,check.size());
        //Assert.assertArrayEquals(moviesToRent.toArray(),customer.getCustomerRentals().toArray());
        assertTrue(customerTest.getNumberOfMoviesRented()==2);
}

}
