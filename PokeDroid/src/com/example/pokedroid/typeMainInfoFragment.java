package com.example.pokedroid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class typeMainInfoFragment extends ListFragment{
	public static String[] names;
	private DatabaseHelper dbHelper;
	private Fragment fragment;
	private FragmentManager manager;

	public typeMainInfoFragment() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (this.getActivity() != null) {
        dbHelper = new DatabaseHelper(this.getActivity());
		}
		Log.e("tle99", "!!!!!!!!!!!!!! got in !!!!!!!!!!!!");
		Intent in = ((typeMainInfo) getActivity()).getIntent();
		String type = in.getStringExtra("name");
		Log.e("tle99", "!!!!!!!!!!!!!!" + type + "!!!!!!!!!!!!");
        List<String> pokemonNames = dbHelper.getPokemonFromType(type);
		names = new String[pokemonNames.size()];
		
		for(int i = 0; i < pokemonNames.size(); i++) {
			names[i] = pokemonNames.get(i);
			Log.e("tle99", "!!!!!!!!!!!!!!" + names[i] + "!!!!!!!!!!!!");
		}
        
        if (pokemonNames != null) {
        	ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, names);
    		Log.e("tle99", "!!!!!!!!!!!!!! got in !!!!!!!!!!!!");

			setListAdapter(adapter);
			Log.e("tle99", "!!!!!!!!!!!!!! got in !!!!!!!!!!!!");

        }

		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		//Remove the id from string
		String temp = names[position].substring(5).trim();
		//Change the activity
		Intent i = new Intent(getActivity(), pokemonMainInfo.class);
		i.putExtra("name", temp); //Passing in the pokemon's name
		startActivity(i);
	}
	
}
