package com.superhero.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Preconditions;
import com.superhero.api.entity.Superhero;
import com.superhero.api.entity.SuperheroDTO;
import com.superhero.api.service.SuperheroService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/api")
public class SuperheroController {

	@Autowired
	SuperheroService service;

	@GetMapping(path = "/superheros")
	public ResponseEntity<List<SuperheroDTO>> getAllSuperheros() {
		List<SuperheroDTO> listSuperHeros =  new ArrayList<SuperheroDTO>();
		try {
			listSuperHeros = createList(service.findAll());
			return ResponseEntity.ok().body(listSuperHeros);
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return ResponseEntity.badRequest().body(listSuperHeros);

	}

	@ApiOperation(value = "Get superhero by id", notes = "")
	@GetMapping(path = "/superheros/id/{id}")
	public ResponseEntity<SuperheroDTO> getSuperheroById(
			@PathVariable("id") @ApiParam(name = "id", value = "Superhero id", example = "1") Long id) {
		SuperheroDTO superhero = new SuperheroDTO();
		try {
			Optional<Superhero> superheroOptional = service.getSuperheroById(id);
			if (superheroOptional.isPresent()) {
				return ResponseEntity.ok().body(mapToDTO(superheroOptional.get()));
			}
		} catch (Exception e) {

		}
		return ResponseEntity.badRequest().body(superhero);

	}

	@ApiOperation(value = "Get superhero list contains word", notes = "")
	@GetMapping(path = "/superheros/{word}")
	public ResponseEntity<List<SuperheroDTO>> getSuperheroByParam(
			@PathVariable("word") @ApiParam(name = "word", value = "", example = "man") String word) {
		List<SuperheroDTO> listSuperHeros = new ArrayList<SuperheroDTO>();
		try {
			Optional<List<Superhero>> superheroListOptional = service.getSuperheroListByWord(word);
			System.out.println(superheroListOptional.get().size());
			if (superheroListOptional.isPresent()) {
				listSuperHeros = createList(superheroListOptional.get());
				return ResponseEntity.ok().body(listSuperHeros);
			}
		} catch (Exception e) {

		}
		return ResponseEntity.badRequest().body(listSuperHeros);

	}

	@ApiOperation(value = "", notes = "")
	@PutMapping(path = "/superheros/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateSuperhero(@RequestBody SuperheroDTO superhero,
			@PathVariable("id") @ApiParam(name = "id", value = "", example = "1") Long id) {
		Preconditions.checkNotNull(superhero);
		try {
			service.updateSuperhero(superhero, id);
		} catch (Exception e) {

		}

	}

	@DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
	
	public List<SuperheroDTO> createList(List<Superhero> list) {
		List<SuperheroDTO> listSuper =  new ArrayList<SuperheroDTO>();
		if (!list.isEmpty()) {
			for (Superhero superhero : list) {
				SuperheroDTO dto = new SuperheroDTO();
				dto.setId(superhero.getId());
				dto.setName(superhero.getName());
				listSuper.add(dto);
			}
		}
		return listSuper;

	}

	public SuperheroDTO mapToDTO(Superhero entity) {
		SuperheroDTO dto = new SuperheroDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;

	}

}
