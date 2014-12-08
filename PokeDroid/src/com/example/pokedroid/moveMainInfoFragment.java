package com.example.pokedroid;

import java.io.IOException;
import java.util.Locale;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class moveMainInfoFragment extends Fragment {
	private DatabaseHelper dbHelper;
	String name;
	Move move;
	View view;
	TextView PP;
	TextView TYPE;
	TextView POWER;
	TextView ACCURACY;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.pokemon_main_info_fragment_layout, container, false);
		name = getActivity().getActionBar().getTitle().toString();
		
		ImageView image = (ImageView) view.findViewById(R.id.image);
		
		PP = (TextView) view.findViewById(R.id.PP);
		TYPE = (TextView) view.findViewById(R.id.TYPE);
		POWER = (TextView) view.findViewById(R.id.POWER);
		ACCURACY = (TextView) view.findViewById(R.id.ACCURACY);
		
	    dbHelper = new DatabaseHelper(getActivity());
		
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        move = dbHelper.getMove(name);
        
        String TYPEdisplay = move.getType();
		int ACCURACYdisplay = move.getAccuracy();
		int POWERdisplay = move.getPower();
		int PPdisplay = move.getPp();
        TYPEdisplay = TYPEdisplay.substring(0, 1).toUpperCase(Locale.US) + TYPEdisplay.substring(1);

		TYPE.setText(		"Attack Type: "+ TYPE);
        PP.setText(  		"PP:          "+ PPdisplay);
        ACCURACY.setText(	"ACCURACY:    " + ACCURACYdisplay);
        POWER.setText(		"POWER:       " + POWERdisplay);
		
        
		return view;
	}
}
