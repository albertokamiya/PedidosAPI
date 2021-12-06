package br.com.app.myapi.api.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.app.myapi.model.Product;

public class ProductForm {

	@NotEmpty @NotNull
	private String name;

	@NotEmpty @NotNull
	private double price;

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Product generate() {
		return new Product(name, price);
	}
}
