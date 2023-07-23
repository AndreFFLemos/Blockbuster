package Blockbuster.Security;

import Blockbuster.Service.CustomerService;
import Blockbuster.Service.CustomerServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;


//this class provides the config for spring security
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final JWTService jwtService;
    private final ModelMapper modelMapper;
    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(JWTService jwtService, ModelMapper modelMapper, @Lazy UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
        this.userDetailsService = userDetailsService;
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
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Arrays.asList(authenticationProvider(customUserDetailsService(null))));
    }
}

