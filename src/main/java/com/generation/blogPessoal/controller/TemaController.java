package com.generation.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogPessoal.model.Tema;
import com.generation.blogPessoal.repository.TemaRepository;
import com.generation.blogPessoal.service.TemaService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tema")
public class TemaController {
	
	
	private @Autowired TemaRepository repository;
	private @Autowired TemaService services;
	
	@GetMapping("/all")
	public ResponseEntity<List<Tema>> GetAll(){	
		return services.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@GetMapping("/procurar")
	public ResponseEntity<List<Tema>> getByName(@RequestParam(value = "") String descricao){
		return services.findByTema(descricao);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Tema> post(@RequestBody Tema tema){
		return services.salvarDesc(tema)
				.map(resp -> ResponseEntity.status(HttpStatus.ACCEPTED).body(tema))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Tema> put(@PathVariable(value = "id") Long id,@RequestBody Tema tema){
		return services.atualizarTema(id, tema)
				.map(resp -> ResponseEntity.status(HttpStatus.ACCEPTED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable long id){
		repository.deleteById(id);
	}

	
}
