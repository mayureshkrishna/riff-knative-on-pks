package io.pivotal.ppv;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PpvApplication {
	
	
	public static void main(String... args) {
		
		new SpringApplicationBuilder().sources(PpvApplication.class)
		.bannerMode(Banner.Mode.OFF).run(args);
	}
	
}

