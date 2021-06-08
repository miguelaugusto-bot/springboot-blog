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

import com.generation.blogPessoal.model.Postagem;
import com.generation.blogPessoal.service.PostagemService;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

	
	private @Autowired PostagemService services;
	
	@GetMapping("/todes")
	public ResponseEntity<List<Postagem>> GetAll(){	
		return services.getPosts();
	}
	
	@PostMapping("/postar")
	public ResponseEntity<Postagem> salvarPostagem(@RequestBody Postagem postar) {
		return services.salvarPostagem(postar)
				.map(novoPost -> ResponseEntity.status(HttpStatus.CREATED).body(novoPost))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Postagem> atualizarPostagem(@PathVariable(value = "id") Long id,@RequestBody Postagem postar){
		return services.atualizarPostagem(id, postar)
				.map(postAtualizado -> ResponseEntity.status(HttpStatus.OK).body(postAtualizado))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
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
	public ResponseEntity<Object> GetByTitulo(@RequestParam(defaultValue = "") String titulo){
		return null;
	}
	
}
