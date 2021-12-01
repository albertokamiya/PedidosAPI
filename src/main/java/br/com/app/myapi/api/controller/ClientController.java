package br.com.app.myapi.api.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.app.myapi.api.controller.dto.ClientDto;
import br.com.app.myapi.api.controller.form.ClientForm;
import br.com.app.myapi.api.repository.ClientRepository;
import br.com.app.myapi.model.Client;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientRepository clientRepository;

	@GetMapping("/hello")
	public void hello() {
		System.out.println("hello");
	}

	@GetMapping
	public Page<ClientDto> listClient(
			@PageableDefault(sort = "dtCreation", direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
		Page<Client> clients = clientRepository.findAll(pageable);
		return ClientDto.converter(clients);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ClientDto> addClient(@RequestBody @Valid ClientForm form, UriComponentsBuilder uriBuilder) {
		Client newClient = form.generate();
		clientRepository.save(newClient);

		URI uri = uriBuilder.path("/client/{id}").buildAndExpand(newClient.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClientDto(newClient));
	}

	public void updateClient() {

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteClient(@PathVariable Long id) {
		Optional<Client> client = clientRepository.findById(id);
		if (client.isPresent()) {
			clientRepository.delete(client.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
