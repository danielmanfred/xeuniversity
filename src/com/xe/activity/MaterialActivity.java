package com.xe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xe.dao.ComputacaoDAO;
import com.xe.model.Material;


public class MaterialActivity extends Activity {
	
	EditText et_nome;
	EditText et_descricao;
	Button botao_variavel;
	Button botao_cancelar;
	Material material;
	Material altMaterial;
	ComputacaoDAO daoCoputacao;
	long retorno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_material);
		salvarMaterial();
	}
	
	public void salvarMaterial() {
		Intent intent = getIntent();
		altMaterial = (Material) intent.getSerializableExtra("material-enviado");
		material = new Material();
		daoCoputacao = new ComputacaoDAO(this);
		
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
					retorno = daoCoputacao.salvar(material);
					daoCoputacao.close();
					if (retorno == -1) {
						alerta("Erro ao cadastrar");
					}
					else {
						alerta("Cadastro realizado com sucesso");
					}
				}
				else {
					retorno = daoCoputacao.alterar(material);
					daoCoputacao.close();
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
