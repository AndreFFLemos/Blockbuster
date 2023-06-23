package Blockbuster;

import Blockbuster.Config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(Config.class)
public class BlockbusterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockbusterApplication.class, args);
	}

}
