package io.pivotal.ppv;

import java.util.Optional;
import java.util.function.Function;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import io.pivotal.ppv.repository.PpvRepository;

@ComponentScan("io.pivotal.ppv")
public class PpvFunction implements Function<String, String> {

	private Log logger = LogFactory.getLog(PpvFunction.class);
	
	@Autowired
	private final PpvRepository ppvRepository;
	
	PpvFunction(PpvRepository ppvRepository) {
		this.ppvRepository = ppvRepository;
		}
	
	public String apply(String id) {
			
		String ppvAsJsonString = null;
		long longId = Long.parseLong(id);
		
		Optional<Ppv> ppv = ppvRepository.findById(longId);
		
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