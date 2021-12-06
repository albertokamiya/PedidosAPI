package br.com.app.myapi.api.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.app.myapi.api.repository.ProductRepository;
import br.com.app.myapi.model.Product;

public class ProductUpdateForm {

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

	public Product updateProduct(Long id, ProductRepository productRepository) {
		Product product = productRepository.getOne(id);
		product.setName(name);
		product.setPrice(price);
		
		return product;
	}

	
}
