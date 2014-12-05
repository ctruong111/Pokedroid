package com.example.pokedroid;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class typeFragment extends Fragment {
	public static String[] names;
	private DatabaseHelper dbHelper;
	private Fragment fragment;
	private FragmentManager manager;
	private String type;

	Button search;
	AutoCompleteTextView query;
	
	public typeFragment() {		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.pokemon_fragment_layout, container, false); 
		
        dbHelper = new DatabaseHelper(this.getActivity());
		List<String> typeNames = dbHelper.getAllTypeNames();
		names = new String[typeNames.size()];
		
		for(int i = 0; i < typeNames.size(); i++) {
			names[i] = typeNames.get(i);
			
			String output = names[i].substring(0, 1).toUpperCase() + names[i].substring(1);
			names[i] = output;
		}

		search = (Button)view.findViewById(R.id.search);
		query = (AutoCompleteTextView)view.findViewById(R.id.query);
		
		ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,names);
		query.setAdapter(adapter);
		
		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				type = query.getText().toString();
				
				InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
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
