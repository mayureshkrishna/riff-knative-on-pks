package io.pivotal.ppv;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PpvApplication {
	
	@Autowired
	private ConfigurableApplicationContext context;
	
	public static void main(String[] args) {
		
		
		SpringApplication.run(PpvApplication.class, args);
		
	}
	@PostConstruct
	public void init() {
		context.registerShutdownHook();
	}
}

