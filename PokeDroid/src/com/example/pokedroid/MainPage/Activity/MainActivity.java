package com.example.pokedroid.MainPage.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pokedroid.Helper.DatabaseHelper;
import com.example.pokedroid.MainPage.Fragments.HomeFragment;
import com.example.pokedroid.R;

import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity{
	private List<String> pokemonNamesList;
	private List<String> moveNamesList;
	private List<String> typeNamesList;
	private List<String> pokemonNamesAndIdList;
	public static String[] pokemonNames;
	public static String[] moveNames;
	public static String[] typeNames;
	public static String[] pokemonNamesAndId;
	
	private DatabaseHelper dbHelper;
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	public static TextView title;
	private Fragment fragment;
	private FragmentManager manager;
	Bundle bundle = new Bundle();



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dbHelper = new DatabaseHelper(getApplicationContext());
		
		try {
			dbHelper.createDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}

        pokemonNamesAndIdsTask task = new pokemonNamesAndIdsTask();
        Thread thread1 = new Thread(new pokemonNamesThread());
        Thread thread2 = new Thread(new typeNamesThread());
        Thread thread3 = new Thread(new moveNamesThread());

        task.execute();
        thread1.start();
        thread2.start();
        thread3.start();

		title = (TextView) findViewById(R.id.pageTitle);

		//Hides the app launcher icon from action bar
		getActionBar().setDisplayShowHomeEnabled(false);

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
	}

    private class pokemonNamesAndIdsTask extends AsyncTask<String[], Void, String[]> {
        @Override
        protected String[] doInBackground(String[]... strings) {
            pokemonNamesAndIdList = dbHelper.getAllPokemonNamesAndId();
            pokemonNamesAndId = new String[pokemonNamesAndIdList.size()];

            for (int i = 0; i < pokemonNamesAndIdList.size(); i++) {
                pokemonNamesAndId[i] = pokemonNamesAndIdList.get(i);
            }
            return pokemonNamesAndId;
        }
        @Override
        protected void onPostExecute(String[] pokemonNamesAndIdsArray) {
            //Starts the app on the home page
            manager = getFragmentManager();
            bundle.putStringArray("names", pokemonNamesAndId);
            fragment = new HomeFragment();
            fragment.setArguments(bundle);
            manager.beginTransaction().replace(R.id.mainContent, fragment).commit();
        }
    }

    private class moveNamesThread implements Runnable {
        @Override
        public void run() {
            moveNamesList = dbHelper.getAllMoveNames();
            moveNames = new String[moveNamesList.size()];

            for (int i = 0; i < moveNamesList.size(); i++) {
                moveNames[i] = moveNamesList.get(i);
            }
        }
    }

    private class pokemonNamesThread implements Runnable {
        @Override
        public void run() {
            pokemonNamesList = dbHelper.getAllPokemonNames();
            pokemonNames = new String[pokemonNamesList.size()];

            for (int i = 0; i < pokemonNamesList.size(); i++) {
                pokemonNames[i] = pokemonNamesList.get(i);
            }
        }
    }

    private class typeNamesThread implements Runnable {
        @Override
        public void run() {
            typeNamesList = dbHelper.getAllTypeNames();
            typeNames = new String[typeNamesList.size()];

            for (int i = 0; i < typeNamesList.size(); i++) {
                typeNames[i] = typeNamesList.get(i);
            }
        }
    }
}
