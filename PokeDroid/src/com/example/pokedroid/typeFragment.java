package com.example.pokedroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class typeFragment extends Fragment {
	private Fragment fragment;
	private FragmentManager manager;
	private String type;

	Button search;
	AutoCompleteTextView query;
	
	public typeFragment() {		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.type_fragment_layout, container, false);
		
		search = (Button)view.findViewById(R.id.search);
		query = (AutoCompleteTextView)view.findViewById(R.id.query);
		
		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				type = query.getText().toString();
				//
				// IMPLEMENT TYPE DISPLAY PAGE
				//
				fragment = new pokemonDisplayFragment(type);
				manager = getFragmentManager();
				manager.beginTransaction().replace(R.id.mainContent, fragment).addToBackStack(null).commit();
			}
		});
		
		return view;
	}
}
