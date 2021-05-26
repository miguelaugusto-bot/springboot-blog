package com.generation.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogPessoal.model.Postagem;
import com.generation.blogPessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repositoty;
	
	/*encontrar todos os valores dentro do banco de dados
	 * */
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repositoty.findAll());
	}
	
	/*trazer as informações com base no id do dado 
	 * ex: 
	 * localhost:8080/postagens/1*/
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		return repositoty.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	/*trazer as informações com base no titulo 
	 * ex: 
	 * localhost:8080/postagens/titulo/"um titulo do banco de dados"*/
	@GetMapping("/titulo/{titulo}") 
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repositoty.findAllByTituloContainingIgnoreCase(titulo));
	}
	
}
