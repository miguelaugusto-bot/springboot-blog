package com.generation.blogPessoal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.generation.blogPessoal.model.Tema;
import com.generation.blogPessoal.repository.TemaRepository;


@Service
public class TemaService {
	@Autowired
	private TemaRepository repository;
	
	//getall
	public ResponseEntity<List<Tema>> getAll(){
		List<Tema> listaTema = repository.findAll();
		if(!listaTema.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaTema);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	//post
	public Optional<Tema> salvarDesc(Tema descricao) {
		Optional<Tema> novoDesc = repository.findAllByDescricao(descricao.getDescricao());
		if(novoDesc.isPresent()) {
			return Optional.empty();
		}else {
			return Optional.ofNullable(repository.save(descricao));
		}
	}
	
	//put
	public Optional<Tema> atualizarTema(Long id, Tema descricao) {
		Optional<Tema> atualizarTema = repository.findById(id);
		if(atualizarTema.isPresent()) {
			atualizarTema.get().setDescricao(descricao.getDescricao());
			return Optional.ofNullable(repository.save(atualizarTema.get()));
		}else {
			return Optional.empty();
		}
	}
}
