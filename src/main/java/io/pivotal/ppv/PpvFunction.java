package io.pivotal.ppv;

import java.util.function.Function;


public class PpvFunction implements Function<String, String> {

	public String apply(String id) {
		Ppv ppv = new Ppv();
		ppv.setId(1L);
		ppv.setName("UFC:Khabib vs. McGregor");
		ppv.setPrice("65.00");
		ppv.setDateOfShowing("10/10/2018");
		return "UFC:Khabib vs. McGregor";
		
	}
	
	public class Ppv {

		private Long id;
		private String name;
		private String price;
		private String dateOfShowing;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public String getDateOfShowing() {
			return dateOfShowing;
		}
		public void setDateOfShowing(String dateOfShowing) {
			this.dateOfShowing = dateOfShowing;
		}
}

}