package com.example.pokedroid;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class pokemonMainInfoFragment extends Fragment {
	TextView type1TextView, type2TextView, name;
    RelativeLayout pokemonRL, mainInfo, hp, attack, defence, special_attack, special_defence, speed;

    Pokemon pokemon = pokemonMainInfo.pokemon;
    List<Abilities> abilities = pokemonMainInfo.ABILITY;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ScrollView mainView = (ScrollView) inflater.inflate(R.layout.pokemon_main_info_fragment_layout, container, false);
        pokemonRL = (RelativeLayout) mainView.findViewById(R.id.pokemon);
        mainInfo = (RelativeLayout)pokemonRL.findViewById(R.id.mainInfo);

		type1TextView = (TextView) mainInfo.findViewById(R.id.type1Text);
		type2TextView = (TextView) mainInfo.findViewById(R.id.type2Text);
        name = (TextView) mainInfo.findViewById(R.id.name);

		hp = (RelativeLayout) mainInfo.findViewById(R.id.hpBar);
		attack = (RelativeLayout) mainInfo.findViewById(R.id.attackBar);
		defence = (RelativeLayout) mainInfo.findViewById(R.id.defenceBar);
		special_attack = (RelativeLayout) mainInfo.findViewById(R.id.special_attackBar);
		special_defence = (RelativeLayout) mainInfo.findViewById(R.id.special_defenceBar);
		speed = (RelativeLayout) mainInfo.findViewById(R.id.speedBar);

        RelativeLayout abilityLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayoutParams.setMargins(0, getDp(2), 0, getDp(20));
        relativeLayoutParams.addRule(RelativeLayout.BELOW, R.id.abilitiesHeader);

        int prevAbilityDescId = R.id.abilitiesHeader;
        for (Abilities ability : abilities) {
            TextView title = new TextView(getContext());
            title.setId(View.generateViewId());
            title.setText(ability.getName());
            title.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
            title.setTextColor(Color.BLACK);

            TextView desc = new TextView(getContext());
            desc.setId(View.generateViewId());
            desc.setText(ability.getDescription());
            desc.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
            desc.setTextColor(Color.BLACK);

            RelativeLayout.LayoutParams titleLayoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            titleLayoutParams.setMargins(0, getDp(2), 0, getDp(2));
            titleLayoutParams.addRule(RelativeLayout.BELOW, prevAbilityDescId);

            RelativeLayout.LayoutParams descLayoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            descLayoutParams.setMargins(0, getDp(2), 0, getDp(2));
            descLayoutParams.addRule(RelativeLayout.BELOW, title.getId());

            title.setLayoutParams(titleLayoutParams);
            desc.setLayoutParams(descLayoutParams);

            abilityLayout.addView(title);
            abilityLayout.addView(desc);

            prevAbilityDescId = desc.getId();
        }
        ((RelativeLayout)(mainInfo.findViewById(R.id.abilities))).addView(abilityLayout, relativeLayoutParams);

        String type1 = Utilities.formatString(pokemon.getType1());
		String type2 = Utilities.formatString(pokemon.getType2());

		type1TextView.setText(type1);
        type1TextView.setTextColor(Color.WHITE);
        setBackgroundDrawableColor(mainInfo.findViewById(R.id.type1), Utilities.getTypeColor(type1));

        if (!type1.equals(type2)) {
            type2TextView.setText(type2);
            type2TextView.setTextColor(Color.WHITE);
            setBackgroundDrawableColor(mainInfo.findViewById(R.id.type2), Utilities.getTypeColor(type2));
            mainInfo.findViewById(R.id.type2).setAlpha(1);
        } else {
            mainInfo.findViewById(R.id.type2).setAlpha(0);
        }

        name.setText(pokemon.getName());

        configureStatValues(hp, pokemon.getHp(), "hp");
        configureStatValues(attack, pokemon.getAttack(), "attack");
        configureStatValues(defence, pokemon.getDefence(), "defence");
        configureStatValues(special_attack, pokemon.getSpecial_attack(), "special_attack");
        configureStatValues(special_defence, pokemon.getSpecial_defence(), "special_defence");
        configureStatValues(speed, pokemon.getSpeed(), "speed");

        ImageView image = (ImageView) pokemonRL.findViewById(R.id.image);
        image.setContentDescription("Image of " + pokemon.getName());
        image.setImageBitmap(pokemonMainInfo.bitmap);

        return mainView;
	}

	private void configureStatValues(RelativeLayout relativeLayout, int statValue, String stat) {
        TextView barValue = (TextView) relativeLayout.getChildAt(0);
        barValue.setText(String.valueOf(statValue));

        relativeLayout.setBackgroundColor(Utilities.getStatColor(stat));
        relativeLayout.setMinimumWidth(getDp(calculateBarLength(statValue)));
    }

    private int calculateBarLength(int value) {
        double fillPercent = value/255.0;
        int maxFillAmt = 400;
        int barLength = (int) (fillPercent*maxFillAmt);

        if (barLength < 20) {
            return 20;
        } else {
            return barLength;
        }
    }

    private void setBackgroundDrawableColor(View view, int color) {
        ((GradientDrawable)view.getBackground()).setColor(color);
    }

    private Context getContext() {
        return getActivity().getApplicationContext();
    }

    private int getDp(int size) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, getResources().getDisplayMetrics());
    }
}
