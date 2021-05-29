package com.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.blogPessoal.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	
	
	List<Postagem> findAllByTituloContaining(String titulo);
	/*idepedende da informação da url e banco de dados, 
	 * o sistema de caixa alta ou pequena é ignorado*/

	Optional<Postagem> findAllByTitulo(String titulo);
	
}
