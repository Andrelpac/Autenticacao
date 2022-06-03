package com.example.demo.model;

public class AutorDTO {

	private Integer id;

	private String nome;

	private String url;

	public AutorDTO() {
		super();
	}

	public AutorDTO(Integer id, String nome, String url) {
		super();
		this.id = id;
		this.nome = nome;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
