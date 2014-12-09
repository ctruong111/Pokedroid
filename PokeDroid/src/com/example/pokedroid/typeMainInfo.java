package com.example.pokedroid;

import java.io.IOException;
import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class typeMainInfo extends FragmentActivity {
	private DatabaseHelper dbHelper;
	private ActionBar actionBar;
	List<String> pokemonNames;
	ListView view;
	String typeName;
	Intent i;

	public typeMainInfo() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.type_main_info_layout);
		dbHelper = new DatabaseHelper(this);

		i = getIntent();
		actionBar = getActionBar();
		typeName = i.getStringExtra("name");
		
		actionBar.setTitle(typeName);
		actionBar.setDisplayShowHomeEnabled(false);

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		view = (ListView)findViewById(R.id.typeMainInfo);
		
		pokemonNames = dbHelper.getPokemonType(typeName);
		if (pokemonNames == null) {
		Log.e("tle99", "!!!!!!!!!!!!POKEMON NAMES IS NULL!!!!!!!!!!!!!");
		}
		
		if (pokemonNames != null) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pokemonNames);
			view.setAdapter(adapter);
			view.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Intent i = new Intent(view.getContext(), pokemonMainInfo.class);
					i.putExtra("name", pokemonNames.get(position)); //Passing in the pokemon's name
					startActivity(i);
				}
			});
		}
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpTo(this, i);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
