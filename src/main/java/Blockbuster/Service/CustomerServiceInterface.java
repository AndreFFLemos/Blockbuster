package Blockbuster.Service;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.DTO.PasswordDto;
import Blockbuster.Model.Customer;
import Blockbuster.Model.UserLoginResponse;
import Blockbuster.Model.UserRegistrationRequest;

import java.util.List;

public interface CustomerServiceInterface {

    CustomerDto createCustomer(UserRegistrationRequest userRegistration);
    void deleteCustomer(int id);
    CustomerDto findCustomerById(int id);
    void updateCustomer(int id,CustomerDto customerDto);
    List <CustomerDto> findAll();
    List <CustomerDto> findCustomerByFirstName(String name);
    List <CustomerDto> findCustomerByLastName(String l);
    CustomerDto findCustomerByPhone(int i);
    Customer findCustomerByEmail(String email);
    UserLoginResponse login(String email, String password);
    void updatePassword(Integer id, PasswordDto passwordDto);
}
