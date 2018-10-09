package io.pivotal.ppv;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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
	public Function<String, List<PayPerView>> getpayperviewbyname() {

		return name -> {

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
				logger.error("PPV Info Failed: " + e.getMessage());
				return null;

			}
		};
	}
	
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
	
	@Bean
	public Supplier <List<PayPerView>> getpayperview() {
		
		return() -> {
		try {
			List<PayPerView> ppv = ppvRepository.findAll();
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
			logger.error("PPV Info Failed: " + e.getMessage());
			return null;

		}
		};
	}
	
	@Bean
	public Consumer <PayPerView> savepayperview() {
		
		return ppv ->
		{
		try {
			if (ppv == null) {
				logger.info("No Data Provided");

			} else {
				
				ppvRepository.save(ppv);
				logger.info("Saved: " + ppv.toString());
			}
		} 
		
		catch (Exception e) {
			logger.error("PPV Info Failed: " + e.getMessage());
		}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(PpvApplication.class, args);
	}
}