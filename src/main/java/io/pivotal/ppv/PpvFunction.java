package io.pivotal.ppv;

import java.util.function.Function;


public class PpvFunction implements Function<String, Ppv> {

	public Ppv apply(String id) {
		Ppv ppv = new Ppv();
		ppv.setId(1L);
		ppv.setName("UFC:Khabib vs. McGregor");
		ppv.setPrice("65.00");
		ppv.setDateOfShowing("10/10/2018");
		return ppv;
		
	}
}