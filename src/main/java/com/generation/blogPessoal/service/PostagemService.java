package com.generation.blogPessoal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.generation.blogPessoal.model.Postagem;
import com.generation.blogPessoal.repository.PostagemRepository;

@Service
public class PostagemService {
	@Autowired
	private PostagemRepository repository;
	
	//getall
	public ResponseEntity<List<Postagem>> getPosts(){
		List<Postagem> listaDePostagem = repository.findAll();
		if(!listaDePostagem.isEmpty()) {
			return new ResponseEntity<>(listaDePostagem, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(listaDePostagem, HttpStatus.BAD_REQUEST);
		}
	}
	//getid
	public ResponseEntity<Postagem> findByPost(long id){
		return repository.findById(id)
				.map(postagem -> ResponseEntity.status(302).body(postagem))
				.orElse(ResponseEntity.status(404).build());
	}
	
	//gettitulo
	public ResponseEntity<List<Postagem>> findByTitulo(String titulo){
	List<Postagem> obterTitulo = repository.findAllByTituloContaining(titulo);
		if(!obterTitulo.isEmpty()) {
			return ResponseEntity.status(302).body(obterTitulo);
		}else {
			return ResponseEntity.status(404).build();
		}
	}
	
	//post
	public Optional<Postagem> salvarPostagem( Postagem postagem) {
		Optional<Postagem> novoPost = repository.findAllByTitulo(postagem.getTitulo());
		if(novoPost.isPresent()) {
			return Optional.empty();
		}else {
			return Optional.ofNullable(repository.save(postagem));
		}
	}
	
	//put
	public Optional<Postagem> atualizarPostagem(Long id, Postagem postagem) {
		Optional<Postagem> atualizarPost = repository.findById(id);
		if(atualizarPost.isPresent()) {
			atualizarPost.get().setTitulo(postagem.getTitulo());
			atualizarPost.get().setTexto(postagem.getTexto());
			return Optional.ofNullable(repository.save(atualizarPost.get()));
		}else {
			return (Optional.empty());
		}
	}
	
	//delete
	public ResponseEntity<?> deletar(long id){
		repository.deleteById(id); 
		return ResponseEntity.status(200).build();
	}
}

