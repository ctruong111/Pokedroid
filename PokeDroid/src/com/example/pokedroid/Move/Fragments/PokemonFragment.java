package com.example.pokedroid.Move.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pokedroid.Move.Activity.MoveActivity;
import com.example.pokedroid.Pokemon.Activity.PokemonActivity;
import com.example.pokedroid.R;

public class PokemonFragment extends ListFragment {
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.pokemon_move_fragment_layout, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, MoveActivity.pokemonNames);
        setListAdapter(adapter);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	public void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent(getActivity(), PokemonActivity.class);
		i.putExtra("name", MoveActivity.pokemonNames[position]); //Passing in the pokemon's name
		startActivity(i);
	}
}
