package com.hkprogrammer.algafood.models;

public class Cliente {

	private Integer id;
	
	private String nome;
	
	private String email;
	
	private boolean ativo = false;
	
	public Cliente() {
		super();
	}
	
	public void ativar() {
		this.setAtivo(true);
	}

	public Cliente(Integer id, String nome, String email, boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.ativo = ativo;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
