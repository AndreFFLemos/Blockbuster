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

    public Optional<Customer> findCustomerById(int id) {
        Optional <Customer> foundCustomer=cr.findById(id);

        if (foundCustomer.isEmpty()){
            return Optional.empty();
        }

        return foundCustomer;
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

    @Override
    public Optional<Customer> findCustomerByPhone(int phone) {
        Optional <Customer> foundCustomer= cr.findByPhone(phone);

        if (foundCustomer.isEmpty()) {
            return Optional.empty();
        }
        return foundCustomer;
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        Optional <Customer> foundCustomer= cr.findByEmail(email);

        if (foundCustomer.isEmpty()) {
            return Optional.empty();
        }
        return foundCustomer;
    }
}