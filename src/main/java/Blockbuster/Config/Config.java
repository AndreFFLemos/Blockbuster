package Blockbuster.Config;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    Customer customer (){
        return new Customer();
    }
    @Bean
    Movie movie(){
        return new Movie();
    }

    //I am telling Spring to manage the ModelMapper object
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
