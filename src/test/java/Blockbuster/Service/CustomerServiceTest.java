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
        customerDto=new CustomerDto("A","L","AL",1234,"a@l");
        customer=new Customer(1,"A","L","AL","ola",1234,"a@l");
        modelMapper=new ModelMapper();
        customerDtoSaved = modelMapper.map(customer, CustomerDto.class);// convert the POJO persisted Customer to CustomerDto
        customersFound= new LinkedList<>();
        customersFound.add(customer);
    }

    @Test
    void createCustomerTest() {

            // test when customer doesn't exist
            when(cr.findById(customerDto.getPhone())).thenReturn(Optional.empty());  // There's no customer with this number
            when(cr.save(any(Customer.class))).thenReturn(customer);  // By saving any customer, return the predefined customer
            Optional<CustomerDto> mockedC = cs.createCustomer(customerDto);
            assertEquals(Optional.of(customerDtoSaved), mockedC);  // Is the returned dto the same as the saved one?

            //test when customer already exists
            when(cr.findById(customerDto.getPhone())).thenReturn(Optional.of(customer)); //When the customer already exists
            Optional<CustomerDto> existingC = cs.createCustomer(customerDto); //invoke the cr.findById and then returns empty as defined in the customerService
            assertTrue(existingC.isEmpty());

            verify(cr, times(1)).save(any(Customer.class)); //the number of times the cr.save method is really used.
        }

    @Test
    void deleteCustomerByIdTest() {

        when(cr.findByPhone(1234)).thenReturn(Optional.of(customer)); // simulates the existance of the customer
        doNothing().when(cr).deleteById(1); //when the delete method is invoked, do nothing because it returns a void
       cs.deleteCustomer(customerDto);
        verify(cr).deleteById(any());

    }

    @Test
    void findCustomerByIdTest() {

        when(cr.findById(anyInt())).thenReturn(of(customer));// it returns a container with a possible object
        Optional<CustomerDto> mockedC= cs.findCustomerById(1);

        assertTrue(mockedC.isPresent()); //checks if there is an instance
        assertEquals(Optional.of(customerDtoSaved),mockedC);
        verify(cr).findById(any());

    }
    @Test
    void findCustomerByFirstNameTest(){

        //check when customer is present
        when(cr.findByFirstName("A")).thenReturn(customersFound); //when the repository method is invoked, return the list
        List <CustomerDto> mockedC= cs.findCustomerByFirstName("A"); //save the results of the search
        assertEquals(mockedC.size(),1);

        //check when customer is not present
        when(cr.findByFirstName("T")).thenReturn(Collections.emptyList()); //when the name doesnt return customers, return empty container
        List<CustomerDto> customerNotFound= cs.findCustomerByFirstName("T");
        assertTrue(customerNotFound.isEmpty());

        verify(cr).findByFirstName("A");
        verify(cr).findByFirstName("T"); //verify that the method was used with an A and then with a T
    }

    @Test
    void findCustomerByLastNameTest(){

        //check when customer is present
        when(cr.findByLastName("L")).thenReturn(customersFound);
        List <CustomerDto> mockedC= cs.findCustomerByLastName("L");
        assertEquals(mockedC.size(),1);

        //check when customer is not present
        when(cr.findByLastName("T")).thenReturn(Collections.emptyList());
        List<CustomerDto> customerNotFound= cs.findCustomerByLastName("T");
        assertTrue(customerNotFound.isEmpty());

        verify(cr).findByLastName("L");
        verify(cr).findByLastName("T");
    }

    @Test
    void findCustomerByPhone (){

        //check when customer is present
        when(cr.findByPhone(1234)).thenReturn(Optional.of(customer));
        Optional<CustomerDto> mockedC= cs.findCustomerByPhone(1234);
        assertEquals(mockedC,Optional.of(customerDtoSaved));

        //check when customer is not present
        when(cr.findByPhone(1111)).thenReturn(Optional.empty());
        Optional<CustomerDto> customerNotFound= cs.findCustomerByPhone(1111);
        assertTrue(customerNotFound.isEmpty());

        verify(cr).findByPhone(1234);
        verify(cr).findByPhone(1111);
    }
    @Test
    void findCustomerByEmail (){

        //check when customer is present
        when(cr.findByEmail("a@L")).thenReturn(Optional.of(customer));
        Optional<CustomerDto> mockedC= cs.findCustomerByEmail("a@a");
        assertEquals(mockedC,Optional.of(customerDtoSaved));

        //check when customer is not present
        when(cr.findByEmail("b@b")).thenReturn(Optional.empty());
        Optional<CustomerDto> customerNotFound= cs.findCustomerByEmail("b@b");
        assertTrue(customerNotFound.isEmpty());

        verify(cr).findByEmail("a@L");
        verify(cr).findByEmail("b@b");
    }

@Test
void findAll (){
    Customer c1=new Customer();
    Customer c2=new Customer();
    Customer c3=new Customer();

    customersFound.add(c1);
    customersFound.add(c2);
    customersFound.add(c3);

    when (cr.findAll()).thenReturn(customersFound);
    List<CustomerDto> mockCustomers= cs.findAll();

    assertEquals(mockCustomers.size(),4);
    verify(cr).findAll();
}
    @Test
    void updateCustomerTest() {
        customer.setId(1);
        customer.setFName("B");
        customer.setLName("P");

        when(cr.findById(1)).thenReturn(Optional.of(customer)); //guarantee that the method returns an existing customer
        when(cr.save(customer)).thenReturn(customer);
        Optional<CustomerDto> mockC= cs.updateCustomer(customerDto);

        assertEquals("B",mockC.get().getFirstN());
        assertEquals("P",mockC.get().getLastN());

        verify(cr).save(customer);
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
