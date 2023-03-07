package com.superhero.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superhero.api.dao.SuperheroDAO;
import com.superhero.api.entity.Superhero;
import com.superhero.api.entity.SuperheroDTO;
import com.superhero.api.repository.SuperheroRepository;
import com.superhero.api.service.SuperheroService;

@Service
public class SuperheroServiceImpl implements SuperheroService {

	@Autowired
	SuperheroRepository repository;

	@Autowired
	SuperheroDAO dao;

	@Override
	public List<Superhero> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Superhero> getSuperheroById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<List<Superhero>> getSuperheroListByWord(String word) {
		return dao.findBySuperheroContaining(word);
	}

	@Override
	public void updateSuperhero(SuperheroDTO superheroDTO, Long id) {
		Optional<Superhero> superheroOptional = repository.findById(id);
		Superhero superhero = null;
		if (superheroOptional.isPresent()) {
			superhero = superheroOptional.get();
		} else {
			superhero = new Superhero();
		}
		superhero.setName(superheroDTO.getName());
		repository.save(superhero);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
