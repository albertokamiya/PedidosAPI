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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.app.myapi.api.controller.dto.ClientDto;
import br.com.app.myapi.api.controller.form.ClientForm;
import br.com.app.myapi.api.controller.form.ClientUpdateForm;
import br.com.app.myapi.api.repository.ClientRepository;
import br.com.app.myapi.model.Client;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientRepository clientRepository;

	@GetMapping
	public Page<ClientDto> listClient(
			@PageableDefault(sort = "name", direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
		Page<Client> clients = clientRepository.findAll(pageable);
		return ClientDto.convert(clients);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> getClient(@PathVariable Long id) {
		Optional<Client> client = clientRepository.findById(id);
		if (client.isPresent()) {
			ClientDto clientDto = new ClientDto(client.get());
			return ResponseEntity.ok(clientDto);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ClientDto> addClient(@RequestBody @Valid ClientForm form, UriComponentsBuilder uriBuilder) {
		Client newClient = form.generate();
		clientRepository.save(newClient);

		URI uri = uriBuilder.path("/client/{id}").buildAndExpand(newClient.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClientDto(newClient));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody @Valid ClientUpdateForm form) {
		Optional<Client> currentClient = clientRepository.findById(id);
		if (currentClient.isPresent()) {
			Client client = form.updateClient(id, clientRepository);
			return ResponseEntity.ok(new ClientDto(client));			
		}
		return ResponseEntity.notFound().build();
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
