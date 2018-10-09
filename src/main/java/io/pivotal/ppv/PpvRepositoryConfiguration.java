package io.pivotal.ppv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableConfigurationProperties
public class PpvRepositoryConfiguration {

	@Autowired
	private PpvRepository ppvRepository;
	
	@Bean
	@Primary
	public PpvRepository ppvRepository() {
		return ppvRepository;
		
	}
}
