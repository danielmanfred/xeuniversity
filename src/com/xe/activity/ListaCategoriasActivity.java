package com.xe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 

public class ListaCategoriasActivity extends ActionBarActivity {

	private ListView lv_materiais;
	String[] NOMES = {"Computação", "Farmácia", "Medicina"};
	ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_categorias);
	
		lv_materiais = (ListView) findViewById(R.id.lv_categorias);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, NOMES);
		lv_materiais.setAdapter(adapter);
		lv_materiais.setOnItemClickListener(chamarAtividade());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_categorias, menu);
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
			startActivity(new Intent(ListaCategoriasActivity.this, UsuarioActivity.class));
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public OnItemClickListener chamarAtividade() {
		return (new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent;
				
				switch (position) {
				case 0:
					intent = new Intent(getBaseContext(), ListaComputacaoActivity.class);
					startActivity(intent);
					break;
				default:
					break;
				}
				
			}
		});
	}
}
