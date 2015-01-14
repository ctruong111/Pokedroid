package com.example.pokedroid;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class pokemonSearchFragment extends Fragment {
	public static String[] names;
	private Fragment fragment;
	private FragmentManager manager;
	private String name;
	private static boolean exist;
	Pokemon pokemon;
	
	Button search;
	AutoCompleteTextView query;
	
	public pokemonSearchFragment() {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.pokemon_search_fragment_layout, container, false); 

		names = getArguments().getStringArray("names");
		
		search = (Button)view.findViewById(R.id.search);
		query = (AutoCompleteTextView)view.findViewById(R.id.query);
		
		ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,names);
		query.setAdapter(adapter);
		
		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				name = query.getText().toString();

                for (int i = 0; i < names.length; i++) {
                    if (name.equals(names[i])) {
                        exist = true;
                        break;
                    }
                    exist = false;
                }

				if (exist == true) {//Pokemon exists!
					pokemon = null;
					//Hides the keyboard
					InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
					imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
					//Change the activity
					Intent i = new Intent(getActivity(), pokemonMainInfo.class);
					i.putExtra("name", name); //Passing in the pokemon's name
					startActivity(i);
				} else if (exist == false){
					query.setError("Pokemon does not exist!");					
				}
			}
			
		});

		return view;
	}
}
