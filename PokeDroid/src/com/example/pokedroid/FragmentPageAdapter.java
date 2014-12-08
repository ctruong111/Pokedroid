package com.example.pokedroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentPageAdapter extends FragmentPagerAdapter {

	public FragmentPageAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		switch (position) {
			case 0:
				return new pokemonMainInfoFragment();
			case 1:
				return new pokemonLocationMainInfoFragment();
			case 2:
				return new movePokemonMainInfoFragment();
			case 3:
				return new pokemonEvolutionFragment();
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
