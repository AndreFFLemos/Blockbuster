package Blockbuster.Service;

import Blockbuster.Model.Customer;
import Blockbuster.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;
import java.util.Optional;

public class CustomerService implements CustomerServiceInterface {

    @Autowired
    CustomerRepository cr;
    Customer customer;

    public Customer createCustomer(Customer customer) {
       if (cr.findById(customer.getId()).isPresent()){
           throw new IllegalArgumentException("Customer already exists");
       }
        return  cr.save(customer);
    }

    public void deleteCustomerById(int id) {
        if (!cr.findById(customer.getId()).isPresent()){
            throw new NoSuchElementException("Customer doesn't exist");
        }
        cr.deleteById(id);
    }

    public Customer getCustomerById(int id) {
        return cr.findById(id).orElseThrow(()-> new NoSuchElementException("Customer not found."));
    }

    public Customer updateCustomer(Customer customer) {
        Customer existingCustomer= cr.findById(customer.getId()).orElseThrow(()->
                new NoSuchElementException("The customer wasn't found"));

        existingCustomer.setFName(customer.getFName());
        existingCustomer.setLName(customer.getLName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setUsername(customer.getUsername());
        existingCustomer.setPassword(customer.getPassword());

        return cr.save(existingCustomer);
    }
}