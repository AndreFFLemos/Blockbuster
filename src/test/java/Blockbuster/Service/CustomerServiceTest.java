package Blockbuster.Service;

import Blockbuster.Model.Customer;
import Blockbuster.Repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
        c=new Customer(1,"A","L","Uga","ok", "1234","a@a");
    }

    @Test
    void createCustomerTest() {

        when (cr.save(c)).thenReturn(c);
        Customer mockedC=cs.createCustomer(c);
        System.out.println(mockedC);

        assertEquals(c,mockedC);
        verify(cr).save(c);
    }

    @Test
    void deleteCustomerByIdTest() {
        when(cr.existsById(1)).thenReturn(true); // simulates the existance of the customer
        doNothing().when(cr).deleteById(1); //when the delete method is invoked, do nothing because it returns a void

        //action
       cs.deleteCustomerById(1);

       //verification (verifies if the method was used or not)
       verify(cr).deleteById(any());
    }

    @Test
    void findCustomerByIdTest() {
        when(cr.findById(any())).thenReturn(of(c));// it returns a container with a possible object

        Customer mockedC= cs.findCustomerById(1);

        assertEquals(mockedC,c);
        verify(cr).findById(any());

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