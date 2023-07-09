package Blockbuster.Service;

import Blockbuster.DTO.CustomerDto;
import Blockbuster.Model.Customer;
import Blockbuster.Repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService implements CustomerServiceInterface {

    CustomerRepository cr;
    ModelMapper modelMapper;

    @Autowired
    public CustomerService(CustomerRepository cr, ModelMapper modelMapper) {
        this.cr = cr;
        this.modelMapper = modelMapper;
    }

    public Optional<CustomerDto> createCustomer(CustomerDto customerDto) {
        //if the customer is in the DB then the method will return an empty container meaning no saved Customer
        Optional <Customer> customerExists= cr.findById(customerDto.getPhone());
        if (customerExists.isPresent()) {
            return Optional.empty();
        }

        //convert the customerDto instance to a POJO instance and save the latter to the customer variable
        Customer customer= modelMapper.map(customerDto, Customer.class);
        //tell the repository to persist the customer instance and save that instance on the customer variable
        customer= cr.save(customer);

        //convert that persisted instance back in to a DTO object
        CustomerDto customerDto1= modelMapper.map(customer,CustomerDto.class);

        return  Optional.of(customerDto1);
    }

    public void deleteCustomer(CustomerDto customerDto) {

        Optional <Customer> customerExists= cr.findById(customerDto.getPhone());
        if (!customerExists.isPresent()) {
            System.out.println("No customer with that phone present");
        }
        Customer customer= modelMapper.map(customerDto, Customer.class);
        cr.deleteById(customer.getId());
    }

    public Optional<CustomerDto> findCustomerById(int id) {
        //if the customer is in the DB
        Optional<Customer> findCustomer=cr.findById(id);
        //if it's not, then return an empty container
        if (findCustomer.isEmpty()){
            return Optional.empty();
        }
        //convert that found customer in to a customerDto instance and return it
        CustomerDto customerDto= modelMapper.map(findCustomer, CustomerDto.class);

        return Optional.of(customerDto);
    }

    public Optional<CustomerDto> updateCustomer(CustomerDto customerDto) {
        Optional<Customer> existingCustomer= cr.findByPhone(customerDto.getPhone());
        //if it's not, then return an empty container
        if (existingCustomer.isEmpty()){
            return Optional.empty();
        }
        //repository delete the customer
        cr.deleteByPhone(customerDto.getPhone());
        //convert the customerDto instance into a customer instance and save it
        Customer customer= modelMapper.map(customerDto, Customer.class);
        customer=cr.save(customer);

        //convert back that persisted customer in to a customerDto and return it
        CustomerDto customerDto1= modelMapper.map(customer, CustomerDto.class);

        return Optional.of(customerDto1);
    }

    @Override
    public List<CustomerDto> findAll() {
        List <Customer> customers= cr.findAll();

        //convert that list of customers in to a List of CustomersDto and return it
           return customers.stream()
                    .map(customer -> modelMapper.map(customers, CustomerDto.class))
                    .collect(Collectors.toList());

    }

    @Override
    public List <CustomerDto> findCustomerByFirstName(String firstName) {

        List<Customer> customers= cr.findByFirstName(firstName);

        if (customers.isEmpty())
        {
            return Collections.emptyList(); // if the name doesn't return customers, the repo returns an empty container
        }

        return customers.stream()
                .map(customer -> modelMapper.map(customers, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> findCustomerByLastName(String lastName) {

        List<Customer> customers= cr.findByLastName(lastName);

        if (customers.isEmpty())
        {
            return Collections.emptyList(); // if the name doesn't return customers, the repo returns an empty object
        }

        return customers.stream()
                .map(customer -> modelMapper.map(customers, CustomerDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public Optional<CustomerDto> findCustomerByPhone(int phone) {
        Optional <Customer> foundCustomer= cr.findByPhone(phone);

        if (foundCustomer.isEmpty()) {
            return Optional.empty();
        }
        CustomerDto customerDto=modelMapper.map(foundCustomer, CustomerDto.class);

        return Optional.of(customerDto);
    }

    @Override
    public Optional<CustomerDto> findCustomerByEmail(String email) {
        Optional <Customer> foundCustomer= cr.findByEmail(email);

        if (foundCustomer.isEmpty()) {
            return Optional.empty();
        }
        CustomerDto customerDto=modelMapper.map(foundCustomer, CustomerDto.class);

        return Optional.of(customerDto);
    }
}