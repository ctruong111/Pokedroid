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

public class moveFragment extends Fragment {
	private Fragment fragment;
	private FragmentManager manager;
	private String move;

	Button search;
	AutoCompleteTextView query;
	
	public moveFragment() {		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.move_fragment_layout, container, false);
		
		search = (Button)view.findViewById(R.id.search);
		query = (AutoCompleteTextView)view.findViewById(R.id.query);
		
		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				move = query.getText().toString();
				//
				// IMPLEMENT MOVE DISPLAY PAGE
				//
				fragment = new pokemonDisplayFragment(move);
				manager = getFragmentManager();
				manager.beginTransaction().replace(R.id.mainContent, fragment).addToBackStack(null).commit();
			}
		});
		
		return view;
	}
}
