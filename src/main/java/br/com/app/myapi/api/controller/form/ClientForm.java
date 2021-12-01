package br.com.app.myapi.api.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.app.myapi.model.Client;

public class ClientForm {
	
	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String address;
	
	@NotNull @NotEmpty @Length(min = 10)
	private String phone;

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Client generate() {
		return new Client(name, address, phone);
	}

}
