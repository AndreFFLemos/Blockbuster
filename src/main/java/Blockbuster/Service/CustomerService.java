package Blockbuster.Service;

import Blockbuster.Model.Customer;
import Blockbuster.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CustomerService implements CustomerServiceInterface {

    CustomerRepository cr;
    Customer customer;

    @Autowired
    public CustomerService(CustomerRepository cr, Customer customer) {
        this.cr = cr;
        this.customer = customer;
    }

    public Optional<Customer> createCustomer(Customer customer) {
        Optional <Customer> customerExists= cr.findById(customer.getId());

        if (customerExists.isPresent()) {
            return Optional.empty(); //if the customer is in the DB then the method will return an empty container meaning no saved C.
        }
        return  Optional.of(cr.save(customer)); //returns a container with the saved object
    }

    public void deleteCustomerById(int id) {
        if (!cr.existsById(id)){
            throw new NoSuchElementException("Customer doesn't exist");
        }
        cr.deleteById(id);
    }

    public Customer findCustomerById(int id) {
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

    @Override
    public List<Customer> findAll() {
        return cr.findAll();
    }

    @Override
    public List <Customer> findCustomerByFirstName(String firstName) {

        List<Customer> foundCustomers= cr.findByFirstName(firstName);

        if (foundCustomers.isEmpty())
        {
            return Collections.emptyList(); // if the name doesn't return customers, the repo returns an empty object
        }
        return foundCustomers;
    }

    @Override
    public List<Customer> findCustomerByLastName(String lastName) {

        List<Customer> foundCustomers= cr.findByLastName(lastName);

        if (foundCustomers.isEmpty())
        {
            return Collections.emptyList(); // if the name doesn't return customers, the repo returns an empty object
        }
        return foundCustomers;

    }
}