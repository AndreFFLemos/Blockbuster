package Blockbuster.Service;

import Blockbuster.Model.Customer;

public interface CustomerServiceInterface {


    Customer findCustomer(Customer customer);
    String createCustomer(Customer customer);
    String deleteCustomer(Customer customer);
    String updateCustomer(Customer customer);
    String doesCustomerHaveUnreturnedMovies(Customer customer);
    String customerHasFines(Customer customer);
}
