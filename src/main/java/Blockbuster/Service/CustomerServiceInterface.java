package Blockbuster.Service;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.Model.Customer;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CustomerServiceInterface {

    Optional<CustomerDto> createCustomer(CustomerDto customerDto);
    void deleteCustomer(CustomerDto customerDto);
    Optional<CustomerDto> findCustomerById(int id);
    Optional<CustomerDto> updateCustomer(CustomerDto customerDto);
    List<CustomerDto> findAll();
    List<CustomerDto> findCustomerByFirstName(String name);
    List<CustomerDto> findCustomerByLastName(String l);
    Optional <CustomerDto> findCustomerByPhone(int i);
    Optional<CustomerDto> findCustomerByEmail(String email);
}
