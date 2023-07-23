package Blockbuster.Security;

import Blockbuster.Service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


//this class provides the config for spring security
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JWTService jwtService;
    private final ModelMapper modelMapper;

    public WebSecurityConfig(JWTService jwtService, ModelMapper modelMapper) {
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomUserDetailsService customUserDetailsService(CustomerService customerService) {
        return new CustomUserDetailsService(modelMapper, customerService);
    }


    //this retrieves the user details and compares the password of the user with the provided password
    @Bean
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    //it filters the requests to the URL to ensure the user is authenticated
    @Bean
    public FilterRegistrationBean<JWTAuthenticationFilter> jwtAuthenticationFilter() {
        FilterRegistrationBean<JWTAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JWTAuthenticationFilter(jwtService, customUserDetailsService(null)));
        registrationBean.addUrlPatterns("/api/customer"); // Only the customer is authenticated
        return registrationBean;
    }
}

