package Blockbuster.Service;

import Blockbuster.Model.Customer;

public interface CustomerServiceInterface {

    Customer createCustomer(Customer customer);
    void deleteCustomerById(int id);
    Customer findCustomerById(int id);
    Customer updateCustomer(Customer customer);

}
