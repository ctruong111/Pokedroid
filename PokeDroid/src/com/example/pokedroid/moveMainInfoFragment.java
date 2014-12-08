package com.example.pokedroid;

import java.io.IOException;
import java.util.Locale;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
	public static TextView PP;
	public static TextView TYPE;
	public static TextView POWER;
	public static TextView ACCURACY;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.move_main_info_fragment_layout, container, false);
		name = getActivity().getActionBar().getTitle().toString();
				
		PP = (TextView) view.findViewById(R.id.PP);
		TYPE = (TextView) view.findViewById(R.id.type);
		POWER = (TextView) view.findViewById(R.id.power);
		ACCURACY = (TextView) view.findViewById(R.id.accuracy);
		
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

		TYPE.setText("Attack Type: "+ TYPEdisplay);
        PP.setText("PP:          "+ String.valueOf(PPdisplay));
        ACCURACY.setText("ACCURACY:    " + String.valueOf(ACCURACYdisplay));
        POWER.setText("POWER:       " + String.valueOf(POWERdisplay));
		
        
		return view;
	}
}
