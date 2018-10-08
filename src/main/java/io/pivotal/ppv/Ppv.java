package io.pivotal.ppv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ppv {


		//@Id
		//@GeneratedValue
		private Long id;
		private String name;
		private String price;
		private String dateOfShowing;

	
}
