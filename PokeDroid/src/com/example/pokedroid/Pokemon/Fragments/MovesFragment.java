package com.example.pokedroid.Pokemon.Fragments;

import java.util.List;

import android.os.AsyncTask;
import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pokedroid.Helper.DatabaseHelper;
import com.example.pokedroid.Move.Activity.MoveActivity;

public class MovesFragment extends ListFragment {
	DatabaseHelper dbHelper;
	String name;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		dbHelper = new DatabaseHelper(getActivity().getApplicationContext());

		Bundle bundle = getArguments();
		name = bundle.getString("name");

		MovesThread movesThread = new MovesThread();
		movesThread.execute(name);

        return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent(getActivity(), MoveActivity.class);
		i.putExtra("name", (String) getListAdapter().getItem(position)); //Passing in the move's name
		startActivity(i);
	}

	private class MovesThread extends AsyncTask<String, Void, List<String>> {
		@Override
		protected List<String> doInBackground(String... params) {
			Log.e("ThreadResult", "Moves Thread Has Started");

			Long timeStart = System.currentTimeMillis();
			List<String> pokemonMoves = dbHelper.getPokemonMove(params[0]);
			Long timeElapsed = System.currentTimeMillis() - timeStart;

			Log.e("ThreadResult", "Moves Thread Has Finished Took " + timeElapsed + "ms");

			return pokemonMoves;
		}

		@Override
		protected void onPostExecute(List<String> pokemonMoves) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, pokemonMoves);
			setListAdapter(adapter);
		}
	}
}
