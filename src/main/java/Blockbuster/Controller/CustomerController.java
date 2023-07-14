package Blockbuster.Controller;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.Model.Customer;
import Blockbuster.Service.CustomerServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController implements CustomerControllerInterface {

    private CustomerServiceInterface csi;

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<CustomerDto> findCustomerByID(int id) {


        csi.findCustomerById(id);

        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> findCustomerByFirstName(String firstName) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> findCustomerByEmail(String email) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> findCustomerByPhone(int number) {
        return null;
    }

    @Override
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> createCustomer(Customer customer) {
        return null;
    }

    @Override
    public void deleteCustomer(Customer customer) {

    }

    @Override
    public ResponseEntity<List<Rental>> findRentalsByCustomer(Customer customer) {
        return null;
    }
}