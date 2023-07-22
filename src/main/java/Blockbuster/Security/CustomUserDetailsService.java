package Blockbuster.Security;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.Model.Customer;
import Blockbuster.Service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public class CustomUserDetailsService implements UserDetailsService {

    private ModelMapper modelMapper;
    private CustomerService customerService;

    public CustomUserDetailsService(ModelMapper modelMapper, CustomerService customerService) {
        this.modelMapper = modelMapper;
        this.customerService = customerService;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        CustomerDto customerDto= customerService.findCustomerByEmail(email);

        if (customerDto==null){
            throw new UsernameNotFoundException("Not found");
        }

        //interface userdetails it's implemented by the Customer class
        return modelMapper.map(customerDto, UserDetails.class);
    }

    public UserDetails loadUserById(int id){

        return (UserDetails) customerService.findCustomerById(id);
    }
}
