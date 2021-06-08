package com.generation.blogPessoal.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogPessoal.model.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long>{
	
	public Optional<Tema> findAllByDescricao(String descricao);

	public List<Tema> findAllByDescricaoContaining(String descricao);
}
