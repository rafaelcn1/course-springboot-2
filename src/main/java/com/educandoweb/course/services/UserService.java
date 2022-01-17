package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseExecption;
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
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new DatabaseExecption(e.getMessage());
		}

	}

	public User update(Long id, User obj) {
		try {
			@SuppressWarnings("deprecation")
			User entity = repository.getOne(id); // getOne, é quase a mesma coisa que o findById
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	private void updateData(User entity, User obj) {
		// TODO Auto-generated method stub
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
