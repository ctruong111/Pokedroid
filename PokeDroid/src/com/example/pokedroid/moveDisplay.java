package com.example.pokedroid;

import java.io.IOException;

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
	
	public moveDisplay() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.move_display_layout);
	}
}
