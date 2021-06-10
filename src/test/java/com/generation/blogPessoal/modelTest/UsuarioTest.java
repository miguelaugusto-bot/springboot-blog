package com.generation.blogPessoal.modelTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioTest {

	public Usuario usuario;
	
	@Autowired
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator(); 
	
	@BeforeEach
	public void start() {
		usuario = new Usuario(null, "MiguelNunes", "Miguel0102", "123456789");
	}
	
	@Test
	public void testValidatorUsuario() {
		usuario.setNome("MiguelNunes");
		usuario.setUsuario("Miguel0102");
		usuario.setSenha("123456789");
		
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		System.out.println(violations.toString());
		assertTrue(violations.isEmpty());
	}
}
