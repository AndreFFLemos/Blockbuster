package Blockbuster.Service;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.Model.Customer;
import Blockbuster.Repository.CustomerRepository;
import Blockbuster.Validation.CustomerValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.*;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository cr;
    @InjectMocks
    CustomerService cs;
    private Validator validator=new CustomerValidator();
    CustomerDto customerDto;
    Customer customer;
    ModelMapper modelMapper;
    List <Customer> customersFound;
    CustomerDto customerDtoSaved;


    @BeforeEach
    public void setup(){
        modelMapper=new ModelMapper();
        //I'm passing this modelmapper instance to the customer service instance so that the modelmapper is not null
        //I'm using new because the model mapper is not a mock
        cs = new CustomerService(cr, modelMapper);
        customerDto=new CustomerDto("A","L","AL","a@l",1234);
        customer=new Customer(0,"A","L","AL",null,1234,"a@l");
        customerDtoSaved = modelMapper.map(customer, CustomerDto.class);// convert the POJO persisted Customer to CustomerDto
        customersFound= new LinkedList<>();
        customersFound.add(customer);
    }

    @Test
    void createCustomerTest() {

        // test when customer doesn't exist
            when(cr.findByPhone(anyInt())).thenReturn(Optional.empty());  // There's no customer with this number
            when(cr.save(customer)).thenReturn(customer); // By saving a customer, return the predefined customer
            CustomerDto mockedC = cs.createCustomer(customerDto);
            assertEquals(customerDtoSaved, mockedC);  // Is the returned dto the same as the saved one?

            //test when customer already exists
            when(cr.findByPhone(1234)).thenReturn(Optional.of(customer)); //When the customer already exists
            CustomerDto existingC = cs.createCustomer(customerDto); //invoke the cr.findById and then returns empty as defined in the customerService
            assertNull(existingC);

            verify(cr, times(1)).save(customer); //the number of times the cr.save method is really used.
            verify(cr,times(2)).findByPhone(anyInt());//the number of times the cr.findByphone is used.
    }

    @Test
    void deleteCustomerTest() {

        when(cr.findByPhone(anyInt())).thenReturn(Optional.of(customer)); // simulates the existence of the customer
        doNothing().when(cr).delete(customer); //when the delete method is invoked, do nothing because it returns a void

        cs.deleteCustomer(customerDto);

        verify(cr).delete(customer);
        verify(cr).findByPhone(anyInt());

    }

    @Test
    void findCustomerByIdTest() {

        when(cr.findById(anyInt())).thenReturn(Optional.of(customer));// it returns a container with a possible object
        CustomerDto mockedC= cs.findCustomerById(0);

        assertNotNull(mockedC); //checks if there is an instance
        assertEquals(customerDtoSaved,mockedC);

        verify(cr).findById(any());

    }
    @Test
    void findCustomerByFirstNameTest(){

        //check when customer is present
        when(cr.findByFirstName("A")).thenReturn(Optional.of(customersFound)); //when the repository method is invoked, return the list
        List <CustomerDto> mockedC= cs.findCustomerByFirstName("A"); //save the results of the search
        assertEquals(1,mockedC.size());

        //check when customer is not present
        when(cr.findByFirstName(anyString())).thenReturn(Optional.of(Collections.emptyList())); //when the name doesnt return customers, return empty container
        List<CustomerDto> customerNotFound= cs.findCustomerByFirstName("T");
        assertTrue(customerNotFound.isEmpty());

        verify(cr).findByFirstName("A");
        verify(cr).findByFirstName("T"); //verify that the method was used with an A and then with a T
    }

    @Test
    void findCustomerByLastNameTest(){

        //check when customer is present
        when(cr.findByLastName("L")).thenReturn(Optional.of(customersFound));
        List <CustomerDto> mockedC= cs.findCustomerByLastName("L");
        assertEquals(1,mockedC.size());

        //check when customer is not present
        when(cr.findByLastName("T")).thenReturn(Optional.of(Collections.emptyList()));
        List<CustomerDto> customerNotFound= cs.findCustomerByLastName("T");
        assertTrue(customerNotFound.isEmpty());

        verify(cr).findByLastName("L");
        verify(cr).findByLastName("T");
    }

    @Test
    void findCustomerByPhoneTest (){

        //check when customer is present
        when(cr.findByPhone(1234)).thenReturn(Optional.of(customer));
        CustomerDto mockedC= cs.findCustomerByPhone(1234);
        assertEquals(customerDtoSaved,mockedC);

        //check when customer is not present
        when(cr.findByPhone(1111)).thenReturn(Optional.empty());
        CustomerDto customerNotFound= cs.findCustomerByPhone(1111);
        assertNull(customerNotFound);

        verify(cr).findByPhone(1234);
        verify(cr).findByPhone(1111);
    }
    @Test
    void findCustomerByEmailTest (){

        //check when customer is present
        when(cr.findByEmail("a@L")).thenReturn(Optional.of(customer));
        CustomerDto mockedC= cs.findCustomerByEmail("a@L");
        assertEquals(customerDtoSaved,mockedC);

        //check when customer is not present
        when(cr.findByEmail("b@b")).thenReturn(Optional.empty());
        CustomerDto customerNotFound= cs.findCustomerByEmail("b@b");
        assertNull(customerNotFound);

        verify(cr).findByEmail("a@L");
        verify(cr).findByEmail("b@b");
    }

