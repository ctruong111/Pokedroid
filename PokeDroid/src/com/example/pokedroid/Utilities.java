package com.example.pokedroid;

import android.graphics.Color;

/**
 * Created by tonyc on 11/20/2016.
 */

public class Utilities {
    public static int getTypeColor(String type) {
        switch (type) {
            case "Grass":
                return Color.rgb(120, 200, 80);
            case "Fire":
                return Color.rgb(240, 128, 48);
            case "Water":
                return Color.rgb(104, 144, 240);
            case "Psychic":
                return Color.rgb(248, 88, 136);
            case "Bug":
                return Color.rgb(168, 184, 32);
            case "Poison":
                return Color.rgb(160, 64, 160);
            case "Flying":
                return Color.rgb(168, 144, 240);
            case "Normal":
                return Color.rgb(168, 168, 120);
            case "Electric":
                return Color.rgb(248, 208, 48);
            case "Ground":
                return Color.rgb(224, 192, 104);
            case "Rock":
                return Color.rgb(184, 160, 56);
            case "Ice":
                return Color.rgb(152, 216, 216);
            case "Dragon":
                return Color.rgb(112, 56, 248);
            case "Fairy":
                return Color.rgb(238, 153, 172);
            case "Fighting":
                return Color.rgb(192, 48, 40);
            case "Steel":
                return Color.rgb(184, 184, 208);
            case "Dark":
                return Color.rgb(112, 88, 72);
            case "Ghost":
                return Color.rgb(112, 88, 152);
        }
        return 0;
    }

    public static int getStatColor(String stat) {
        switch (stat) {
            case "hp":
                return Color.rgb(255, 0, 0);
            case "attack":
                return Color.rgb(240, 128, 48);
            case "defence":
                return Color.rgb(248, 208, 48);
            case "special_attack":
                return Color.rgb(104, 144, 240);
            case "special_defence":
                return Color.rgb(120, 200, 80);
            case "speed":
                return Color.rgb(248, 88, 136);

        }
        return 0;
    }

    public static String formatString(String str) {
        String formattedString = str.replace('-', ' ');
        String[] strings = formattedString.split(" ");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].substring(0, 1).toUpperCase() + strings[i].substring(1);
        }

        formattedString = "";
        for (String string : strings) {
            formattedString = formattedString.concat(string + " ");
        }

        return formattedString.trim();
    }
}
