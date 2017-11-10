package com.xe.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xe.model.Material;
import com.xe.sql.MaterialSQL;

public class ComputacaoDAO extends MaterialDAO {
	private static final String TABELA = "computacao"; 
	
	public ComputacaoDAO(Context context) {
		super(context, BANCO, null, VERSAO);
		sql = new MaterialSQL();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sql.criarTabela(TABELA, ID, NOME, DESCRICAO));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(sql.destruirTabela(TABELA));
		onCreate(db);
	}

	public long salvar(Material material) {
		System.out.println("s00");
		ContentValues values = new ContentValues();
		long retorno;
		
		values.put(NOME, material.getNome());
		values.put(DESCRICAO, material.getDescricao());
		
		retorno = getWritableDatabase().insert(TABELA, null, values);
		System.out.println("s01");
		return retorno;
	}
	
	public long alterar(Material material) {
		ContentValues values = new ContentValues();
		long retorno;
		
		values.put(NOME, material.getNome());
		values.put(DESCRICAO, material.getDescricao());
	
		String[] args = {String.valueOf(material.getId())};
		retorno = getWritableDatabase().update(TABELA, values, "id=?", args);
		
		return retorno;
	}
	
	public long excluir(Material material) {
		long retorno;
	
		String[] args = {String.valueOf(material.getId())};
		retorno = getWritableDatabase().delete(TABELA, "id=?", args);
		
		return retorno;
	}
	
	public ArrayList<Material> selecionarTodosMateriais() {
		String[] colunas = {ID, NOME, DESCRICAO};
		Cursor cursor = getWritableDatabase().query(TABELA, colunas, null, null, null, null, "upper(nome)");
		
		ArrayList<Material> lista = new ArrayList<Material>();
		
		while (cursor.moveToNext()) {
			Material material = new Material();
			
			material.setId(cursor.getLong(0));
			material.setNome(cursor.getString(1));
			material.setDescricao(cursor.getString(2));
			
			lista.add(material);
		}
		
		return lista;
	}
}
