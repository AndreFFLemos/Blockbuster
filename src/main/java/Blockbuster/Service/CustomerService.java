package Blockbuster.Service;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.Model.UserLoginResponse;
import Blockbuster.Model.UserRegistrationRequest;
import Blockbuster.Repository.CustomerRepository;
import Blockbuster.Model.Customer;
import Blockbuster.Security.JWTService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService implements CustomerServiceInterface {

    private static final String headerPrefix= "Bearer";
    private final CustomerRepository cr;
    private UserRegistrationRequest userRegistration;
    private final ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    @Autowired
    public CustomerService(CustomerRepository cr, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.cr = cr;
        this.modelMapper = modelMapper;
        this.passwordEncoder=passwordEncoder;
    }

    @Autowired
    public void setAuthenticationManager(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public CustomerDto createCustomer(UserRegistrationRequest userRegistration) {
        if (userRegistration == null) {
            throw new IllegalArgumentException("user is null");
        }

        //if the customer is in the DB then the method will return an empty container meaning no saved Customer
        Optional <Customer> customerExists= cr.findByEmail(userRegistration.getEmail());

        //it's cheaper in terms of resources to return an optional  than throwing an exception for something that can be normal
        if (customerExists.isPresent()) {
            return null;
        }

        //convert the customerDto instance to a POJO instance and save the latter to the customer instance
        Customer customer= modelMapper.map(userRegistration, Customer.class);

        if(customer.getPassword() == null || customer.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is missing or empty");
        }
        //passwordEncoder criptographs the password introduced by the customer
        String thePassword=passwordEncoder.encode(customer.getPassword());
        customer.setPassword(thePassword);
        //tell the repository to persist the customer instance and save that instance on the customer variable
        customer= cr.save(customer);

        //convert that persisted instance back in to a DTO object
        CustomerDto customerDto1= modelMapper.map(customer,CustomerDto.class);

        return  customerDto1;
    }

    public void deleteCustomer(CustomerDto customerDto) {

        Optional <Customer> customerExists= cr.findByEmail(customerDto.getEmail());
        if (!customerExists.isPresent()) {
            System.out.println("No customer with that email present");
        }
        Customer customer= modelMapper.map(customerDto, Customer.class);
        cr.delete(customer);
    }

    public CustomerDto findCustomerById(int id) {
        //if the customer is in the DB
        Optional<Customer> findCustomer=cr.findById(id);
        //if it's not, then return an empty container
        if (findCustomer.isEmpty()){
            return null;
        }
        //convert that found customer in to a customerDto instance and return it
        CustomerDto customerDto= modelMapper.map(findCustomer, CustomerDto.class);

        return customerDto;
    }

    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Optional<Customer> existingCustomer= cr.findByEmail(customerDto.getEmail());

        //if the customer isn't found, then return nothing
        if (existingCustomer.isEmpty()){
            return null;
        }
        //repository delete the customer
        cr.deleteByEmail(existingCustomer.get().getEmail());
        //convert the customerDto instance into a customer instance and save it
        Customer customer= modelMapper.map(customerDto, Customer.class);
        customer=cr.save(customer);

        //convert back that persisted customer in to a customerDto and return it
        CustomerDto customerDto1= modelMapper.map(customer, CustomerDto.class);

        return customerDto1;
    }

    @Override
    public List <CustomerDto> findAll() {
        List <Customer> customers= cr.findAll();

        //convert that list of customers in to a List of CustomersDto and return it
           return customers.stream()
                    .map(customer -> modelMapper.map(customer, CustomerDto.class))
                    .collect(Collectors.toList());
    }

    @Override
    public List <CustomerDto> findCustomerByFirstName(String firstName) {

        Optional<List<Customer>> customers= cr.findByFirstName(firstName);
        if (customers.isEmpty())
        {
            return Collections.emptyList(); // if the name doesn't return customers, the repo returns an empty container
        }

        List<Customer> getCustomersOut= customers.get();

        return getCustomersOut.stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> findCustomerByLastName(String lastName) {

        Optional<List<Customer>> customers= cr.findByLastName(lastName);

        if (customers.isEmpty())
        {
            return Collections.emptyList(); // if the name doesn't return customers, the repo returns an empty object
        }

        List<Customer> getCustomersOut= customers.get();

        return getCustomersOut.stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public CustomerDto findCustomerByPhone(int phone) {
        Optional <Customer> foundCustomer= cr.findByPhone(phone);

        if (foundCustomer.isEmpty()) {
            return null;
        }
        CustomerDto customerDto=modelMapper.map(foundCustomer, CustomerDto.class);

        return customerDto;
    }

    @Override
    public CustomerDto findCustomerByEmail(String email) {
        Optional <Customer> foundCustomer= cr.findByEmail(email);

        if (foundCustomer.isEmpty()) {
            return null;
        }
        CustomerDto customerDto=modelMapper.map(foundCustomer, CustomerDto.class);

        return customerDto;
    }

    public UserLoginResponse login(String email, String password){

        //the authentication manager gets the login values and if they match an existent user it checks its authentication
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password,Collections.emptyList()));

        //and now Spring knows there is an authenticated user
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token= headerPrefix+jwtService.generateToken(authentication);

        Optional<Customer> customerFound= cr.findByEmail(email);
        Customer customer= customerFound.get();
        CustomerDto customerDto= modelMapper.map(customer,CustomerDto.class);

        return new UserLoginResponse(token,customerDto);
    }
}