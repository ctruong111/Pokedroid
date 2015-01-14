package com.example.pokedroid;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class pokemonMoveFragment extends ListFragment {
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.pokemon_move_fragment_layout, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, moveMainInfo.pokemonNames);
        setListAdapter(adapter);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	public void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent(getActivity(), pokemonMainInfo.class);
		i.putExtra("name", moveMainInfo.pokemonNames[position]); //Passing in the pokemon's name
		startActivity(i);
	}
}
