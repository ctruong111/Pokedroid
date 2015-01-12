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

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.pokemon_location_main_info_fragment, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, pokemonMainInfo.pokemonLocations);
		setListAdapter(adapter);

		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
