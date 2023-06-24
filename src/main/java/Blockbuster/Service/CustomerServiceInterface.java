package Blockbuster.Service;

import Blockbuster.Model.Customer;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CustomerServiceInterface {

    Optional<Customer> createCustomer(Customer customer);
    void deleteCustomerById(int id);
    Optional<Customer> findCustomerById(int id);
    Customer updateCustomer(Customer customer);
    List<Customer> findAll();
    List<Customer> findCustomerByFirstName(String name);
    List<Customer> findCustomerByLastName(String l);
    Optional <Customer> findCustomerByPhone(int i);

    Optional<Customer> findCustomerByEmail(String email);
}
