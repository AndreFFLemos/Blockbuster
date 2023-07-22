package Blockbuster.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {

    //I am telling Spring to create and manage the ModelMapper object
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
