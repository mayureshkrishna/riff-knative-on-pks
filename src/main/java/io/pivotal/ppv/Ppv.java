package io.pivotal.ppv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ppv {

	private String id;
	private String name;
	private String price;
	private String dateOfShowing;
}
