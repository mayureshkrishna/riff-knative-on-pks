package io.pivotal.ppv;

import java.util.Optional;
import java.util.function.Function;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PpvApplication {

	private Log logger = LogFactory.getLog(PpvApplication.class);

	@Autowired
	private PpvRepository ppvRepository;

	@Bean
	public Function <Long,PayPerView> getpayperviewbyid() {

		return id -> {
			try {
				Optional<PayPerView> ppv = ppvRepository.findById(id);
				if (ppv.isPresent()) {
					
					logger.info("Retrieved: " + ppv.get().toString());
					return ppv.get();
				} else {
					logger.info("No Data Found");
					return null;
				}
			} 
			catch (Exception e) {
				logger.error("PPV Info Failed: " + e.getMessage());
				return null;
			}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PpvApplication.class, args);
	}
}