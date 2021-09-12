package br.com.app.myapi.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.myapi.dao.SQliteManager;
import br.com.app.myapi.model.Product;

@RestController
public class ProductApiController extends MyApiController {

	private static final Logger log = LogManager.getLogger(ProductApiController.class);
	
	@GetMapping("/product")
	public ResponseEntity<List<Product>> listProduct() {
		log.info(">listProduct");
		SQliteManager sq = new SQliteManager();
		List<Product> list = sq.getAllProduct();
		sq.connectionClose();
		log.info("<listProduct");
		if (list.isEmpty()) {
			return new ResponseEntity<List<Product>>(new ArrayList<Product>(), HttpStatus.OK);	
		}
		return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable(value="id") long id) {
		log.info(">getProduct");
		SQliteManager sq = new SQliteManager();
		Product product = sq.getProduct(id);
		sq.connectionClose();
		log.info("<getProduct");
		if (product.getId() == 0) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}

	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product) {
		log.info(">addProduct");
		SQliteManager sq = new SQliteManager();
		boolean isInserted = sq.insertProduct(product);
		sq.connectionClose();
		log.info("<addProduct");
		if (isInserted) {
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		}
		return new ResponseEntity<Product>(product,HttpStatus.NOT_ACCEPTABLE);	
	}
		
	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product product) {
		log.info(">addProduct");
		SQliteManager sq = new SQliteManager();
		boolean isInserted = sq.updateProduct(product);
		sq.connectionClose();
		log.info("<addProduct");
		if (isInserted) {
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_MODIFIED);	
	}
	
}
