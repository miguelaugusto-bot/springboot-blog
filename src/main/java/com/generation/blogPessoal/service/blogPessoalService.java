package com.generation.blogPessoal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.blogPessoal.repository.PostagemRepository;

@Service
public class blogPessoalService {
	
	@Autowired
	private PostagemRepository repository;
	
	
}
