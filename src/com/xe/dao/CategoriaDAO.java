package com.xe.dao;

import java.util.ArrayList;

import com.xe.model.Categoria;
import com.xe.sql.CategoriaSQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CategoriaDAO extends GenericDAO {
	
	private static final String TABELA = "categoria";
	private static final String NOME =  "nome";

	private CategoriaSQL sql;
	
	public CategoriaDAO(Context context) {
		super(context, BANCO, null, VERSAO);
		sql = new CategoriaSQL();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sql.criarTabela(TABELA, ID, NOME));
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(sql.destruirTabela(TABELA));
	}
	
	public long salvar(Categoria categoria) {
		ContentValues values = new ContentValues();
		long retorno;
		
		values.put(NOME, categoria.getNome());
		
		retorno = getWritableDatabase().insert(TABELA, null, values);
		
		return retorno;
	}
	
	public long alterar(Categoria categoria) {
		ContentValues values = new ContentValues();
		long retorno;
		
		values.put(NOME, categoria.getNome());
	
		String[] args = {String.valueOf(categoria.getId())};
		retorno = getWritableDatabase().update(TABELA, values, "id=?", args);
		
		return retorno;
	}
	
	public long excluir(Categoria categoria) {
		long retorno;
	
		String[] args = {String.valueOf(categoria.getId())};
		retorno = getWritableDatabase().delete(TABELA, "id=?", args);
		
		return retorno;
	}
	
	public ArrayList<Categoria> selecionarTodasCategorias() {
		String[] colunas = {ID, NOME};
		Cursor cursor = getWritableDatabase().query(TABELA, colunas, null, null, null, null, "upper(nome)");
		
		ArrayList<Categoria> lista = new ArrayList<Categoria>();
		
		while (cursor.moveToNext()) {
			Categoria categoria = new Categoria();
			
			categoria.setId(cursor.getLong(0));
			categoria.setNome(cursor.getString(1));
			
			lista.add(categoria);
		}
		
		return lista;
	}
}
