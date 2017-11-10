package com.xe.sql;

public class MaterialSQL extends GenericSQL {

	public String criarTabela(String tabela, String id, String nome, String descricao) {
		System.out.println("ct00");
		return "CREATE TABLE " + tabela + " ( " +
				id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				nome + " TEXT, " +
				descricao + " TEXT " + "); ";
	}
}
