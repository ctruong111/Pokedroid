package com.example.pokedroid;

import java.io.IOException;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

public class moveDisplay extends FragmentActivity {
	private DatabaseHelper dbHelper;
	private String moveName;
	private ActionBar actionBar;
	public moveDisplay() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.move_display_layout);
		
		Intent i = getIntent();
		
		moveName = i.getStringExtra("name");
		actionBar = getActionBar();

		//change the title to the pokemon's name
		actionBar.setTitle(moveName);
		actionBar.setDisplayShowHomeEnabled(false);

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
