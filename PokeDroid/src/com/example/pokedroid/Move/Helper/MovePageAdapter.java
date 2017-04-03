package com.example.pokedroid.Move.Helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pokedroid.Move.Fragments.MainInfoFragment;
import com.example.pokedroid.Move.Fragments.PokemonFragment;

public class MovePageAdapter extends FragmentPagerAdapter {

	public MovePageAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		switch (position) {
			case 0:
				return new MainInfoFragment();
			case 1:
				return new PokemonFragment();
			default:
				break;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}
