package br.com.app.myapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.myapi.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
