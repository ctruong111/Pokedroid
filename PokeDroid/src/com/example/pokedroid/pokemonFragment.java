package com.example.pokedroid;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class pokemonFragment extends Fragment {
	public static String[] names;
	private DatabaseHelper dbHelper;
	private Fragment fragment;
	private FragmentManager manager;
	private String name;
	
	Button search;
	AutoCompleteTextView query;
	
	public pokemonFragment() {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.pokemon_fragment_layout, container, false); 
		
        dbHelper = new DatabaseHelper(this.getActivity());
		List<String> pokemonNames = dbHelper.getAllPokemonNames();
		names = new String[pokemonNames.size()];
		
		for(int i = 0; i < pokemonNames.size(); i++) {
			names[i] = pokemonNames.get(i);
			
			String output = names[i].substring(0, 1).toUpperCase() + names[i].substring(1);
			names[i] = output;
		}

		search = (Button)view.findViewById(R.id.search);
		query = (AutoCompleteTextView)view.findViewById(R.id.query);
		
		ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,names);
		query.setAdapter(adapter);
		
		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				name = query.getText().toString();
				fragment = new pokemonDisplayFragment(name);
				manager = getFragmentManager();
				manager.beginTransaction().replace(R.id.mainContent, fragment).addToBackStack(null).commit();
			}
		});
		return view;
	}
}
