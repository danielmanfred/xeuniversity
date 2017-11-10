package com.xe.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.xe.dao.ComputacaoDAO;
import com.xe.model.Material;

public class ListaComputacaoActivity extends ActionBarActivity {

	ListView lv_computacao;
	Button botao_adicionar;
	Material material;
	ComputacaoDAO materialDAO;
	ArrayList<Material> listaMateriais;
	ArrayAdapter<Material> adaptadorMateriais;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_computacao);

		lv_computacao = (ListView) findViewById(R.id.lv_computacao);

		registerForContextMenu(lv_computacao);

		botao_adicionar = (Button) findViewById(R.id.botao_adicionar);

		botao_adicionar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				adicionar(v);
			}
		});
		
		lv_computacao.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Material materialEnviado = (Material) adaptadorMateriais.getItem(position); 
				Intent intent = new Intent(ListaComputacaoActivity.this, MaterialActivity.class);
				intent.putExtra("material-enviado", materialEnviado);
				startActivity(intent);
			}
		});
		
		lv_computacao.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				material = adaptadorMateriais.getItem(position);
				return false;
			}
			
		});	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_computacao, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		
		if (id == R.id.action_perfil) {
			startActivity(new Intent(ListaComputacaoActivity.this, UsuarioActivity.class));
			return true;
		}
		
		return super.onOptionsItemSelected(item);
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
				materialDAO = new ComputacaoDAO(ListaComputacaoActivity.this);
				retorno = materialDAO.excluir(material);
				materialDAO.close();
				
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

	public void listar() {
		materialDAO = new ComputacaoDAO(this);
		
		listaMateriais = materialDAO.selecionarTodosMateriais();
		materialDAO.close();
		
		if (lv_computacao != null) {
			adaptadorMateriais = new ArrayAdapter<Material>(this, android.R.layout.simple_list_item_1, listaMateriais);
			lv_computacao.setAdapter(adaptadorMateriais);
		}
	}
	
	public void voltar(View view) {
    	startActivity(new Intent(this, UsuarioActivity.class));
    }
	
}
