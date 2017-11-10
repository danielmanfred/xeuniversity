package com.xe.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.xe.model.Material;
import com.xe.sql.MaterialSQL;

public abstract class MaterialDAO extends GenericDAO {
 
	protected static final String NOME = "nome";
	protected static final String DESCRICAO = "descricao";
	
	protected MaterialSQL sql;
	
	public MaterialDAO(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public abstract long excluir(Material material);
	
	public abstract ArrayList<Material> selecionarTodosMateriais();
}
