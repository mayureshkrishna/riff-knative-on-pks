package io.pivotal.ppv;

import java.util.function.Function;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PpvFunction implements Function<String, String> {

	public String apply(String id) {

		String ppvAsJSONString = null;
		try {
			Ppv ppv = new Ppv();
			ppv.setId("1");
			ppv.setName("UFC:Khabib vs. McGregor");
			ppv.setPrice("65.00");
			ppv.setDateOfShowing("10/10/2018");

			ObjectMapper objectMapper = new ObjectMapper();

			ppvAsJSONString = objectMapper.writeValueAsString(ppv);

		} catch (Exception e) {
			ppvAsJSONString = e.getMessage().toString();
		}
		return ppvAsJSONString;
	}

}