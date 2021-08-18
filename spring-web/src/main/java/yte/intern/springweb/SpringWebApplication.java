package yte.intern.springweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import yte.intern.springweb.controllers.TestController;

@SpringBootApplication
public class SpringWebApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringWebApplication.class, args);


	}

}
