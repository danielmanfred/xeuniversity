package com.xe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xe.dao.UsuarioDAO;
import com.xe.model.Usuario;

public class CadastroUsuarioActivity extends ActionBarActivity {

	EditText et_nome;
	EditText et_senha;
	EditText et_senha2;
	Button botao_cadastrar;
	Button botao_cancelar;
	Usuario usuario;
	UsuarioDAO dao;
	long retorno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_usuario);
		salvar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_usuario, menu);
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
		return super.onOptionsItemSelected(item);
	}
	
	public void salvar() {
		usuario = new Usuario();
		dao = new UsuarioDAO(this);
		
		et_nome = (EditText) findViewById(R.id.et_nome);
		et_senha = (EditText) findViewById(R.id.et_senha);
		et_senha2 = (EditText) findViewById(R.id.et_senha2);
		botao_cadastrar = (Button) findViewById(R.id.botao_cadastrar);
		
		botao_cadastrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				usuario.setNome(et_nome.getText().toString());
				usuario.setSenha(et_senha.getText().toString());
				
				String senha = et_senha.getText().toString();
				
				if (et_senha2.getText().toString().equals(senha)) {
					retorno = dao.salvar(usuario);
					dao.close();
					startActivity(new Intent(CadastroUsuarioActivity.this, UsuarioActivity.class));
				} else {
					alerta("Senha não foi repetida corretamente");
				}
	
			}
		});
	}
	
	private void alerta(String s) {
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}
}
