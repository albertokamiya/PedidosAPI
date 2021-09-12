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
import org.springframework.web.bind.annotation.RestController;

import br.com.app.myapi.dao.SQliteManager;
import br.com.app.myapi.model.Client;

@RestController
public class ClientApiController extends MyApiController {
	
	private static final Logger log = LogManager.getLogger(ClientApiController.class);

	@GetMapping("/clients")
	public ResponseEntity<List<Client>> listClient() {
		log.info(">listClient()");
		SQliteManager sq = new SQliteManager();
		List<Client> list = sq.getAllClient();
		sq.connectionClose();
		log.info("<listClient()");
		if (list.isEmpty()) {
			return new ResponseEntity<List<Client>>(HttpStatus.OK);	
		}
		return new ResponseEntity<List<Client>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/client/{id}")
	public ResponseEntity<Client> getClient(@PathVariable(value="id") long id) {
		log.info(">getClient()");
		SQliteManager sq = new SQliteManager();
		Client client = sq.getClient(id);
		sq.connectionClose();
		if (client.getId() == 0) {
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);	
		}
		log.info("<getClient()");
		return new ResponseEntity<Client>(client,HttpStatus.OK);
	}

	@PostMapping("/client")
	public ResponseEntity<Client> addClient(@RequestBody @Valid Client client) {
		log.info(">addClient()");
		SQliteManager sq = new SQliteManager();
		boolean isInserted = sq.insertClient(client);
		sq.connectionClose();
		log.info("<addClient()");
		if (isInserted) {
			return new ResponseEntity<Client>(client,HttpStatus.OK);	
		}
		return new ResponseEntity<Client>(HttpStatus.NOT_ACCEPTABLE);
	}
		
	@PutMapping("/client")
	public ResponseEntity<Client> updateClient(@RequestBody @Valid Client client) {
		log.info(">updateClient()");
		SQliteManager sq = new SQliteManager();
		boolean isUpdated = sq.updateClient(client);
		sq.connectionClose();
		log.info("<updateClient()");
		if (isUpdated) {
			return new ResponseEntity<Client>(client,HttpStatus.OK);	
		}
		return new ResponseEntity<Client>(client,HttpStatus.NOT_MODIFIED);
	}
	
}