@Test
void findAllTest (){
    Customer c1=new Customer();
    Customer c2=new Customer();
    Customer c3=new Customer();

    customersFound.add(c1);
    customersFound.add(c2);
    customersFound.add(c3);

    when (cr.findAll()).thenReturn(customersFound);
    List<CustomerDto> mockCustomers= cs.findAll();

    assertEquals(4,mockCustomers.size());
    verify(cr).findAll();
}
    @Test
    void updateCustomerTest() {
        Customer updatedCustomer= new Customer(1,"Ana","Lemos","AL",null,1234,"a@l");

        //if the customer exists
        when(cr.findByPhone(1234)).thenReturn(Optional.of(updatedCustomer)); //guarantee that the method returns an existing customer
        when(cr.save(any(Customer.class))).thenReturn(updatedCustomer);
        CustomerDto mockC= cs.updateCustomer(customerDto);
        assertEquals("Ana",mockC.getfName());
        assertEquals("Lemos",mockC.getlName());

        //if the customer doesn't exist
        when(cr.findByPhone(anyInt())).thenReturn(Optional.empty());
        CustomerDto nonExistingC= cs.updateCustomer(customerDto);
        assertNull(nonExistingC);


        verify(cr).save(any(Customer.class));
        verify(cr).deleteByPhone(customer.getPhone());
        verify(cr,times(2)).findByPhone(anyInt());
    }
    @Test
    void validEmailAddress() {
        Customer customer = new Customer();
        customer.setEmail("andre@email.com");

        Errors errors = new BeanPropertyBindingResult(customer, "customer");
        validator.validate(customer, errors);

        assertFalse(errors.hasErrors());
    }

    @Test
    void invalidEmailAddress() {
        Customer customer = new Customer();
        customer.setEmail("invalid-email");

        Errors errors = new BeanPropertyBindingResult(customer, "customer");
        validator.validate(customer, errors);

        assertTrue(errors.hasFieldErrors("email"));
        assertEquals("field.invalidFormat", errors.getFieldError("email").getCode());
    }
    @Test
    void emptyEmailAddress() {
        Customer customer = new Customer();
        customer.setEmail("");

        Errors errors = new BeanPropertyBindingResult(customer, "customer");
        validator.validate(customer, errors);

        assertTrue(errors.hasFieldErrors("email"));
        assertEquals("Email must not be empty", errors.getFieldError("email").getDefaultMessage());
    }

    @Test
    void nullEmailAddress() {
        Customer customer = new Customer();
        customer.setEmail(null);

        Errors errors = new BeanPropertyBindingResult(customer, "customer");
        validator.validate(customer, errors);

        assertTrue(errors.hasFieldErrors("email"));
        assertEquals("Email must not be empty", errors.getFieldError("email").getDefaultMessage());
    }
}
