package com.example.pokedroid;

import java.io.IOException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class mainInfoFragment extends Fragment {
	private DatabaseHelper dbHelper;
	String name;
	Pokemon pokemon;
	View view;
	TextView type1;
	TextView type2;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.main_info_fragment_layout, container, false);
		name = getActivity().getActionBar().getTitle().toString();
		
		type1 = (TextView) view.findViewById(R.id.type1);
		type2 = (TextView) view.findViewById(R.id.type2);
		
	    dbHelper = new DatabaseHelper(getActivity());
		
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        pokemon = dbHelper.getPokemon(name);

        String temp = pokemon.getType1();
        String output = temp.substring(0, 1).toUpperCase() + temp.substring(1);
        
		type1.setText(output);

//		temp = pokemon.getType2();
//        output = temp.substring(0, 1).toUpperCase() + temp.substring(1);
//
//		type2.setText(output);
//		
		return view;
	}
}
