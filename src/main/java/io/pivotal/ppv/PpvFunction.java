package io.pivotal.ppv;

import java.util.function.Function;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PpvFunction implements Function<String, String> {

	private Log logger = LogFactory.getLog(PpvFunction.class);
	
/*	@Autowired
	private final PpvRepository ppvRepository;
	
	PpvFunction(PpvRepository ppvRepository) {
		this.ppvRepository = ppvRepository;
		}*/
	
	public String apply(String id) {
		String ppvAsJsonString = null;
		long longId = Long.parseLong(id);
		try {
		
		
		
	//	Optional<Ppv> ppv = ppvRepository.findById(longId);
		
			Ppv ppv = new Ppv();
			ppv.setId(1L);
			ppv.setName("Khabib vs. McGregor");
			ppv.setPrice("64.99");
			ppv.setDateOfShowing("10/10/2018");
			
		/*if (ppv.isPresent())
		{	
			 
			ObjectMapper objectMapper = new ObjectMapper();
			
			
			
				ppvAsJsonString = objectMapper.writeValueAsString(ppv.get());
			
				logger.info("Retrieved: "+ ppvAsJsonString);
		}
		else 
			
		{
			ppvAsJsonString = "{\"msg\":\"No Data Found\"\"}";
			logger.info("No Data Found");
		}*/
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			
			
			ppvAsJsonString = objectMapper.writeValueAsString(ppv);
		
			logger.info("Retrieved: "+ ppvAsJsonString);
			
			
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