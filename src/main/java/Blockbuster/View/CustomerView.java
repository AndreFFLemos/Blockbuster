package Blockbuster.View;

import Blockbuster.Controller.CustomerController;
import Blockbuster.Model.Customer;

public class CustomerView {
    private Customer customer;
    private CustomerController customerController;

    public void showCustomer (Customer customer){
        System.out.println(customerController.findCustomer(customer));
    }

    public void customerCreated (Customer customer){
        System.out.println(customerController.createCustomer(customer));
    }

    public void customerDeleted (Customer customer){

    }
    public void customerUpdated (Customer customer){
        System.out.println(customerController.updateCustomer(customer));
    }
}
