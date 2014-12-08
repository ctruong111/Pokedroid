package com.example.pokedroid;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class pokemonMainInfoFragment extends Fragment {
	private DatabaseHelper dbHelper;
	String name;
	Pokemon pokemon;
	List<Abilities> ABILITY;
	View view;
	TextView type1, type2, height, weight, hp, attack, defence, special_attack, special_defence, 
				speed, ability;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
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
		ability = (TextView) view.findViewById(R.id.Abilities);
	    dbHelper = new DatabaseHelper(getActivity());
		
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        pokemon = dbHelper.getPokemon(name);
        ABILITY = dbHelper.getAbilities(name);
        byte[] byteImage = dbHelper.getImage(name);
        image.setImageBitmap(BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length));

        for(int i = 0; 0 < ABILITY.size(); i++){
        	Abilities temp = ABILITY.get(i);
        	ability.setText(Abilities.getName() + "\n");
        	ability.setText(Abilities.getDescription() + "\n");

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
