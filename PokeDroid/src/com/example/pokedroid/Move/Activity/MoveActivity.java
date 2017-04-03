package com.example.pokedroid.Move.Activity;

import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.example.pokedroid.Helper.DatabaseHelper;
import com.example.pokedroid.Move.Helper.MovePageAdapter;
import com.example.pokedroid.Objects.Move;
import com.example.pokedroid.R;

public class MoveActivity extends FragmentActivity implements ActionBar.TabListener {
	private static ActionBar actionBar;
	private static ViewPager viewPager;
	private static MovePageAdapter adapter;
    private DatabaseHelper dbHelper;
    public static Move move;
    private List<String> pokemon;
    public static String pokemonNames[];
    private String name;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.move_main_info_layout);
		
		Intent i = getIntent();
		name = i.getStringExtra("name");

        dbHelper = new DatabaseHelper(getApplicationContext());

        actionBar = getActionBar();
		actionBar.setTitle(name);
		actionBar.setDisplayShowHomeEnabled(false);

        Thread thread1 = new Thread(new mainInfoThread());
        Thread thread2 = new Thread(new pokemonThread());

        thread1.run();
        thread2.run();

        viewPager = (ViewPager) findViewById(R.id.moveMainInfo);
		adapter = new MovePageAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().setText("Main Info").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Pokemon").setTabListener(this));
		
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

    private class mainInfoThread implements Runnable {
        @Override
        public void run() {
            move = dbHelper.getMove(name);
        }
    }

    private class pokemonThread implements Runnable {
        @Override
        public void run() {
            pokemon = dbHelper.getMovePokemon(name);
            pokemonNames = new String[pokemon.size()];

            for (int i = 0; i < pokemon.size(); i++) {
                pokemonNames[i] = pokemon.get(i);
            }
        }
    }
}
