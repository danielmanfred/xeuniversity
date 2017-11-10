package com.xe.model;


@SuppressWarnings("serial")
public class Material extends GenericModel {
	
	private String nome;
	private String descricao;
	
	public Material() {
		super();
	}
	
	public Material(String material, String descricao) {
		super();
		this.nome = material;
		this.descricao = descricao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	@Override
	public String toString() {
		return nome.toString();
	}
	
}
