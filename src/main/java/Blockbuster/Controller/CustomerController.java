package Blockbuster.Controller;

import Blockbuster.Model.Customer;
import Blockbuster.Service.CustomerServiceInterface;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController implements CustomerControllerInterface {

    private CustomerServiceInterface csi;

    @Override
    public Customer findCustomer(Customer customer) {
        return csi.createCustomer(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public void deleteCustomer(Customer customer) {

    }

    //a method that receives a HTTP request from the user and tells the Service Layer to check


}