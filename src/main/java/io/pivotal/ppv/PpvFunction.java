package io.pivotal.ppv;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

public class PpvFunction implements Function<String, String> {

	private Log logger = LogFactory.getLog(PpvFunction.class);
	
	private final PpvRepository ppvRepository;
	
	PpvFunction(PpvRepository ppvRepository) {
		this.ppvRepository = ppvRepository;
		}
	
	public String apply(String id) {
			
		String ppvAsJsonString = null;
		Optional<Ppv> ppv = ppvRepository.findById(id);
		
		if (ppv.isPresent())
		{	
			 ppvAsJsonString = "{\"id\":\"" + ppv.get().getId() + "\"," +
									 "\"name\":\"" + ppv.get().getName() + "\"," +
									 "\"price\":\"" + ppv.get().getPrice() + "\"," +
									 "\"dateOfShowing\":\"" + ppv.get().getDateOfShowing() + "\"}";
				
				logger.info("Retrieved: "+ ppvAsJsonString);
		}
		else 
			
		{
			ppvAsJsonString = "{\"msg\":\"No Data Found\"\"}";
			logger.info("No Data Found");
		}
		return ppvAsJsonString;
	}
	
	
	

}

@Component
class Initializer implements ApplicationRunner {

	private Log loggerInit = LogFactory.getLog(Initializer.class);
private final PpvRepository ppvRepository;
	
Initializer(PpvRepository ppvRepository) {
		this.ppvRepository = ppvRepository;
		}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Stream.of("Khabib vs. McGregor", "Woodley vs. Till", "WWE: Evolution", "Soccer: Peru vs. Chile",
				"Dan Cummins: Don’t Wake The Bear")
				.forEach(name -> ppvRepository.save(new Ppv(null, name, "64.99", "10/10/2018")));

		ppvRepository.findAll().forEach(ppv -> loggerInit
				.info("Ppv Info: " + ppv.getName() + " ," + ppv.getPrice() + " ," + ppv.getDateOfShowing()));
		
	}
	
}