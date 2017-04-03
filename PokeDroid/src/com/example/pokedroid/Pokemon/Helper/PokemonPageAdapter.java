package com.example.pokedroid.Pokemon.Helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pokedroid.Pokemon.Fragments.EvolutionsFragment;
import com.example.pokedroid.Pokemon.Fragments.LocationsFragment;
import com.example.pokedroid.Pokemon.Fragments.MainInfoFragment;
import com.example.pokedroid.Pokemon.Fragments.MovesFragment;

public class PokemonPageAdapter extends FragmentPagerAdapter {
	String name;

	public PokemonPageAdapter(FragmentManager fragmentManager, String name) {
		super(fragmentManager);
		this.name = name;

		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		Fragment fragment;
		Bundle bundle = new Bundle();
		bundle.putString("name", name);

		switch (position) {
			case 0:
				return new MainInfoFragment();
			case 1:
				fragment = new LocationsFragment();
				fragment.setArguments(bundle);
				return fragment;
			case 2:
				fragment = new MovesFragment();
				fragment.setArguments(bundle);
				return fragment;
			case 3:
				fragment = new EvolutionsFragment();
				fragment.setArguments(bundle);
				return fragment;
			default:
				break;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}

}
