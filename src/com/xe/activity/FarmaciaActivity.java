package com.xe.activity;

import com.xe.dao.ComputacaoDAO;
import com.xe.dao.FarmaciaDAO;
import com.xe.model.Material;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FarmaciaActivity extends ActionBarActivity {

	EditText et_nome;
	EditText et_descricao;
	Button botao_variavel;
	Button botao_cancelar;
	Material material;
	Material altMaterial;
	FarmaciaDAO dao;
	long retorno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_farmacia);
		salvarMaterial();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.farmacia, menu);
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
	
	public void salvarMaterial() {
		Intent intent = getIntent();
		altMaterial = (Material) intent.getSerializableExtra("material-enviado");
		material = new Material();
		dao = new FarmaciaDAO(this);
		
		et_nome = (EditText) findViewById(R.id.et_nome);
		et_descricao = (EditText) findViewById(R.id.et_descricao);
		botao_variavel = (Button) findViewById(R.id.botao_variavel);
		botao_cancelar = (Button) findViewById(R.id.botao_cancelar);
		
		if (altMaterial != null) {
			botao_variavel.setText("Salvar");
			et_nome.setText(altMaterial.getNome());
			et_descricao.setText(altMaterial.getDescricao());
			material.setId(altMaterial.getId());
		}
		else {
			botao_variavel.setText("Cadastrar");
		}
		
		botao_variavel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				material.setNome(et_nome.getText().toString());
				material.setDescricao(et_descricao.getText().toString());

				if (botao_variavel.getText().toString().equals("Cadastrar")) {
					retorno = dao.salvar(material);
					dao.close();
					if (retorno == -1) {
						alerta("Erro ao cadastrar");
					}
					else {
						alerta("Cadastro realizado com sucesso");
					}
				}
				else {
					retorno = dao.alterar(material);
					dao.close();
					if (retorno == -1) {
						alerta("Erro ao atualizar");
					}
					else {
						alerta("Atualização realizada com sucesso");
					}
				}
				
				finish();
			}
		});
	}
	
	private void alerta(String s) {
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}
	
	public void cancelar(View view) {
		finish();
	}
}
