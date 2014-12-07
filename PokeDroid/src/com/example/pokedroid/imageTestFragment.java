package com.example.pokedroid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class imageTestFragment extends ListFragment {

	private DatabaseHelper dbHelper;
	private Fragment fragment;
	private FragmentManager manager;

	public imageTestFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.image_test, container, false);
		getActivity().setTitle(MainActivity.titles[5]);

		if (this.getActivity() != null) {
			dbHelper = new DatabaseHelper(this.getActivity());
		}
		try {
			dbHelper.createDataBase();    
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<byte[]> pokemonImages = dbHelper.getImageList();
		Bitmap[] img = new Bitmap[pokemonImages.size()];
		for(int i = 0; i < pokemonImages.size(); i++) {
			img[i] = BitmapFactory.decodeByteArray(pokemonImages.get(i), 0, pokemonImages.get(i).length);
			

		}

		if (pokemonImages != null) {
			ArrayAdapter<Bitmap> adapter = new ArrayAdapter<Bitmap>(inflater.getContext(), android.R.layout.simple_list_item_1, img);
			setListAdapter(adapter);
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
