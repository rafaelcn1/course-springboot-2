package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@Service // Registrando a class como componente do Spring
public class UserService {

	@Autowired // Injetar a dependencia de forma transparente para o usuário
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.findById(id);
	}

	public User update(Long id, User obj) {
		@SuppressWarnings("deprecation")
		User entity = repository.getOne(id); // getOne, é quase a mesma coisa que o findById
		updateData(entity, obj);
		return repository.save(entity);

	}

	private void updateData(User entity, User obj) {
		// TODO Auto-generated method stub
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
