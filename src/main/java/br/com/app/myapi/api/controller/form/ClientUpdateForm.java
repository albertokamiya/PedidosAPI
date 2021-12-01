package br.com.app.myapi.api.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.app.myapi.api.repository.ClientRepository;
import br.com.app.myapi.model.Client;

public class ClientUpdateForm {

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

	public Client updateClient(Long id, ClientRepository clientRepository) {
		Client client = clientRepository.getOne(id);
		
		client.setName(name);
		client.setAddress(address);
		client.setPhone(phone);
		
		return client;
	}
	
}
