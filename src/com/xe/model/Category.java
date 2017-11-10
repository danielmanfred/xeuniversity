package com.xe.model;

public enum Category {
	ADMINSTRACAO(1), BIOLOGIA(2), COMPUTACAO(3), ENGENHARIAS(4), MEDICINA(5);
	
	private final int valor;
	
	Category(int opcao) {
		valor = opcao;
	}
	
	public int getValor() {
		return valor;
	}
}
