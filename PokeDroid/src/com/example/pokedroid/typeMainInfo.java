package com.example.pokedroid;

import java.io.IOException;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class typeMainInfo extends FragmentActivity {
	private DatabaseHelper dbHelper;
	private ActionBar actionBar;
	private Fragment fragment;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	String typeName;
	Intent i;
	View view;

	public typeMainInfo() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.type_main_info_layout);
		
		manager = getFragmentManager();
		transaction = manager.beginTransaction();
		
		i = getIntent();
		actionBar = getActionBar();
		typeName = i.getStringExtra("name");
		
		actionBar.setTitle(typeName);
		actionBar.setDisplayShowHomeEnabled(false);
		
		ListFragment l = new typeMainInfoFragment();
		transaction.add(R.id.typeMainInfo, l);
		transaction.commit();
		
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
