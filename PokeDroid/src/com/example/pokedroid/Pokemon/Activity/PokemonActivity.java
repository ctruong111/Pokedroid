package com.example.pokedroid.Pokemon.Activity;

import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;

import com.example.pokedroid.Objects.Abilities;
import com.example.pokedroid.Helper.DatabaseHelper;
import com.example.pokedroid.Objects.Pokemon;
import com.example.pokedroid.Pokemon.Helper.PokemonPageAdapter;
import com.example.pokedroid.R;

public class PokemonActivity extends FragmentActivity implements ActionBar.TabListener {
    private DatabaseHelper dbHelper;

    public static Pokemon pokemon;
    public static List<Abilities> abilities;

    String pokemonName;

    ViewPager viewPager;
    ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pokemon_main_info_layout);
		
		pokemonName = getIntent().getStringExtra("name");

        dbHelper = new DatabaseHelper(getApplicationContext());

        actionBar = getActionBar();
		actionBar.setTitle("Back");
		actionBar.setDisplayShowHomeEnabled(false);

        Thread thread1 = new Thread(new pokemonThread());
        Thread thread2 = new Thread(new abilityThread());

        thread1.run();
        thread2.run();

		viewPager = (ViewPager) findViewById(R.id.mainInfo);
		viewPager.setAdapter(new PokemonPageAdapter(getSupportFragmentManager(), pokemonName));
		
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    actionBar.addTab(actionBar.newTab().setText("Main Info").setTabListener(this));
	    actionBar.addTab(actionBar.newTab().setText("Location").setTabListener(this));
	    actionBar.addTab(actionBar.newTab().setText("Moves").setTabListener(this));
	    actionBar.addTab(actionBar.newTab().setText("Evolutions").setTabListener(this));
	    
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

    private class pokemonThread implements Runnable {
        @Override
        public void run() {
            Log.e("ThreadResult", "Pokemon Thread Has Started");

            Long timeStart = System.currentTimeMillis();
            pokemon = dbHelper.getPokemon(pokemonName);
            Long timeElapsed = System.currentTimeMillis() - timeStart;

            Log.e("ThreadResult", "Pokemon Thread Has Finished Took " + timeElapsed + "ms");
        }
    }

    private class abilityThread implements Runnable {
        @Override
        public void run() {
            Log.e("ThreadResult", "Ability Thread Has Started");

            Long timeStart = System.currentTimeMillis();
            abilities = dbHelper.getAbilities(pokemonName);
            Long timeElapsed = System.currentTimeMillis() - timeStart;

            Log.e("ThreadResult", "Ability Thread Has Finished Took " + timeElapsed + "ms");

        }
    }
}
