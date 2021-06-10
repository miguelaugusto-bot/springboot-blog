package com.generation.blogPessoal.repositoryTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogPessoal.model.Usuario;
import com.generation.blogPessoal.repository.UsuarioRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	public void start() {
		Usuario usuario = new Usuario( "MiguelNunes", "Miguel0102", "123456789");
		if(usuarioRepository.findByUsuario(usuario.getUsuario()) != null)
			usuarioRepository.save(usuario);
		
	}
	
	@Test
	public void findByNomeRetornaUsuario() throws Exception{
		Usuario usuario = usuarioRepository.findByUsuario("Miguel0102").get();
		assertTrue(usuario.getUsuario().equals("Miguel0102"));

	}
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
	
	
}
