package com.generation.blogPessoal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.generation.blogPessoal.model.Tema;
import com.generation.blogPessoal.repository.TemaRepository;


@Service
public class TemaService {
	@Autowired
	private TemaRepository repository;
	
	
	public Optional<Tema> salvarDesc(Tema descricao) {
		Optional<Tema> novoDesc = repository.findAllByDescricao(descricao.getDescricao());
		if(novoDesc.isPresent()) {
			return Optional.empty();
		}else {
			return Optional.ofNullable(repository.save(descricao));
		}
	}
	
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
