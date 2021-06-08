package com.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.generation.blogPessoal.model.Postagem;
import com.generation.blogPessoal.service.PostagemService;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

	
	private @Autowired PostagemService services;
	
	@GetMapping("/all")
	public ResponseEntity<List<Postagem>> GetAll(){	
		return services.getPosts();
	}
	
	@PostMapping("/postar")
	public Optional<Postagem> salvarPostagem(@RequestBody Postagem postar) {
		return services.salvarPostagem(postar);
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Optional<Postagem>> atualizarPostagem(@PathVariable(value = "id") Long id,@RequestBody Postagem postar){
		return services.atualizarPostagem(id, postar);
	}
	
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deletarPostagem(@PathVariable long id) {
		return services.deletar(id);
	}
	 
	@GetMapping("/procurar/id")
	public ResponseEntity<Postagem> GetById(@RequestParam(defaultValue = "") long id){
		return services.findByPost(id);
	}
	
	@GetMapping("/procurar/titulo")
	public ResponseEntity<List<Postagem>> GetByTitulo(@RequestParam(defaultValue = "") String titulo){
		return services.findByTitulo(titulo);
	}
	
}
