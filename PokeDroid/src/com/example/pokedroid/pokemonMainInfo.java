package com.example.pokedroid;

import java.io.IOException;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class pokemonMainInfo extends FragmentActivity implements ActionBar.TabListener {
    private DatabaseHelper dbHelper;

    private List<String> evolutionChain;
    public static String[] pokemonEvolutions;
    private List<String> locations;
    public static String[] pokemonLocations;
    private List<String> movesList;
    public static String[] pokemonMoves;
    public static Pokemon pokemon;
    public static List<Abilities> ABILITY;
    public static Bitmap bitmap;
    private byte[] byteImage;
    private int byteLength;

    ViewPager viewPager;
	FragmentPageAdapter adapter;
    ActionBar actionBar;
    String pokemonName;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pokemon_main_info_layout);
		
		pokemonName = getIntent().getStringExtra("name");

        dbHelper = new DatabaseHelper(getApplicationContext());

        actionBar = getActionBar();
		actionBar.setTitle("Back");
		actionBar.setDisplayShowHomeEnabled(false);

        ImageTask task = new ImageTask();
        Thread thread1 = new Thread(new movesThread());
        Thread thread2 = new Thread(new evolutionsThread());
        Thread thread3 = new Thread(new locationsThread());
        Thread thread4 = new Thread(new pokemonThread());
        Thread thread5 = new Thread(new abilityThread());

        task.execute();
        thread1.run();
        thread2.run();
        thread3.run();
        thread4.run();
        thread5.run();

		viewPager = (ViewPager) findViewById(R.id.mainInfo);
		adapter = new FragmentPageAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		
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

    private class locationsThread implements Runnable {
        @Override
        public void run() {
            Log.e("ThreadResult", "Locations Thread Has Started");
            Long timeStart = System.currentTimeMillis();

            locations = dbHelper.getPokemonLocations(pokemonName);

            if (locations == null) {
                pokemonLocations = new String[1];
                pokemonLocations[0] = "Cannot be found in the wild!";
            } else {
                pokemonLocations = new String[locations.size()];

                for(int i = 0; i < locations.size(); i++) {
                    pokemonLocations[i] = locations.get(i);
                }
            }

            Long timeElapsed = System.currentTimeMillis() - timeStart;
            Log.e("ThreadResult", "Locations Thread Has Finished Took " + timeElapsed + "ms");
        }
    }

    private class movesThread implements Runnable {
        @Override
        public void run() {
            Log.e("ThreadResult", "Moves Thread Has Started");
            Long timeStart = System.currentTimeMillis();

            movesList = dbHelper.getPokemonMove(pokemonName);

            pokemonMoves = new String[movesList.size()];
            for (int i = 0; i < movesList.size(); i++) {
                String temp = movesList.get(i);
                temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
                pokemonMoves[i] = temp;
            }

            Long timeElapsed = System.currentTimeMillis() - timeStart;
            Log.e("ThreadResult", "Moves Thread Has Finished Took " + timeElapsed + "ms");
        }
    }

    private class evolutionsThread implements Runnable {
        @Override
        public void run() {
            Log.e("ThreadResult", "Evolutions Thread Has Started");

            Long timeStart = System.currentTimeMillis();

            evolutionChain = dbHelper.getEvolutionChain(pokemonName);

            if (evolutionChain != null) {
                pokemonEvolutions = new String[evolutionChain.size()];
                for (int i = 0; i < evolutionChain.size(); i++) {
                    String temp = evolutionChain.get(i);
                    temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
                    pokemonEvolutions[i] = temp;
                }
            } else {
                pokemonEvolutions = new String[1];
                pokemonEvolutions[0] = "No Evolutions!";
            }

            Long timeElapsed = System.currentTimeMillis() - timeStart;
            Log.e("ThreadResult", "Evolutions Thread Has Finished Took " + timeElapsed + "ms");
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
            ABILITY = dbHelper.getAbilities(pokemonName);
            Long timeElapsed = System.currentTimeMillis() - timeStart;

            Log.e("ThreadResult", "Ability Thread Has Finished Took " + timeElapsed + "ms");

        }
    }

    private class ImageTask extends AsyncTask<byte[], Void, byte[]> {
        @Override
        protected byte[] doInBackground(byte[]... params) {
            Log.e("ThreadResult", "ImageTask Has Begun");
            Long timeStart = System.currentTimeMillis();

            bitmap = null;
            byteImage = dbHelper.getImage(pokemonName);
            byteLength = byteImage.length;

            Long timeElapsed = System.currentTimeMillis() - timeStart;
            Log.e("ThreadResult", "ImageTask Has Finished Took " + timeElapsed + "ms");
            return byteImage;
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            super.onPostExecute(bytes);

            Long timeStart = System.currentTimeMillis();
            bitmap = BitmapFactory.decodeByteArray(byteImage, 0, byteLength);
            Long timeElapsed = System.currentTimeMillis() - timeStart;

            Log.e("Debug", "Setting Bitmap Took " + timeElapsed + "ms");
        }
    }

}
