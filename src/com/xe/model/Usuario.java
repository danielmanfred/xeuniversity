package com.xe.model;

@SuppressWarnings("serial")
public class Usuario extends Pessoa {

	private Integer nota;
	private String senha;
	
	public Integer getNota() {
		return nota;
	}
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
