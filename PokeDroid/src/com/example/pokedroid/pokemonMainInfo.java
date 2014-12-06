package com.example.pokedroid;

import java.io.IOException;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

public class pokemonMainInfo extends FragmentActivity implements ActionBar.TabListener {
	private DatabaseHelper dbHelper;
	private Pokemon pokemon;
	public static String pokemonName;
	Bundle bundle;
	ViewPager viewPager;
	FragmentPageAdapter adapter;
    ActionBar actionBar;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pokemon_main_info_layout);
		
		Intent i = getIntent();
		bundle = new Bundle();
		
		pokemonName = i.getStringExtra("name");
		actionBar = getActionBar();

		//change the title to the pokemon's name
		actionBar.setTitle(pokemonName);
		actionBar.setDisplayShowHomeEnabled(false);
		
		viewPager = (ViewPager) findViewById(R.id.mainInfo);
		adapter = new FragmentPageAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    actionBar.addTab(actionBar.newTab().setText("Main Info").setTabListener(this));
	    actionBar.addTab(actionBar.newTab().setText("Location").setTabListener(this));
	    actionBar.addTab(actionBar.newTab().setText("Moves").setTabListener(this));
	    
	    viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(position);
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
