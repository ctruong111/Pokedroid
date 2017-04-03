package com.example.pokedroid;

import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pokedroid.Helper.DatabaseHelper;
import com.example.pokedroid.Pokemon.Activity.PokemonActivity;

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

		i = getIntent();
		actionBar = getActionBar();
		typeName = i.getStringExtra("name");
		
		actionBar.setTitle(typeName);
		actionBar.setDisplayShowHomeEnabled(false);

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		view = (ListView)findViewById(R.id.typeMainInfo);

        while(true) {
            if (typeSearchFragment.done == true) {
                pokemonNames = typeSearchFragment.pokemonList;
                break;
            }
        }

        if (pokemonNames != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, pokemonNames);
            view.setAdapter(adapter);
            view.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // TODO Auto-generated method stub
                    Intent i = new Intent(view.getContext(), PokemonActivity.class);
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
