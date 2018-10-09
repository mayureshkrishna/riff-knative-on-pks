package io.pivotal.ppv;

import java.util.Collection;
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

	public String apply(String name) {
		String ppvAsJsonString = null;

		logger.info("Inside Apply - Input Name: " + name);

		try {

			Collection<Ppv> ppv = ppvRepository.findByName(name);

			logger.info("Find All Invoked");
			
			logger.info("Number of records: " + ppv.size());

			if (ppv.isEmpty()) {
				ppvAsJsonString = "{\"msg\":\"No Data Found\"\"}";
				logger.info("No Data Found");

			} else {

				ObjectMapper objectMapper = new ObjectMapper();
				ppvAsJsonString = objectMapper.writeValueAsString(ppv);
				logger.info("Retrieved: " + ppvAsJsonString);
			}
		} catch (JsonProcessingException jpe) {
			ppvAsJsonString = jpe.getMessage();
			logger.error("PPV Info Failed: " + ppvAsJsonString);
		} catch (Exception e) {
			ppvAsJsonString = e.getMessage();
			logger.error("PPV Info Failed: " + ppvAsJsonString);

		}
		return ppvAsJsonString;
	}

}