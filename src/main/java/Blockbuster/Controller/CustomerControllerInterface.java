package Blockbuster.Controller;

import Blockbuster.Model.Customer;

public interface CustomerControllerInterface {

    Customer findCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Customer createCustomer(Customer customer);
    void deleteCustomer(Customer customer);

}
