package Blockbuster.Controller;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.DTO.MovieDto;
import Blockbuster.Model.Customer;
import Blockbuster.Service.CustomerServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController implements CustomerControllerInterface {

    private CustomerServiceInterface customerServiceInterface;


    @Override
    @PostMapping(value = "/new")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
            //spring converts the dto instance in to a json format by using the Jackson library
        return new ResponseEntity<>(customerServiceInterface.createCustomer(customerDto),HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id,@RequestBody CustomerDto customerDto) {

        customerServiceInterface.deleteCustomer(customerDto);

        // the build method constructs a response entity with an empty body
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable int id) {

        return new ResponseEntity<>(customerServiceInterface.findCustomerById(id),HttpStatus.OK);
    }
    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable int id,@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerServiceInterface.updateCustomer(customerDto));
    }

    @Override
    @GetMapping(value = "/findbyid")
    public ResponseEntity<CustomerDto> findCustomerByID(@RequestParam ("id") int id) {
        return ResponseEntity.ok(customerServiceInterface.findCustomerById(id));
    }

    @Override
    @GetMapping(value = "/findbyfirstname")
    public ResponseEntity<List<CustomerDto>> findCustomerByFirstName(@RequestParam("firstName") String firstName) {

        return new ResponseEntity<>(customerServiceInterface.findCustomerByFirstName(firstName),HttpStatus.OK);

    }

    @Override
    @GetMapping (value = "/findbylastname")
    public ResponseEntity<List<CustomerDto>> findCustomerByLastName(@RequestParam ("lastName") String lastName) {
        return new ResponseEntity<>(customerServiceInterface.findCustomerByLastName(lastName),HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/findbyemail")
    public ResponseEntity<CustomerDto> findCustomerByEmail(@RequestParam ("email") String email) {
        return ResponseEntity.ok(customerServiceInterface.findCustomerByEmail(email));
    }

    @Override
    @GetMapping(value = "/findbyphone")
    public ResponseEntity<CustomerDto> findCustomerByPhone(@RequestParam ("number") int number) {
        return ResponseEntity.ok(customerServiceInterface.findCustomerByPhone(number));
    }

    @Override
    @GetMapping(value = "/all")
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {

        return new ResponseEntity<>(customerServiceInterface.findAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/{id}/movies")
    public ResponseEntity<List<MovieDto>> getMoviesByCustomer(@PathVariable int id, @RequestBody CustomerDto customerDto) {
        return null;
    }



}