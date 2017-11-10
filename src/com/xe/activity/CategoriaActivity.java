package com.xe.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class CategoriaActivity extends Activity {

	ListView lv_categorias;
	
	int[] IMAGENS = {R.drawable.computacao, R.drawable.enfermagem, R.drawable.farmacia, R.drawable.medicina};
	String[] NOMES = {"Computação", "Enfermagem", "Farmácia", "Medicina"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoria);
		
		lv_categorias = (ListView) findViewById(R.id.lv_categorias);
		
		ClienteAdapter adapter = new ClienteAdapter();
		
		lv_categorias.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.categoria, menu);
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
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}
	
	private void popularLista() {
		String[] categorias = {"Farmácia", "Medicina", "Computação", "Enfermagem"};
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.activity_lista_categorias, categorias);
		
		lv_categorias.setAdapter(adaptador);
	}
	
	class ClienteAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return IMAGENS.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = getLayoutInflater().inflate(R.layout.cliente_layout, null);
			
			ImageView imageView = (ImageView) findViewById(R.id.imageComputacao);
			TextView textView = (TextView) findViewById(R.id.textComputacao);
			
			imageView.setImageResource(IMAGENS[position]);
			textView.setText(NOMES[position]);
			
			return convertView;
		}
		
	}
}
