package com.xe.sql;

public class GenericSQL {

	public String destruirTabela(String tabela) {
		return "DROP TABLE IS EXISTS " + tabela + ";";
	}
}
