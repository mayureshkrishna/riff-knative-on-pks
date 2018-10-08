package io.pivotal.ppv;

import java.util.Optional;
import java.util.function.Function;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PpvFunction implements Function<String, String> {

	private Log logger = LogFactory.getLog(PpvFunction.class);
	
	@Autowired
	private PpvRepository ppvRepository;
	

	public String apply(String id) {
		String ppvAsJsonString = null;
		long longId = Long.parseLong(id);
		try {
		
		
		
		Optional<Ppv> ppv = ppvRepository.findById(longId);
		
		
		if (ppv.isPresent())
		{	
			 
			ObjectMapper objectMapper = new ObjectMapper();
			
			
			
				ppvAsJsonString = objectMapper.writeValueAsString(ppv.get());
			
				logger.info("Retrieved: "+ ppvAsJsonString);
		}
		else 
			
		{
			ppvAsJsonString = "{\"msg\":\"No Data Found\"\"}";
			logger.info("No Data Found");
		}
		} catch (JsonProcessingException jpe) {
			ppvAsJsonString = jpe.getMessage().toString();
			logger.error("PPV Info Failed: "+ppvAsJsonString);
		}
		catch (Exception e) {
			ppvAsJsonString = e.getMessage().toString();
			logger.error("PPV Info Failed: "+ppvAsJsonString);

		}
		return ppvAsJsonString;
	}
	
	
}