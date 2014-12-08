package com.example.pokedroid;

import java.text.NumberFormat;

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

		
		
		return view;
	}
}
