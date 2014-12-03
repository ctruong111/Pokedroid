package com.example.pokedroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PokemonLookupActivity extends Activity {

	public static String pokemon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pokemon_lookup);
		
		final EditText query = (EditText) findViewById(R.id.query);
		
		Button search = (Button)findViewById(R.id.search);
		Button back = (Button)findViewById(R.id.backButton);
		
		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0){
				pokemon = query.getText().toString();
				
				//Enter secondary screen
				Intent myIntent = new Intent(arg0.getContext(), PokemonDisplay.class);
				startActivityForResult(myIntent,0);
			}
		});
		
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//Go back home
				Intent myIntent = new Intent();
				setResult(RESULT_OK, myIntent);
				finish();
			}
			
		});
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
}
