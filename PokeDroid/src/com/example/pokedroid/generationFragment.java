package com.example.pokedroid;

import java.text.NumberFormat;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class generationFragment extends Fragment {
	private Fragment fragment;
	private FragmentManager manager;
	private String generation;

	Button search;
	EditText query;
	
	public generationFragment() {		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.generation_fragment_layout, container, false);

		search = (Button)view.findViewById(R.id.search);
		query = (EditText)view.findViewById(R.id.query);
		
		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				generation = query.getText().toString();

				InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
				//
				// IMPLEMENT GENERATION DISPLAY PAGE
				//
				fragment = new pokemonDisplayFragment(generation);
				manager = getFragmentManager();
				manager.beginTransaction().replace(R.id.mainContent, fragment).addToBackStack(null).commit();
			}
		});
		
		return view;
	}
}
