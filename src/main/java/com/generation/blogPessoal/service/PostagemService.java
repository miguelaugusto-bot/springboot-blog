package com.generation.blogPessoal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.generation.blogPessoal.model.Postagem;
import com.generation.blogPessoal.repository.PostagemRepository;

@Service
public class PostagemService {
	@Autowired
	private PostagemRepository repository;
	
	
	public Optional<Postagem> salvarPostagem( Postagem postagem) {
		Optional<Postagem> novoPost = repository.findAllByTitulo(postagem.getTitulo());
		if(novoPost.isPresent()) {
			return Optional.empty();
		}else {
			return Optional.ofNullable(repository.save(postagem));
		}
	}
	
	public Optional<Postagem> atualizarPostagem(Long id, Postagem postagem) {
		Optional<Postagem> atualizarPost = repository.findById(id);
		if(atualizarPost.isPresent()) {
			atualizarPost.get().setTitulo(postagem.getTitulo());
			atualizarPost.get().setTexto(postagem.getTexto());
			return Optional.ofNullable(repository.save(atualizarPost.get()));
		}else {
			return Optional.empty();
		}
	}
}

