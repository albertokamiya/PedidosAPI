package br.com.app.myapi.api.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.app.myapi.api.controller.dto.ProductDto;
import br.com.app.myapi.api.controller.form.ProductForm;
import br.com.app.myapi.api.controller.form.ProductUpdateForm;
import br.com.app.myapi.api.repository.ProductRepository;
import br.com.app.myapi.model.Product;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	@GetMapping
	public Page<ProductDto> listProducts(@PageableDefault(direction = Direction.DESC, page = 0, size = 10, sort = "name") Pageable pageable) {
		Page<Product> products = productRepository.findAll(pageable);
		return ProductDto.convert(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			ProductDto productDto = new ProductDto(product.get());
			return ResponseEntity.ok(productDto);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductForm productForm, UriComponentsBuilder uriComponentsBuilder) {
		Product product = productForm.generate();
		productRepository.save(product);
		
		URI uri = uriComponentsBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateForm productUpdateForm) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			Product productUpdated = productUpdateForm.updateProduct(id, productRepository);
			return ResponseEntity.ok(new ProductDto(productUpdated));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
