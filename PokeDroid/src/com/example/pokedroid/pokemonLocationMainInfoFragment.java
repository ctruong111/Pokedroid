package com.example.pokedroid;

import java.util.List;

import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class pokemonLocationMainInfoFragment extends ListFragment {
	private View view;
	public static String name;
	private DatabaseHelper dbHelper;
	private List<String> locations;
	private String[] stringLocations;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		name = getActivity().getActionBar().getTitle().toString();

		view = inflater.inflate(R.layout.pokemon_location_main_info_fragment, container, false);
		
        dbHelper = new DatabaseHelper(this.getActivity());
        
        locations = dbHelper.getPokemonLocations(name);
        
        if (locations == null) {
        	stringLocations = new String[1];
        	stringLocations[0] = "Cannot be found in the wild!";
        } else {
			stringLocations = new String[locations.size()];
	        
			for(int i = 0; i < locations.size(); i++) {
				stringLocations[i] = locations.get(i);	
			}
        }
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, stringLocations);
		setListAdapter(adapter);

		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
