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
	public static String name;
	private DatabaseHelper dbHelper;
	private List<String> evolutionChain;
	private String[] stringNames;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		name = getActivity().getActionBar().getTitle().toString();

		view = inflater.inflate(R.layout.pokemon_evolution_fragment, container, false);
		
        dbHelper = new DatabaseHelper(this.getActivity());
        
        evolutionChain = dbHelper.getEvolutionChain(name);
		stringNames = new String[evolutionChain.size()];
        
		for(int i = 0; i < evolutionChain.size(); i++) {
			stringNames[i] = evolutionChain.get(i);	
		}
		
        if (evolutionChain != null) {
        	ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, stringNames);
			setListAdapter(adapter);
        }

		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent(getActivity(), pokemonMainInfo.class);
		i.putExtra("name", stringNames[position]); //Passing in the pokemon's name
		startActivity(i);
	}
}
