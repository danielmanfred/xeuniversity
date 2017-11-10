package com.xe.sql;

public class UsuarioSQL extends GenericSQL {

	public String criarTabela(String tabela, String id, String nome, String senha, String nota) {
		return "CREATE TABLE " + tabela + " ( " +
				id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				nome + " TEXT, " +
				senha + " TEXT, " +
				nota + " INTEGER);";
	}
}
