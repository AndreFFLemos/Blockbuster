package Blockbuster.Controller;

import Blockbuster.Config.Config;
import Blockbuster.Controller.CustomerController;
import Blockbuster.DTO.CustomerDto;
import Blockbuster.Model.Customer;
import Blockbuster.Repository.CustomerRepository;
import Blockbuster.Service.CustomerServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@WebMvcTest(CustomerController.class)
@Import(Config.class)
@ComponentScan(basePackages = {"Blockbuster.App", "Blockbuster.App.Repository"})
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc; // it simulates the request
    @MockBean
    private CustomerServiceInterface customerServiceInterface;
    @MockBean
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerController customerController;
    private Customer customer;
    private CustomerDto customerDto;
    private List<CustomerDto> customerDtos;
    @MockBean
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        customerDtos=new LinkedList<>();
        customer= new Customer(1,"A","L","AL",null,1234,"a@l");
        customerDto=new CustomerDto("A","L","AL","a@l",1234);
       customerDtos.add(customerDto);
       //the objectmapper converts the Dto instance to a json format
        objectMapper=new ObjectMapper();
        MockitoAnnotations.initMocks(this); // Initialize mocks
        // creates an instance to mock the http requests
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void createCustomerTest() throws Exception {
        //the object mapper is converting the customerDto instance in to a json format and saving it in the request body
        String requestBody= objectMapper.writeValueAsString(customerDto);

        //i'm telling the mockmvc to build a post request with the content type json and the content is the instance converted
        var requestBuilder=MockMvcRequestBuilders.post("/api/customer/new")
                        .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody);

        when(customerServiceInterface.createCustomer(customerDto)).thenReturn(customerDto);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateCustomerTest() throws Exception {
        customerDto.setFirstName("B");
        customerDto.setLastName("M");

        String requestBody= objectMapper.writeValueAsString(customerDto);

        var requestBuilder= MockMvcRequestBuilders.put("/api/customer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(customerServiceInterface.updateCustomer(customerDto)).thenReturn(customerDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestBody));
    }
    @Test
    public void deleteCustomerTest() throws Exception {
        String requestBody= objectMapper.writeValueAsString(customerDto);

        var requestBuilder= MockMvcRequestBuilders.delete("/api/customer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        //because the method deleteCustomer returns a void, then we tell mockito to do nothing when the method is invoked
        doNothing().when(customerServiceInterface).deleteCustomer(customerDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void findCustomerByIdTest() throws Exception {
        String requestBody= objectMapper.writeValueAsString(customerDto);

        var requestBuilder= MockMvcRequestBuilders.get("/api/customer/findbyid?id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(customerServiceInterface.findCustomerById(1)).thenReturn(customerDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestBody));

    }

    @Test
    public void findCustomerByFirstNameTest() throws Exception {
        String requestBody= objectMapper.writeValueAsString(customerDtos);

        var requestBuilder= MockMvcRequestBuilders.get("/api/customer/findbyfirstname?firstName=A")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(customerServiceInterface.findCustomerByFirstName("A")).thenReturn(customerDtos);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                //the true indicates that the json file can be an array or a single object
                .andExpect(MockMvcResultMatchers.content().json(requestBody,true));
    }
    @Test
    public void findCustomerByLastNameTest() throws Exception {
        String requestBody= objectMapper.writeValueAsString(customerDtos);

        var requestBuilder= MockMvcRequestBuilders.get("/api/customer/findbylastname?lastName=L")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(customerServiceInterface.findCustomerByLastName("L")).thenReturn(customerDtos);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                //the true indicates that the json file can be an array or a single object
                .andExpect(MockMvcResultMatchers.content().json(requestBody,true));
    }

    @Test
    public void findCustomerByPhone() throws Exception {
        String requestBody= objectMapper.writeValueAsString(customerDto);

        var requestBuilder= MockMvcRequestBuilders.get("/api/customer/findbyphone?number=1234")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(customerServiceInterface.findCustomerByPhone(1234)).thenReturn(customerDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestBody));
    }

    @Test
    public void findCustomerByEmailTest() throws Exception {
        String requestBody= objectMapper.writeValueAsString(customerDto);

        var requestBuilder= MockMvcRequestBuilders.get("/api/customer/findbyemail?email=a@l")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(customerServiceInterface.findCustomerByEmail("a@l")).thenReturn(customerDto);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(requestBody));
    }
    @Test
    public void findAllCustomersTest() throws Exception {

        String requestBody=objectMapper.writeValueAsString(customerDtos);

        //instance that creates the request
        //var is a local variable
        var requestBuilder= MockMvcRequestBuilders.get("/api/customer/all")
                        .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody);

        when(customerServiceInterface.findAll()).thenReturn(customerDtos);

        //the perform method simulates a request to the endpoint, like sending in Postman
        mockMvc.perform(requestBuilder)
                //asserts
                .andExpect(MockMvcResultMatchers.status().isOk())
                //and compare the json in the requestbody with the expected json representation
                .andExpect(MockMvcResultMatchers.content().json(requestBody));
    }



}
