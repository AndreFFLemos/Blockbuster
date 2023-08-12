package Blockbuster.Service;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.Model.UserLoginResponse;
import Blockbuster.Model.UserRegistrationRequest;

import java.util.List;

public interface CustomerServiceInterface {

    CustomerDto createCustomer(UserRegistrationRequest userRegistration);
    void deleteCustomer(int id);
    CustomerDto findCustomerById(int id);
    CustomerDto updateCustomer(int id,CustomerDto customerDto);
    List <CustomerDto> findAll();
    List <CustomerDto> findCustomerByFirstName(String name);
    List <CustomerDto> findCustomerByLastName(String l);
    CustomerDto findCustomerByPhone(int i);
    CustomerDto findCustomerByEmail(String email);
    UserLoginResponse login(String email, String password);
}
