package Blockbuster.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    //I am telling Spring to manage the ModelMapper object
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
