package com.xe.model;

@SuppressWarnings("serial")
public abstract class Pessoa extends GenericModel {

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
