package com.xe.sql;

public class CategoriaSQL extends GenericSQL {

	public String criarTabela(String tabela, String id, String nome) {
		return "CREATE TABLE " + tabela + " ( " +
				id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				nome + " TEXT);";
	}
	
}
