package com.xe.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.xe.dao.ComputacaoDAO;
import com.xe.dao.MaterialDAO;
import com.xe.model.Material;

public class ListaMateriaisActivity extends Activity {

	ListView lv_materiais;
	Button botao_adicionar;
	Material material;
	MaterialDAO cdao;
	ArrayList<Material> listaMateriais;
	ArrayAdapter<Material> adaptadorMateriais;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_materiais);
		
		lv_materiais = (ListView) findViewById(R.id.lv_materiais);
		
		registerForContextMenu(lv_materiais);
		
		botao_adicionar = (Button) findViewById(R.id.botao_adicionar);
		
		botao_adicionar.setOnClickListener(new View.OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				adicionar(v);
			}
		});
		
		lv_materiais.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Material materialEnviado = (Material) adaptadorMateriais.getItem(position); 
				Intent intent = new Intent(ListaMateriaisActivity.this, MaterialActivity.class);
				intent.putExtra("material-enviado", materialEnviado);
				startActivity(intent);
			}
		});
		
		lv_materiais.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				material = adaptadorMateriais.getItem(position);
				return false;
			}
			
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		listar();
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuItem itemExcluir = menu.add("Apagar material");
		itemExcluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				long retorno;
				cdao = new ComputacaoDAO(ListaMateriaisActivity.this);
				retorno = cdao.excluir(material);
				cdao.close();
				
				if (retorno == -1) {
					alerta("Erro de exclusão");
				}
				else {
					alerta("Material excluído com sucesso");
				}
				
				listar();
				return false;
			}
		});
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	private void alerta(String s) {
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}
	
	public void adicionar(View view) {
    	startActivityForResult(new Intent(this, MaterialActivity.class), 1);
    }
	
	public void listar() {/*
		cdao = new MaterialDAO(this);
		
		listaMateriais = cdao.selecionarTodosMateriais();
		cdao.close();
		
		if (lv_materiais != null) {
			adaptadorMateriais = new ArrayAdapter<Material>(this, android.R.layout.simple_list_item_1, listaMateriais);
			lv_materiais.setAdapter(adaptadorMateriais);
		}*/
	}
}
