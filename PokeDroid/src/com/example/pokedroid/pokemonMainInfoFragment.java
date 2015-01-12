package com.example.pokedroid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class pokemonMainInfoFragment extends Fragment {
	private DatabaseHelper dbHelper;
	String name;
	Pokemon pokemon;
	List<Abilities> ABILITY;
	View view;
	TextView type1, type2, height, weight, hp, attack, defence, special_attack, special_defence, 
				speed, abilityT1, abilityT2, abilityT3, abilityD1, abilityD2, abilityD3;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ABILITY = new ArrayList<Abilities>();
		view = inflater.inflate(R.layout.pokemon_main_info_fragment_layout, container, false);
		name = getActivity().getActionBar().getTitle().toString();
		
		ImageView image = (ImageView) view.findViewById(R.id.image);
		
		type1 = (TextView) view.findViewById(R.id.type1);
		type2 = (TextView) view.findViewById(R.id.type2);
		height = (TextView) view.findViewById(R.id.height);
		weight = (TextView) view.findViewById(R.id.weight);
		hp = (TextView) view.findViewById(R.id.hp);
		attack = (TextView) view.findViewById(R.id.attack);
		defence = (TextView) view.findViewById(R.id.defence);
		special_attack = (TextView) view.findViewById(R.id.special_attack);
		special_defence = (TextView) view.findViewById(R.id.special_defence);
		speed = (TextView) view.findViewById(R.id.speed);
		abilityT1 = (TextView) view.findViewById(R.id.AbilityTitle1);
        abilityT2 = (TextView) view.findViewById(R.id.AbilityTitle2);
        abilityT3 = (TextView) view.findViewById(R.id.AbilityTitle3);
        abilityD1 = (TextView) view.findViewById(R.id.AbilityDesc1);
        abilityD2 = (TextView) view.findViewById(R.id.AbilityDesc2);
        abilityD3 = (TextView) view.findViewById(R.id.AbilityDesc3);
        dbHelper = new DatabaseHelper(getActivity());

        pokemon = dbHelper.getPokemon(name);
        ABILITY = dbHelper.getAbilities(name);
        byte[] byteImage = dbHelper.getImage(name);
        image.setImageBitmap(BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length));
        
        Abilities temp = new Abilities();

        temp = ABILITY.get(0);
        abilityT1.setText(temp.getName());
        abilityD1.setText(temp.getDescription());
        temp = new Abilities();
        if (ABILITY.size() == 2) {
            temp = ABILITY.get(1);
            abilityT2.setText(temp.getName());
            abilityD2.setText(temp.getDescription());
        } else {
            abilityT2.setText("");
            abilityD2.setText("");
        }
        temp = new Abilities();
        if (ABILITY.size() == 3) {
            temp = ABILITY.get(2);
            abilityT3.setText(temp.getName());
            abilityD3.setText(temp.getDescription());
        } else {
            abilityT3.setText("");
            abilityD3.setText("");
        }

        String temp1 = pokemon.getType1();
		String temp2 = pokemon.getType2();

        temp1 = temp1.substring(0, 1).toUpperCase(Locale.US) + temp1.substring(1);
        temp2 = temp2.substring(0, 1).toUpperCase(Locale.US) + temp2.substring(1);

		type1.setText(temp1);
		
        if (temp1.equals(temp2)) {
        	type2.setText("");
        } else {
        	type2.setText(temp2);
        }
        
        double num = pokemon.getHeight();
        height.setText(String.valueOf(num/10) + " m");
        
        num = pokemon.getWeight();
        weight.setText(String.valueOf(num/10) + " kg");
		
        int intNum;
        intNum = pokemon.getHp();
        hp.setText("HP:               " + Integer.toString(intNum));
        
        intNum = pokemon.getAttack();
        attack.setText("Attack:        " + String.valueOf(intNum));
        
        intNum = pokemon.getDefence();
        defence.setText("Defence:     " + String.valueOf(intNum));
        
        intNum = pokemon.getSpecial_attack();
        special_attack.setText("Sp. Atk:       " + String.valueOf(intNum));
        
        intNum = pokemon.getSpecial_defence();
        special_defence.setText("Sp. Def:       " + String.valueOf(intNum));
        
        intNum = pokemon.getSpeed();
        speed.setText("Speed:         " + String.valueOf(intNum));
        
        
		return view;
	}
}
