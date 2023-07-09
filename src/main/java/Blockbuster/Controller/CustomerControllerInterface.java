package Blockbuster.Controller;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.Model.Customer;
import Blockbuster.Model.Rental;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerControllerInterface {

    ResponseEntity<CustomerDto> findCustomerByID(int id);
    ResponseEntity<CustomerDto> findCustomerByFirstName(String firstName);
    ResponseEntity<CustomerDto> findCustomerByEmail(String email);
    ResponseEntity<CustomerDto> findCustomerByPhone(int number);
    ResponseEntity<List<CustomerDto>> findAllCustomers();
    ResponseEntity<CustomerDto> updateCustomer(Customer customer);
    ResponseEntity<CustomerDto> createCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    ResponseEntity <List<Rental>> findRentalsByCustomer(Customer customer);

}
