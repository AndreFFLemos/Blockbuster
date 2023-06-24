package Blockbuster.Service;

import Blockbuster.Model.Customer;
import Blockbuster.Repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.*;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@ComponentScan(basePackages = "Blockbuster")
class CustomerServiceTest {

    @Mock
    CustomerRepository cr;
    @InjectMocks
    CustomerService cs;

    Customer c;

    @BeforeEach
    public void setup(){
        c=new Customer(1,"A","L","Uga","ok", 01234,"a@a");
    }

    @Test
    void createCustomerTest() {
        // test when customer doesn't exist
        when (cr.save(c)).thenReturn(c);
        Optional <Customer> mockedC=cs.createCustomer(c);
        assertEquals(Optional.of(c),mockedC);

        //test when customer already exists
        when(cr.findById(1)).thenReturn(Optional.of(c)); //When the customer already exists, the method will return an empty optional
        Optional<Customer> existingC= cs.createCustomer(c); //this method invokes the cr.findbyid and then returns empty
        assertTrue(existingC.isEmpty());

        verify(cr,times(1)).save(c); //the number of times the cr.save method is really used.
    }

    @Test
    void deleteCustomerByIdTest() {

        when(cr.existsById(1)).thenReturn(true); // simulates the existance of the customer
        doNothing().when(cr).deleteById(1); //when the delete method is invoked, do nothing because it returns a void
       cs.deleteCustomerById(1);
        verify(cr).deleteById(any());

    }

    @Test
    void findCustomerByIdTest() {
        when(cr.findById(any())).thenReturn(of(c));// it returns a container with a possible object
        Optional<Customer> mockedC= cs.findCustomerById(1);

        assertEquals(mockedC,Optional.of(c));
        verify(cr).findById(any());

    }
    @Test
    void findCustomerByFirstNameTest(){
    List <Customer> customersFound= new LinkedList<>();
    customersFound.add(c);

        //check when customer is present
        when(cr.findByFirstName(anyString())).thenReturn(customersFound);
        List <Customer> mockedC= cs.findCustomerByFirstName("A");
        assertTrue(mockedC.size()==1);

        //check when customer is not present
        when(cr.findByFirstName(anyString())).thenReturn(Collections.emptyList());
        List<Customer> customerNotFound= cs.findCustomerByFirstName("T");
        assertTrue(customerNotFound.isEmpty());

        verify(cr, times(2)).findByFirstName(anyString()); //verify that the method was used 2 times
    }

    @Test
    void findCustomerByLastNameTest(){
        List <Customer> customersFound= new LinkedList<>();
        customersFound.add(c);

        //check when customer is present
        when(cr.findByLastName(anyString())).thenReturn(customersFound);
        List <Customer> mockedC= cs.findCustomerByLastName("L");
        assertTrue(mockedC.size()==1);

        //check when customer is not present
        when(cr.findByLastName(anyString())).thenReturn(Collections.emptyList());
        List<Customer> customerNotFound= cs.findCustomerByLastName("T");
        assertTrue(customerNotFound.isEmpty());

        verify(cr, times(2)).findByLastName(anyString()); //verify that the method was used 2 times
    }

    @Test
    void findCustomerByPhone (){

        //check when customer is present
        when(cr.findByPhone(anyInt())).thenReturn(Optional.of(c));
        Optional<Customer> mockedC= cs.findCustomerByPhone(01234);
        assertEquals(Optional.of(c),mockedC);

        //check when customer is not present
        when(cr.findByPhone(anyInt())).thenReturn(Optional.empty());
        Optional<Customer> customerNotFound= cs.findCustomerByPhone(11111);
        assertTrue(customerNotFound.isEmpty());

        verify(cr, times(2)).findByPhone(anyInt()); //verify that the method was used 2 times
    }
    @Test
    void findCustomerByEmail (){

        //check when customer is present
        when(cr.findByEmail(anyString())).thenReturn(Optional.of(c));
        Optional<Customer> mockedC= cs.findCustomerByEmail("a@a");
        assertEquals(Optional.of(c),mockedC);

        //check when customer is not present
        when(cr.findByEmail(anyString())).thenReturn(Optional.empty());
        Optional<Customer> customerNotFound= cs.findCustomerByEmail("b@b");
        assertTrue(customerNotFound.isEmpty());

        verify(cr, times(2)).findByEmail(anyString()); //verify that the method was used 2 times
    }

@Test
void findAll (){
    Customer c1=new Customer();
    Customer c2=new Customer();
    Customer c3=new Customer();

    List <Customer> customers= new LinkedList<>();
    customers.add(c1);
    customers.add(c2);
    customers.add(c3);

    when (cr.findAll()).thenReturn(customers);
    List<Customer> mockCustomers= cs.findAll();

    assertTrue(mockCustomers.size()==3);
    verify(cr).findAll();
}
    @Test
    void updateCustomerTest() {
        c.setId(1);
        c.setFName("B");
        c.setLName("P");

        when(cr.findById(1)).thenReturn(Optional.of(c)); //guarantee that the method returns an existing customer
        when(cr.save(any(Customer.class))).thenReturn(c);
        Customer mockC= cs.updateCustomer(c);

        assertEquals("B",mockC.getFName());
        assertEquals("P",mockC.getLName());

        verify(cr).save(any(Customer.class));
    }
}