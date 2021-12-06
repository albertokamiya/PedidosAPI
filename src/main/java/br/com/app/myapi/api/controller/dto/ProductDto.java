package br.com.app.myapi.api.controller.dto;

import org.springframework.data.domain.Page;

import br.com.app.myapi.model.Product;

public class ProductDto {

	private Long id;

	private String name;

	private double price;
	
	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
	}

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static Page<ProductDto> convert(Page<Product> products) {
		return products.map(ProductDto::new);
	}

}
