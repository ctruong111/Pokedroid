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

import org.w3c.dom.Text;

public class moveMainInfoFragment extends Fragment {
	String name;
	Move move;
	View view;
	private static TextView PP;
    private static TextView TYPE;
    private static TextView ATTACKTYPE;
    private static TextView POWER;
    private static TextView ACCURACY;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.move_main_info_fragment_layout, container, false);

		PP = (TextView) view.findViewById(R.id.PP);
		TYPE = (TextView) view.findViewById(R.id.type);
        ATTACKTYPE = (TextView) view.findViewById(R.id.attackType);
		POWER = (TextView) view.findViewById(R.id.power);
		ACCURACY = (TextView) view.findViewById(R.id.accuracy);

        move = moveMainInfo.move;

        String TYPEdisplay = move.getType();
        int ATTACKTYPEdisplay = move.getAttack_type();
		int ACCURACYdisplay = move.getAccuracy();
		int POWERdisplay = move.getPower();
		int PPdisplay = move.getPp();

        TYPEdisplay = TYPEdisplay.substring(0, 1).toUpperCase(Locale.US) + TYPEdisplay.substring(1);

        String temp = null;
        if (ATTACKTYPEdisplay == 1) {
            temp = "Status";
        } else if (ATTACKTYPEdisplay == 2) {
            temp = "Physical";
        } else if (ATTACKTYPEdisplay == 3) {
           temp = "Special Attack";
        }

		TYPE.setText("Type:                "+ TYPEdisplay);
        ATTACKTYPE.setText("Attack Type:  " + temp);
        PP.setText("PP:                   "+ String.valueOf(PPdisplay));
        ACCURACY.setText("Accuracy:       " + String.valueOf(ACCURACYdisplay));
        POWER.setText("Power:             " + String.valueOf(POWERdisplay));
		
        
		return view;
	}
}
