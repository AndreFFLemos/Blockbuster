package Blockbuster.Service;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.Model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceInterface {

    CustomerDto createCustomer(CustomerDto customerDto);
    void deleteCustomer(CustomerDto customerDto);
    CustomerDto findCustomerById(int id);
    CustomerDto updateCustomer(CustomerDto customerDto);
    List <CustomerDto> findAll();
    List <CustomerDto> findCustomerByFirstName(String name);
    List <CustomerDto> findCustomerByLastName(String l);
    CustomerDto findCustomerByPhone(int i);
    CustomerDto findCustomerByEmail(String email);
}
