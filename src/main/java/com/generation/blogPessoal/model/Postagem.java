package com.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

/*como a conexão do banco de dados é estabelecida no arquivo application.properties
 * na pasta src/main/resources, aqui criaremos as tabelas, atributo, definição dos tipos
 * e os get e set para inserir e buscar dados*/

@Entity
@Table(name = "postagem") //criar uma tabelo no bando de dados
public class Postagem {
	
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
	private long id;
	
	@NotNull //necessario agregar valores 
	@Size(min = 5, max = 100) //valores dos tipos de tabela
	private String titulo;
	
	@NotNull
	@Size(min = 10, max = 500)
	private String texto;	
	
	@Temporal(TemporalType.TIMESTAMP) //tipo date
	private Date date = new java.sql.Date(System.currentTimeMillis()); //data atual
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
