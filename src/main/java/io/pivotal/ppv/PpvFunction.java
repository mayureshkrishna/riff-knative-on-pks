package io.pivotal.ppv;

import java.util.function.Function;

public class PpvFunction implements Function<String, String> {

	public String apply(String id) {
			Ppv ppv = new Ppv();
			ppv.setId("1");
			ppv.setName("UFC:Khabib vs. McGregor");
			ppv.setPrice("65.00");
			ppv.setDateOfShowing("10/10/2018");
			
			String ppvAsJsonString = "{\"id\":\"" + ppv.getId() + "\"," +
									 "{\"name\":\"" + ppv.getName() + "\"," +
									 "{\"price\":\"" + ppv.getPrice() + "\"," +
									 "{\"dateOfShowing\":\"" + ppv.getDateOfShowing() + "\"}";
				return ppvAsJsonString;
	}

}