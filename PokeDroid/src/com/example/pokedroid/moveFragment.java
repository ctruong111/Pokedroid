package com.example.pokedroid;

import java.util.List;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class moveFragment extends ListFragment {
	private View view;
	public static String name;
	private DatabaseHelper dbHelper;
	private List<String> moves;
	private String[] stringMoves;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		name = getActivity().getActionBar().getTitle().toString();

		view = inflater.inflate(R.layout.move_fragment_layout, container, false);
		
        dbHelper = new DatabaseHelper(this.getActivity());
        
        moves = dbHelper.getPokemonMoves(name);
		stringMoves = new String[moves.size()];
        
		for(int i = 0; i < moves.size(); i++) {
			stringMoves[i] = moves.get(i);	
		}
		
		for (int i = 0; i < stringMoves.length; i++){
			String temp = stringMoves[i];
			char[] charArray;
			temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
			charArray = temp.toCharArray();

			for(int j = 0; j < temp.length(); j++) {
				charArray = temp.toCharArray();
				if (charArray[j] == '-') {
					temp = temp.substring(0, j) + " " + temp.substring(j+1);
					break;
				}
			}
			
			stringMoves[i] = temp;
		}
		
        if (moves != null) {
        	ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, stringMoves);
			setListAdapter(adapter);
        }

		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
	}
}
