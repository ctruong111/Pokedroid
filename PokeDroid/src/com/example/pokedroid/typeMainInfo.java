package com.example.pokedroid;

import java.io.IOException;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

public class typeMainInfo extends FragmentActivity {
	private DatabaseHelper dbHelper;
	private ActionBar actionBar;
	String typeName;
	Intent i;

	public typeMainInfo() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.type_main_info_layout);
		
		i = getIntent();
		actionBar = getActionBar();
		typeName = i.getStringExtra("name");
		
		actionBar.setTitle(typeName);
		actionBar.setDisplayShowHomeEnabled(false);

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpTo(this, i);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
