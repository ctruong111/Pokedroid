package com.example.pokedroid.MainPage.Fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pokedroid.MainPage.Activity.MainActivity;
import com.example.pokedroid.Pokemon.Activity.PokemonActivity;

public class HomeFragment extends ListFragment{
	public static String[] names;
    private TextView title;

	public HomeFragment() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        title = MainActivity.title;
		names = MainActivity.pokemonNamesAndId;

        if (names != null) {
        	ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, names);
			setListAdapter(adapter);
        }
        title.setText("Home");
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		//Remove the id from string
		String temp = names[position].substring(5).trim();
		//Change the activity
		Intent i = new Intent(getActivity(), PokemonActivity.class);
        i.putExtra("name", temp); //Passing in the pokemon's name
		startActivity(i);
	}

}
