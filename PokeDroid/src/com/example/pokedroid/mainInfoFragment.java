package com.example.pokedroid;

import java.io.IOException;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class mainInfoFragment extends Fragment {
	private DatabaseHelper dbHelper;
	String name;
	Pokemon pokemon;
	View view;
	TextView type1;
	TextView type2;
	TextView height;
	TextView weight;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.main_info_fragment_layout, container, false);
		name = getActivity().getActionBar().getTitle().toString();
		
		ImageView image = (ImageView) view.findViewById(R.id.image);
		
		type1 = (TextView) view.findViewById(R.id.type1);
		type2 = (TextView) view.findViewById(R.id.type2);
		height = (TextView) view.findViewById(R.id.height);
		weight = (TextView) view.findViewById(R.id.weight);
		
	    dbHelper = new DatabaseHelper(getActivity());
		
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        pokemon = dbHelper.getPokemon(name);
        
        byte[] byteImage = dbHelper.getImage(name);
        image.setImageBitmap(BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length));

        String temp1 = pokemon.getType1();
		String temp2 = pokemon.getType2();

        temp1 = temp1.substring(0, 1).toUpperCase() + temp1.substring(1);
        temp2 = temp2.substring(0, 1).toUpperCase() + temp2.substring(1);

		type1.setText(temp1);        
        if (temp1 != temp2) {
        	type2.setText(temp2);
        }
        
        double num = pokemon.getHeight();
        height.setText(String.valueOf(num) + " m");
        
        num = pokemon.getWeight();
        weight.setText(String.valueOf(num) + " kg");
		
		return view;
	}
}
