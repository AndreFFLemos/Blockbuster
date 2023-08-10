package Blockbuster.Controller;

import Blockbuster.Model.UserRegistrationRequest;
import Blockbuster.Service.CustomerService;
import Blockbuster.Service.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping(value= "/api")
    public class AuthController {

        @Autowired
        private CustomerServiceInterface customerServiceInterface;

        @PostMapping(value = "/register")
        public ResponseEntity<?> register(@RequestBody UserRegistrationRequest registrationRequest) {
            // Check if user already exists
            if(customerServiceInterface.findCustomerByEmail(registrationRequest.getEmail())!=null) {
                return ResponseEntity.badRequest().body("User already exists");
            }

            // Create a new user
            customerServiceInterface.createCustomer(registrationRequest);

            // Return a success response
            return ResponseEntity.ok("Registration successful");
        }
    }
