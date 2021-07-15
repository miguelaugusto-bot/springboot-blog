package com.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.generation.blogPessoal.model.UserLogin;
import com.generation.blogPessoal.model.Usuario;
import com.generation.blogPessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	/*Get para todos sos usuarios*/
	public ResponseEntity<List<Usuario>> getAll(){
		List<Usuario> listaUsuario = repository.findAll();
		if(!listaUsuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaUsuario);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	/*Get id usuario*/
	public ResponseEntity<Usuario> findByIdUser(Long id){
		return repository.findById(id)
				.map(user -> ResponseEntity.status(200).body(user))
				.orElse(ResponseEntity.status(404).build());
	}
	
	/*Put uusuario*/
	public Optional<Usuario> updateUser(Long id, Usuario usuario){
		Optional<Usuario> atualizarUsuario =  repository.findById(id);
		if(atualizarUsuario.isPresent()) {
			atualizarUsuario.get().setNome(usuario.getNome());
			atualizarUsuario.get().setFoto(usuario.getFoto());
			atualizarUsuario.get().setUsuario(usuario.getUsuario());
			atualizarUsuario.get().setSenha(usuario.getSenha());
			atualizarUsuario.get().setTipo(usuario.getTipo());
			return Optional.ofNullable(repository.save(atualizarUsuario.get()));
		}else {
			return Optional.empty();
		}
	}
	
	/*Delete usuario*/
	public ResponseEntity<Usuario> deleteUser(Long id){
		repository.deleteById(id);
		return ResponseEntity.status(200).build();
	}
	
	/*Cadastrar usuario*/
	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return repository.save(usuario);
	}
	
	/*Logar*/
	public Optional<UserLogin> Logar(Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setId(usuario.get().getId());
				user.get().setNome(usuario.get().getNome());
				user.get().setFoto(usuario.get().getFoto());
				user.get().setTipo(usuario.get().getTipo());
				
				
				return user;
				
			}
		}
		return null;
	}
}
