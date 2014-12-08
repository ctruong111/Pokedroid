package com.example.pokedroid;

import java.text.NumberFormat;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class generationFragment extends Fragment {
	DatabaseHelper dbHelper;
	private Fragment fragment;
	private FragmentManager manager;
	private String generation;
	String selectedMove;
	String selectedType;
	String selectedLoc;
	List<String> moves;
	List<String> locations;
	List<String> types;
	Button search;
	EditText query;
	
	public generationFragment() {		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.generation_fragment_layout, container, false);
		
		if (this.getActivity() != null) {
	        dbHelper = new DatabaseHelper(this.getActivity());
		}
		Button search = (Button)view.findViewById(R.id.search);
		Spinner moveDropdown = (Spinner)view.findViewById(R.id.moveDropDown);
		Spinner locDropdown = (Spinner)view.findViewById(R.id.locDropDown);
		Spinner typeDropdown = (Spinner)view.findViewById(R.id.typeDropDown);
		
		moves = dbHelper.getAllMoveNames();
		String[] moveNames = new String[moves.size()];
		for (int i = 0; i < moves.size(); i++) {
			moveNames[i] = moves.get(i);
		}
		
		locations = dbHelper.getLocationNames();
		String[] locNames = new String[locations.size()];
		for (int i = 0; i < locations.size(); i++) {
			locNames[i] = locations.get(i);
		}
		
		types = dbHelper.getAllTypeNames();
		String[] typeNames = new String[types.size()];
		for (int i = 0; i < types.size(); i++) {
			typeNames[i] = types.get(i);
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, moveNames);
		moveDropdown.setAdapter(adapter);
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, locNames);
		locDropdown.setAdapter(adapter);
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, typeNames);
		typeDropdown.setAdapter(adapter);
		
		moveDropdown.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				selectedMove = parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		locDropdown.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				selectedLoc = parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		typeDropdown.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				selectedType = parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		search.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity().getApplicationContext(), generationDisplayFragment.class);
				i.putExtra("move", selectedMove);
				i.putExtra("loc", selectedLoc);
				i.putExtra("type", selectedType);
				startActivity(i);
			}
		});
		
		return view;
	}
}
