package Blockbuster.Service;

import Blockbuster.Model.CustomerTest;
import Blockbuster.Model.MovieTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class StoreTestTest {

    private int id;
    private List<CustomerTest> customerTests;
    private CustomerTest customerTest;
    private List<MovieTest> movieTests;
    private MovieTest movieTest;
    private Blockbuster.Model.StoreTest storeTest;
    private List <Fine> customerFines;
    private Fine customerFine;
    private boolean hasMovies=false;

    @Before
    public void setup() {
        customerTest = new CustomerTest();
        customerTests = new LinkedList<>();
        customerTests.add(customerTest);

        movieTest = new MovieTest(1,"Zombie",3,5.0);
        movieTests = new LinkedList<>();
        movieTests.add(movieTest);

        storeTest = new Blockbuster.Model.StoreTest(customerTests, movieTests);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CustomerTest> getCustomers() {
        return customerTests;
    }

    public void setCustomers(List<CustomerTest> customerTests) {
        this.customerTests = customerTests;
    }

    public List<MovieTest> getMovies() {
        return movieTests;
    }

    public void setMovies(List<MovieTest> movieTests) {
        this.movieTests = movieTests;
    }



@Test
public void dontAddCustomerAlreadyInListTest(){

        //action
    boolean result= storeTest.createCustomer(customerTests, customerTest);

    //verification
    assertFalse(result);
    assertTrue(customerTests.size()== storeTest.getCustomers().size());
}

@Test
public void checkCustomerInfoTest(){
        //action
        String info= customerController.checkCustomerInfo(customerTests, customerTest);

        //validation
    assertEquals(info, customerTest.toString());
}
@Test
public void checkCustomerInfoDoesntExistTest(){
        //scene
        CustomerTest c2=new CustomerTest(6,"Pedrinho","B Street", 7666923);
        //action
        String info= storeTest.checkCustomerInfo(customerTests,c2);

        //verification
        assertTrue("Customer doesn't exist"==info);
}

@Test
public void editCustomerTest(){
        //scene
        customerTest.setName("Duarte");
        customerTest.setContact(7666924);
        customerTest.setAddress("C street");

        //action
        String result= storeTest.editCustomer(customerTests, customerTest);

        //verification
    assertEquals("Duarte", customerTest.getName());
    assertNotEquals("Ana", customerTest.getName());
}


@Test
public void dontEditCustomerIfDoesntExistTest(){
        //scene
        CustomerTest c5= new CustomerTest(7,"Roger","D street",7666925);
        c5.setName("Antonio");
        c5.setAddress("F street");

        //action
        String result= storeTest.editCustomer(customerTests,c5);

        //verification
        assertEquals(result,"Customer doesn't exist");
}

@Test
public void eliminateCustomerTest(){

        //action
        int size= customerTests.size();
        boolean result= storeTest.eliminateCustomer(customerTests, customerTest);

        //verification
        assertTrue(result);

}
@Test
public void cantEliminateCustomerIfDoesntExistTest(){

        //scene
    CustomerTest c9=new CustomerTest(9,"Afonso","T Street",7666926);

    //action
    boolean result= storeTest.eliminateCustomer(customerTests,c9);

    //verification
    assertFalse(result);

    }

    @Test
    public void cantEliminateCustomerIfHasDebtTest(){
        //scene
        CustomerTest c1=new CustomerTest(2,"Pedro","D Street",7666927);
        Fine f= new Fine(2,LocalDate.now(),5.0);
        List <Fine> fs= new LinkedList<>();
        fs.add(f);
        c1.setFines(fs);
        customerTests.add(c1);

        //action
        boolean result= storeTest.eliminateCustomer(customerTests,c1);

        //verification
        assertFalse(result);
    }

    @Test
    public void cantEliminateCustomerIfHasMoviesTest(){
        //scene


        //action
        boolean result= storeTest.eliminateCustomer(customerTests, customerTest);

        //verification
        assertEquals(true,result);
    }
    @Test
    public void customerHasDebtsTest(){
        //scene
        CustomerTest customerTest1 = new CustomerTest(2,"Pedro","Shrubs Street", 11111112); //created a new C
        Fine customer1Fine1= new Fine(1,LocalDate.now(),3.0); //created a new fine
        List <Fine> customer1Fines=new LinkedList<>(); //created a list of Fines
        customer1Fines.add(customer1Fine1); //added the fine to the list of fines
        customerTest1.setFines(customer1Fines); //injected the list of fines to the respective customer
        customerTests.add(customerTest1); //added that customer to the list of customers

        //action
        boolean result= storeTest.checkIfCustomerHasFines(customerTests, customerTest1);

        //verification
        assertTrue(result);
    }

    @Test
    public void checkCustomersRentalHistoryTest(){

    }


    @Test
    public void checkIfCustomerHasUndeliveredMoviesTest(){

    }

    @Test
    public void insertNewMovieTest(){
        //scene
        MovieTest movieTest1 = new MovieTest(2,"Matrix",4, 6.0);

        //action
        boolean result= storeTest.insertNewMovie(movieTests, movieTest1);

        //verification
        assertTrue(result);
        assertTrue(movieTests.size()==2);

    }
    @Test
    public void dontInsertMovieThatAlreadyExistsTest(){
        //scene
        MovieTest movieTest1 = new MovieTest(2,"Matrix",4, 6.0);
        movieTests.add(movieTest1);
        //action
        boolean result= storeTest.insertNewMovie(movieTests, movieTest1);

        //verification
        assertFalse(result);
        assertTrue(movieTests.size()==2);
    }

    @Test
    public void checkMovieInfoTest(){
        //action
        String m= storeTest.checkMovieInfo(movieTest);

        //verification
        assertEquals(movieTest.toString(),m);
    }
@Test
    public void checkIfMovieExistsTest(){
        //scene
        MovieTest m= new MovieTest("ET");

        //action
        boolean result= storeTest.movieAlreadyExists(m);

        //verification
        assertFalse(result);
}
@Test
    public void movieInfoTest(){
        //action
    String m= storeTest.checkMovieInfo(movieTest);

    //verification
    assertEquals(movieTest.toString(),m);
}
    @Test
    public void movieInfoWeDontHaveTest(){
       //scene
        MovieTest m= new MovieTest("uga");
        //action
        String s= storeTest.checkMovieInfo(m);

        //verification
        assertEquals("We don't have that movie",s);
    }

    @Test
    public void editMovieInfoTest(){
        //scene
        movieTest.setMoviePrice(2.0);
        movieTest.setnMovieCopies(2);
        //action
        MovieTest t= storeTest.editMovieInfo(movieTests, movieTest);

        //verification
        assertTrue(movieTest.getMoviePrice()==t.getMoviePrice());
        assertTrue(movieTest.getnMovieCopies()==t.getnMovieCopies());
    }

    @Test
    public void cantEditMovieWeDontHaveTest(){
        //scene
        MovieTest m=new MovieTest("ET");
        //action
        MovieTest test= storeTest.editMovieInfo(movieTests,m);

        assertNull(test);
    }

    @Test
    public void eliminateMovie(){
        //scene
        MovieTest m=new MovieTest(3,"ET", 5, 7.0);
        movieTests.add(m);

        //action
        boolean t= storeTest.eliminateMovie(movieTests,m);

        //verification
        assertTrue(t);
        assertEquals(movieTests.size(),1);
    }

    @Test
    public void cantEliminateMovieThatDoesntExist(){
        //scene
        MovieTest m= new MovieTest("ET");
        //action
        boolean t= storeTest.eliminateMovie(movieTests,m);

        //verification
        assertFalse(t);
        assertTrue(movieTests.size()==1);
    }

}
