package io.pivotal.ppv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("io.pivotal.ppv")
@ComponentScan("io.pivotal.ppv")
@SpringBootApplication
public class PpvApplication {
	
	public static void main(String[] args) {
		
		
		SpringApplication.run(PpvApplication.class, args);
		
	}
}

