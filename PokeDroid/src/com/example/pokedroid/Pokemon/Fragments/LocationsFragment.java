package com.example.pokedroid.Pokemon.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.pokedroid.Helper.DatabaseHelper;

import java.util.List;

public class LocationsFragment extends ListFragment {
    DatabaseHelper dbHelper;
    String name;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        dbHelper = new DatabaseHelper(getActivity().getApplicationContext());

        Bundle bundle = getArguments();
        name = bundle.getString("name");

        LocationsThread locationsThread = new LocationsThread();
        locationsThread.execute(name);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	private class LocationsThread extends AsyncTask<String, Void, List<String>> {
        @Override
        protected List<String> doInBackground(String... params) {
            Log.e("ThreadResult", "Locations Thread Has Started");

            Long timeStart = System.currentTimeMillis();
            List<String> pokemonLocations = dbHelper.getPokemonLocations(params[0]);
            Long timeElapsed = System.currentTimeMillis() - timeStart;

            Log.e("ThreadResult", "Locations Thread Has Finished Took " + timeElapsed + "ms");

            return pokemonLocations;
        }

        @Override
        protected void onPostExecute(List<String> pokemonLocations) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, pokemonLocations);
            setListAdapter(adapter);
        }
    }
}
