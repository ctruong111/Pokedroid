package com.example.pokedroid;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class typeFragment extends Fragment {
	
	public typeFragment() {		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.type_fragment_layout, container, false); 
		return view;
	}
}
