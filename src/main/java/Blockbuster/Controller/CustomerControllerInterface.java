package Blockbuster.Controller;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerControllerInterface {

    ResponseEntity<CustomerDto> findCustomerByID(@PathVariable int id);
    ResponseEntity<CustomerDto> findCustomerByFirstName(@PathVariable String firstName);
    ResponseEntity<CustomerDto> findCustomerByEmail(@PathVariable String email);
    ResponseEntity<CustomerDto> findCustomerByPhone(@PathVariable int number);
    ResponseEntity<List<CustomerDto>> findAllCustomers();
    ResponseEntity<CustomerDto> updateCustomer(CustomerDto customerDto);
    ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto);
    void deleteCustomer(CustomerDto customerDto);
    ResponseEntity <List<MovieDto>> findMoviesByCustomer(@RequestBody @PathVariable CustomerDto customerDto);

}
