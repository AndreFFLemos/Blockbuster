package Blockbuster.Service;

import Blockbuster.Model.Customer;
import Blockbuster.Repository.CustomerRepositoryInterface;

public class CustomerService implements CustomerServiceInterface{

    CustomerRepositoryInterface cri;

    @Override
    public Customer findCustomer(Customer customer) {
        return cri.findCustomer(customer);
    }

    @Override
    public String createCustomer(Customer customer) {
        if (cri.createCustomer(customer)==false) {
            return "Customer already exists";
        }
        return "Customer created!";
    }

    @Override
    public String deleteCustomer(Customer customer) {
        if (cri.deleteCustomer(customer) == true) {
            return "Customer deleted";
        }return "";
    }

    @Override
    public String updateCustomer(Customer customer) {
        if (cri.updateCustomer(customer)){
            return "Customer updated";
        }
        return "";
    }

    @Override
    public String doesCustomerHaveUnreturnedMovies(Customer customer) {
      /*  if (cri.getCustomerNumberOfDeliveredMovies(customer)){
            return "Customer has movies to return";
        }
    */
        return "Customer has 0 movies to return";

    }
    @Override
    public String customerHasFines(Customer customer) {
        /* if (cri.customerHasFines(customer)==true){
            return "Customer has unpaid fines";
        }*/
        return "Customer doesn't have fines to pay";
    }
}
