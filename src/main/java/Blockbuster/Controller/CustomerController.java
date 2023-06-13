package Blockbuster.Controller;

import Blockbuster.Model.Customer;
import Blockbuster.Service.CustomerServiceInterface;

public class CustomerController implements CustomerControllerInterface{

    private CustomerServiceInterface customerServiceInterface;

    //a method that receives a HTTP request from the user and tells the Service Layer to check
    public String findCustomer(Customer customer) {
        return customerServiceInterface.findCustomerById(customer).toString();
    }

public String updateCustomer(Customer customer){
        return customerServiceInterface.updateCustomer(customer);
    }

    public String createCustomer(Customer customer) {
     return customerServiceInterface.createCustomer(customer);
    }
    public String deleteCustomer(Customer customer) {
        return customerServiceInterface.deleteCustomerById(customer.getId());
    }
}
