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

public class typeDisplayFragment extends Fragment {
	private DatabaseHelper dbHelper;
	
	public typeDisplayFragment() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.type_display_fragment_layout, container, false);
		
		if (this.getActivity() != null) {
	        dbHelper = new DatabaseHelper(this.getActivity());
			}
			
	        try {
	            dbHelper.createDataBase();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        	        
		return view;
	}
}
