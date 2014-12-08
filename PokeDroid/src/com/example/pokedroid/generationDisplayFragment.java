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

public class generationDisplayFragment extends Fragment {
	private DatabaseHelper dbHelper;
	
	public generationDisplayFragment() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.generation_display_fragment_layout, container, false);
		
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
