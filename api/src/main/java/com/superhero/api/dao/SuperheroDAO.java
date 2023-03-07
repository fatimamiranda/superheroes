package com.superhero.api.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.superhero.api.entity.Superhero;
import com.superhero.api.repository.SuperheroRepository;

@Component
public class SuperheroDAO {

	@Autowired
	SuperheroRepository repository;

	public Optional<List<Superhero>> findBySuperheroContaining(String word) {
		List<Superhero> list = new ArrayList<>();
		try {
			Optional<List<Superhero>> lists = repository.findByNameContaining(word);
			if (lists.isPresent()) {
				list = lists.get();
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

		return Optional.ofNullable(list);
	}

}
