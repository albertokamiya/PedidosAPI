package br.com.app.myapi.api.controller;

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

import br.com.app.myapi.model.Client;


public class ClientApiController {
	
	private static final Logger log = LogManager.getLogger(ClientApiController.class);

	@GetMapping("/clients")
	public ResponseEntity<List<Client>> listClient() {
		log.info(">listClient()");
		log.info("<listClient()");
			return new ResponseEntity<List<Client>>(HttpStatus.OK);	
	}
	
	@GetMapping("/client/{id}")
	public ResponseEntity<Client> getClient(@PathVariable(value="id") long id) {
		log.info(">getClient()");
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);	
	}

	@PostMapping("/client")
	public ResponseEntity<Client> addClient(@RequestBody @Valid Client client) {
		log.info(">addClient()");
			return new ResponseEntity<Client>(client,HttpStatus.OK);	
	}
		
	@PutMapping("/client")
	public ResponseEntity<Client> updateClient(@RequestBody @Valid Client client) {
		log.info(">updateClient()");
			return new ResponseEntity<Client>(client,HttpStatus.OK);	
	}
	
}
