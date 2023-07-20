package Blockbuster.App.Controller;

import Blockbuster.App.DTO.CustomerDto;
import Blockbuster.App.Service.CustomerServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController implements CustomerControllerInterface {

    private CustomerServiceInterface customerServiceInterface;

    @Autowired
    public CustomerController(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    @Override
    @PostMapping(value = "/new")
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
            //spring converts the dto instance in to a json format by using the Jackson library
        return new ResponseEntity<>(customerServiceInterface.createCustomer(customerDto),HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id,@Valid @RequestBody CustomerDto customerDto) {

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
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable int id,@Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerServiceInterface.updateCustomer(customerDto));
    }

    @Override
    @GetMapping(value = "/findbyid")
    public ResponseEntity<CustomerDto> findCustomerByID(@RequestParam ("id") int id) {
        return ResponseEntity.ok(customerServiceInterface.findCustomerById(id));
    }

    @Override
    @GetMapping(value = "/findbyfirstname")
    public ResponseEntity<List<CustomerDto>> findCustomerByFirstName(@Valid @RequestParam("firstName") String firstName) {

        return new ResponseEntity<>(customerServiceInterface.findCustomerByFirstName(firstName),HttpStatus.OK);

    }

    @Override
    @GetMapping (value = "/findbylastname")
    public ResponseEntity<List<CustomerDto>> findCustomerByLastName(@Valid @RequestParam ("lastName") String lastName) {
        return new ResponseEntity<>(customerServiceInterface.findCustomerByLastName(lastName),HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/findbyemail")
    public ResponseEntity<CustomerDto> findCustomerByEmail(@Valid @RequestParam ("email") String email) {
        return ResponseEntity.ok(customerServiceInterface.findCustomerByEmail(email));
    }

    @Override
    @GetMapping(value = "/findbyphone")
    public ResponseEntity<CustomerDto> findCustomerByPhone(@Valid @RequestParam ("number") int number) {
        return ResponseEntity.ok(customerServiceInterface.findCustomerByPhone(number));
    }

    @Override
    @GetMapping(value = "/all")
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {

        return new ResponseEntity<>(customerServiceInterface.findAll(), HttpStatus.OK);
    }



}