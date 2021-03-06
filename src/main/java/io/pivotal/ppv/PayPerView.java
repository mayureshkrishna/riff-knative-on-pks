package io.pivotal.ppv;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayPerView {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private String price;
	private String dateOfShowing;
}