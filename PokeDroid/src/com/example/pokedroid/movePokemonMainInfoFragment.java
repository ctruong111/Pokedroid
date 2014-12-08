package com.example.pokedroid;

import java.util.List;

import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class movePokemonMainInfoFragment extends ListFragment {
	private View view;
	public static String name;
	private DatabaseHelper dbHelper;
	private List<String> moves;
	private String[] stringMoves;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		name = getActivity().getActionBar().getTitle().toString();

		view = inflater.inflate(R.layout.move_pokemon_main_info_fragment, container, false);
		
        dbHelper = new DatabaseHelper(this.getActivity());
        
        moves = dbHelper.getPokemonMoves(name);
		stringMoves = new String[moves.size()];
        
		for(int i = 0; i < moves.size(); i++) {
			stringMoves[i] = moves.get(i);	
		}
		
        if (moves != null) {
        	ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, stringMoves);
			setListAdapter(adapter);
        }

		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent(getActivity(), moveMainInfo.class);
		i.putExtra("name", stringMoves[position]); //Passing in the move's name
		startActivity(i);
	}
}
