package Blockbuster.Controller;

import Blockbuster.Model.Customer;

public interface CustomerControllerInterface {

    String findCustomer(Customer customer);
    String updateCustomer(Customer customer);
    String createCustomer(Customer customer);
    String deleteCustomer(Customer customer);

}
