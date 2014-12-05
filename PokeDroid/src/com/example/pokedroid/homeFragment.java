package com.example.pokedroid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class homeFragment extends ListFragment{
	public static String[] names;
	private DatabaseHelper dbHelper;
	private Fragment fragment;
	private FragmentManager manager;

	public homeFragment() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getActivity().setTitle(MainActivity.titles[0]);

		if (this.getActivity() != null) {
        dbHelper = new DatabaseHelper(this.getActivity());
		}
		
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        List<String> pokemonNames = dbHelper.getAllPokemonNames();
		names = new String[pokemonNames.size()];
		
		for(int i = 0; i < pokemonNames.size(); i++) {
			names[i] = pokemonNames.get(i);
			
			String output = names[i].substring(0, 1).toUpperCase() + names[i].substring(1);
			names[i] = output;
		}
        
        if (pokemonNames != null) {
        	ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, names);
			setListAdapter(adapter);
        }
        
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		fragment = new pokemonDisplayFragment(names[position]);
		manager = getFragmentManager();
		manager.beginTransaction().replace(R.id.mainContent, fragment).addToBackStack(null).commit();
	}
	
}