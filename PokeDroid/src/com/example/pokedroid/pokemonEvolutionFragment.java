package com.example.pokedroid;

import java.util.List;

import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class pokemonEvolutionFragment extends ListFragment {
	private View view;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		view = inflater.inflate(R.layout.pokemon_evolution_fragment, container, false);
		
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, pokemonMainInfo.pokemonEvolutions);
		setListAdapter(adapter);

		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent(getActivity(), pokemonMainInfo.class);
		i.putExtra("name", pokemonMainInfo.pokemonEvolutions[position]); //Passing in the pokemon's name
		startActivity(i);
	}
}
