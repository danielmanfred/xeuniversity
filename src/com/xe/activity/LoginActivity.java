package com.xe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

	private Button b_login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		b_login = (Button) findViewById(R.id.b_login);
		
		login();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
	
	private void alerta(String s) {
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}
	
	public void login() {
b_login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText et_usuario = (EditText) findViewById(R.id.et_usuario);
				EditText et_senha = (EditText) findViewById(R.id.et_senha);
				String usuarioLogin = et_usuario.getText().toString();
				String senhaLogin = et_senha.getText().toString();
				
				if (usuarioLogin.equals("manfred") && senhaLogin.equals("aaa")) {
					startActivity(new Intent(LoginActivity.this, UsuarioActivity.class));
				} else {
					alerta("Nome do usuário ou senha incorreta");
				}
			}
		});
	}
	
	public void cadastrarUsuario(View view) {
	    	startActivity(new Intent(this, CadastroUsuarioActivity.class));
	}
}
