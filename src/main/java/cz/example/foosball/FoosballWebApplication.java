package cz.example.foosball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class FoosballWebApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FoosballWebApplication.class, args);
	}
}
