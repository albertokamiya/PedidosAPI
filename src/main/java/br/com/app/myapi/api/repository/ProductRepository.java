package br.com.app.myapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.myapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
