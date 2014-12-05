package com.example.pokedroid;

import java.io.IOException;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

public class pokemonDisplayFragment extends Fragment {
	private DatabaseHelper dbHelper;
	private Pokemon pokemon;
	public static String pokemonName;
	
	public pokemonDisplayFragment(String name) {
		pokemonName = name;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getActivity().setTitle(pokemonName);
		
		View view = inflater.inflate(R.layout.pokemon_display_fragment_layout, container, false);
		
		if (this.getActivity() != null) {
	        dbHelper = new DatabaseHelper(this.getActivity());
			}
			
	        try {
	            dbHelper.createDataBase();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        pokemon = dbHelper.getPokemon(pokemonName);
		return view;
	}
}
