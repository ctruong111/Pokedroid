package com.example.pokedroid;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	private DrawerLayout drawerLayout;
	private ListView listView;
	private TextView title;
	public static String[] titles;
	private ActionBarDrawerToggle drawerListener;
	private Fragment fragment;
	private FragmentManager manager;
	private FragmentTransaction transaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Starts the app on the home page
		manager = getFragmentManager();
		transaction = manager.beginTransaction();
		fragment = new homeFragment();
		manager.beginTransaction().replace(R.id.mainContent, fragment).commit();

		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		listView = (ListView) findViewById(R.id.drawerList);
		title = (TextView) findViewById(R.id.pageTitle);
		titles = getResources().getStringArray(R.array.titles);
		setTitle(titles[0]);
		title.setText(titles[0]);

		//Sets the navigation drawer to display the titles string array
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles));
		listView.setOnItemClickListener(this);
		
		//Hides the app launcher icon from action bar
		getActionBar().setDisplayShowHomeEnabled(false);

		//What happens when the drawer is opened and closed
		drawerListener = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				bringDrawerToFront();
				super.onDrawerSlide(drawerView, slideOffset);
			}

			@Override
			public void onDrawerStateChanged(int newState) {
				bringDrawerToFront();
				super.onDrawerStateChanged(newState);
			}

			private void bringDrawerToFront() {
				listView.bringToFront();
				drawerLayout.requestLayout();
			}
		};
		
		//Allows for pressing action bar to open navigation drawer
		drawerLayout.setDrawerListener(drawerListener);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (drawerListener.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		drawerListener.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		drawerListener.syncState();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		selectTitle(position);

		if (position == 0) { //If home is selected
			fragment = new homeFragment();
		} else if (position == 1) { //If pokemon is selected
			fragment = new pokemonFragment();
		} else if (position == 2) { //If move is selected
			fragment = new moveSearchFragment();
		} else if (position == 3) { //If type is selected
			fragment = new typeFragment();
		} else if (position == 4) { //If generation is selected
			fragment = new generationFragment();
		} else if (position == 5) {	//If imageTest is selected
			fragment = new imageTestFragment();
		}
		
		title.setText(titles[position]);
		//Get the fragment manager and change the current fragment
		manager = getFragmentManager();
		manager.beginTransaction().replace(R.id.mainContent, fragment).addToBackStack(null).commit();
		
		//Closes the drawer after selection
		drawerLayout.closeDrawer(listView);
	}

	private void selectTitle(int position) {
		// TODO Auto-generated method stub
		//Changes the action bar title to reflect the page
		listView.setItemChecked(position, true);
		setTitle(titles[position]);
	}
}
