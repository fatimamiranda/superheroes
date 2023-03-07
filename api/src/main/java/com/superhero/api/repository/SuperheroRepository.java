package com.superhero.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.superhero.api.entity.Superhero;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Long> {

	@Query(value = "select s.id, s.name from superheros s where s.name like %:word%", nativeQuery = true)
	Optional<List<Superhero>> findByNameContaining(String word);

}
