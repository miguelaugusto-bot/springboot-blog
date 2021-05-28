package com.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogPessoal.model.Postagem;
import com.generation.blogPessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;
	
	/*encontrar todos os valores dentro do banco de dados
	 * */
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll(){
		//return ResponseEntity.ok(repository.findAll()); //retorna ok(200) para confirmar 
		//return ResponseEntity.status(202).body(repository.findAll()); //retorna accepted(202) para confirmar
		
		List<Postagem> listaDePostagem = repository.findAll();
		if(!listaDePostagem.isEmpty()) {
			return new ResponseEntity<>(listaDePostagem, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(listaDePostagem, HttpStatus.BAD_REQUEST);
		}
	}
	
	//equivalente ao um insert no mysql /  metodo post
	@PostMapping("/postar")
	public ResponseEntity<Postagem> salvarPostagem(@RequestBody Postagem posta) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(posta));
		
		/*
		Optional<Postagem> novoPost = repository.findAllByPostagem(posta.getTexto());
		if(novoPost.isPresent()) {
		return null;
		}else {
			
		}*/
	}
	
	/*trazer as informações com base no id do dado 
	 * ex: 
	 * localhost:8080/postagens/1*/
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	
	/*trazer as informações com base no titulo 
	 * ex: 
	 * localhost:8080/postagens/titulo/"um titulo do banco de dados"
	@GetMapping("/titulo/{titulo}") 
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}*/
	
}
