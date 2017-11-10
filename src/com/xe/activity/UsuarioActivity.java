package com.xe.activity;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class UsuarioActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usuario);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.usuario, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if (id == R.id.action_tela_inicial) {
			startActivity(new Intent(UsuarioActivity.this, MainActivity.class));
			return true;
		}
		
		if (id == R.id.action_categorias) {
			alerta("Ir para categoria");
			return true;
		}
		
		if (id == R.id.action_settings) {
			alerta("Menu configurações");
			return true;
		}
		
		if (id == R.id.action_sair) {
			startActivity(new Intent(UsuarioActivity.this, LoginActivity.class));
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private void alerta(String s) {
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}
	
	public void irCategorias(View view) {
		startActivity(new Intent(UsuarioActivity.this, CategoriaActivity.class));
	}
	
	public void ofertar(View view) {
		startActivity(new Intent(UsuarioActivity.this, ListaCategoriasActivity.class));
	}
	
	public void demandar(View view) {
		startActivity(new Intent(UsuarioActivity.this, ListaCategoriasActivity.class));
	}
}
