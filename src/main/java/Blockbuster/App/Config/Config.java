package Blockbuster.App.Config;

import Blockbuster.App.Model.Customer;
import Blockbuster.App.Model.Movie;
import Blockbuster.App.Repository.MovieRepository;
import Blockbuster.App.Service.CustomerService;
import Blockbuster.App.Service.MovieService;
import Blockbuster.App.Service.CustomerServiceInterface;
import Blockbuster.App.Service.MovieServiceInterface;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@Configuration
@ComponentScan(basePackages = {"Blockbuster.App"})
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
