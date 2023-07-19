package Blockbuster.Controller;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CustomerControllerInterface {

    ResponseEntity<CustomerDto> findCustomerByID(@RequestParam("id") int id);
    ResponseEntity<CustomerDto> getCustomer(@PathVariable int id);
    ResponseEntity <List<CustomerDto>> findCustomerByFirstName(@RequestParam("firstName") String firstName);
    ResponseEntity<List<CustomerDto>> findCustomerByLastName(@RequestParam("lastName") String lastName);
    ResponseEntity<CustomerDto> findCustomerByEmail(@RequestParam("email") String email);
    ResponseEntity<CustomerDto> findCustomerByPhone(@RequestParam ("number") int number);
    ResponseEntity<List<CustomerDto>> findAllCustomers();
    ResponseEntity<CustomerDto> updateCustomer(@PathVariable int id,@RequestBody CustomerDto customerDto);
    ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto);
    ResponseEntity<Void> deleteCustomer(@PathVariable int id, @RequestBody CustomerDto customerDto);
    ResponseEntity <List<MovieDto>> getMoviesByCustomer(@PathVariable int id,@RequestBody CustomerDto customerDto);

}
