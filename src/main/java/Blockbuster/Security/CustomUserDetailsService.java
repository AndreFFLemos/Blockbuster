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

    @Override // from the userdetailsservice
    // the method is invoked during the authentication process
    //it takes an email as input and tries to load the customerDto based on the email
    public UserDetails loadUserByUsername(String email){
        CustomerDto customerDto= customerService.findCustomerByEmail(email);

        if (customerDto==null){
            throw new UsernameNotFoundException("Not found");
        }

        //interface userdetails it's implemented by the Customer class
        return modelMapper.map(customerDto, UserDetails.class);
    }

}
