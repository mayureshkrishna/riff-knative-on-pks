package io.pivotal.ppv;

import java.util.List;
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
	public Function<String, List<PayPerView>> payperview() {

		return name -> {

			String ppvAsJsonString = null;
			try {
				List<PayPerView> ppv = ppvRepository.findByName(name);
				logger.info("Number of records: " + ppv.size());

				if (ppv.isEmpty()) {
					logger.info("No Data Found");
					return null;

				} else {

					logger.info("Retrieved: " + ppv.toString());
					return ppv;
					
				}
			} 
			
			catch (Exception e) {
				ppvAsJsonString = e.getMessage();
				logger.error("PPV Info Failed: " + ppvAsJsonString);
				return null;

			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(PpvApplication.class, args);
	}
}