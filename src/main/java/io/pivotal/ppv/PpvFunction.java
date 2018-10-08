package io.pivotal.ppv;

import java.util.List;
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

		logger.info("Inside Apply - Input Id: " + id);

		try {

			// long longId = Long.parseLong(id);

			List<Ppv> ppv = ppvRepository.findAll();

			logger.info("Find All Invoked");

			if (!ppv.isEmpty()) {
				ObjectMapper objectMapper = new ObjectMapper();

				ppvAsJsonString = objectMapper.writeValueAsString(ppv);

				logger.info("Retrieved: " + ppvAsJsonString);
			} else {
				ppvAsJsonString = "{\"msg\":\"No Data Found\"\"}";
				logger.info("No Data Found");
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