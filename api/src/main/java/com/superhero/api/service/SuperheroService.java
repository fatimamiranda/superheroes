package com.superhero.api.service;

import java.util.List;
import java.util.Optional;

import com.superhero.api.entity.Superhero;
import com.superhero.api.entity.SuperheroDTO;

public interface SuperheroService {

	List<Superhero> findAll();

	Optional<Superhero> getSuperheroById(Long id);

	Optional<List<Superhero>> getSuperheroListByWord(String word);

	void updateSuperhero(SuperheroDTO superhero, Long id);

	void deleteById(Long id);

}
