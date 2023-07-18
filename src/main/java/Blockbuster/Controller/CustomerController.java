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

    private CustomerServiceInterface csi;


    @Override
    @PostMapping(value = "/new")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {


        return new ResponseEntity<>(customerDto,HttpStatus.OK);
    }

    @Override
    public void deleteCustomer(CustomerDto customerDto) {

    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<CustomerDto> findCustomerByID(@PathVariable int id) {

        CustomerDto optionalCustomerDto=csi.findCustomerById(id);
        CustomerDto customerToSendToBrowser= optionalCustomerDto;

        return new ResponseEntity<>(customerToSendToBrowser,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerDto> findCustomerByFirstName(@PathVariable String firstName) {

        return null;

    }

    @Override
    public ResponseEntity<CustomerDto> findCustomerByEmail(@PathVariable String email) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> findCustomerByPhone(@PathVariable int number) {
        return null;
    }

    @Override
    @GetMapping(value = "/all")
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {
        List<CustomerDto> allCustomers= new LinkedList<>();

        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MovieDto>> findMoviesByCustomer(@PathVariable CustomerDto customerDto) {
        return null;
    }
    @Override
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {
        return null;
    }


}