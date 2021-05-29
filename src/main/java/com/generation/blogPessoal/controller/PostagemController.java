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
import com.generation.blogPessoal.model.Tema;
import com.generation.blogPessoal.repository.PostagemRepository;
import com.generation.blogPessoal.service.PostagemService;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

	
	private @Autowired PostagemRepository repository;
	private @Autowired PostagemService services;
	
	/*encontrar todos os post realizados
	 * 
	 * outros exemplos de como realizar:
	 * 	1. return ResponseEntity.ok(repository.findAll()); //retorna ok(200) para confirmar 
		2. return ResponseEntity.status(202).body(repository.findAll()); //retorna accepted(202) para confirmar
	 * */
	@GetMapping("/todes")
	public ResponseEntity<List<Postagem>> GetAll(){	
		List<Postagem> listaDePostagem = repository.findAll();
		if(!listaDePostagem.isEmpty()) {
			return new ResponseEntity<>(listaDePostagem, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(listaDePostagem, HttpStatus.BAD_REQUEST);
		}
	}
	
	/*equivalente ao um insert no mysql / metodo post
	 * 
	 * outra forma de realizar determinada ação
	 * 1. return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(posta));
	 * */
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
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	 
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo")
	public ResponseEntity<Object> GetByTitulo(@RequestParam(defaultValue = "") String titulo){
		List<Postagem> obterTitulo = repository.findAllByTituloContaining(titulo);
		if(!obterTitulo.isEmpty()) {
			return ResponseEntity.status(202).body(obterTitulo);
		}else {
			return ResponseEntity.status(200).body("num achou");
		}
	}
	
	
	
	/*trazer as informações com base no titulo 
	 * ex: 
	 * localhost:8080/postagens/titulo/"um titulo do banco de dados"
	@GetMapping("/titulo/{titulo}") 
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}*/
	
}
