package Blockbuster.App.Controller;

import Blockbuster.App.DTO.CustomerDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CustomerControllerInterface {

    ResponseEntity<CustomerDto> findCustomerByID(@RequestParam("id") int id);
    ResponseEntity<CustomerDto> getCustomer(@PathVariable int id);
    ResponseEntity <List<CustomerDto>> findCustomerByFirstName(@Valid @RequestParam("firstName") String firstName);
    ResponseEntity<List<CustomerDto>> findCustomerByLastName(@Valid @RequestParam("lastName") String lastName);
    ResponseEntity<CustomerDto> findCustomerByEmail(@Valid @RequestParam("email") String email);
    ResponseEntity<CustomerDto> findCustomerByPhone(@Valid @RequestParam ("number") int number);
    ResponseEntity<List<CustomerDto>> findAllCustomers();
    ResponseEntity<CustomerDto> updateCustomer(@PathVariable int id,@Valid @RequestBody CustomerDto customerDto);
    ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto);
    ResponseEntity<Void> deleteCustomer(@PathVariable int id, @Valid @RequestBody CustomerDto customerDto);

}
