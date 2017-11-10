package com.xe.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xe.model.Usuario;
import com.xe.sql.UsuarioSQL;

public class UsuarioDAO extends GenericDAO {

	private static final String TABELA = "usuario";
	private static final String NOME = "nome";
	private static final String SENHA = "senha";
	private static final String NOTA = "nota";
	
	private UsuarioSQL sql;

	public UsuarioDAO(Context context) {
		super(context, BANCO, null, VERSAO);
		sql = new UsuarioSQL();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sql.criarTabela(TABELA, ID, NOME, SENHA, NOTA));
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(sql.destruirTabela(TABELA));
	}
	
	public long salvar(Usuario usuario) {
		ContentValues values = new ContentValues();
		long retorno;
		usuario.setNota(10);
		
		values.put(NOME, usuario.getNome());
		values.put(SENHA, usuario.getSenha());
		values.put(NOTA, usuario.getNota());
		
		retorno = getWritableDatabase().insert(TABELA, null, values);
		
		return retorno;
	}
}
