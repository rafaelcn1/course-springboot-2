package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping // Para buscar dados em urls
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}") // Quer dizer que a url vai aceitar um tipo id
	public ResponseEntity<User> findById(@PathVariable Long id) { // @PathVariable serve para receber o id pela URL
		User user = service.findById(id);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping // Para inserção de daods
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);

		// Poderia ser abaixo, mas normalmente se trabalha com o cod 201
		// return ResponseEntity.ok().body(user);
	}

	@DeleteMapping(value = "/{id}") // Anotação para delete
	public ResponseEntity<Void> delete(@PathVariable Long id) { // @PathVariable para determina na url
		service.delete(id);
		// Não vai me retornar nada na página
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}") // Anotação para update
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
