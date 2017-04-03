package com.example.pokedroid.Objects;

public class Move {
	private int id;
	private String name;
	private int type_id;
	private String type;
	private int power;
	private int pp;
	private int accuracy;
	private int attack_type;
	private int effect_id;
	private int effect_chance;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getPp() {
		return pp;
	}
	public void setPp(int pp) {
		this.pp = pp;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public int getAttack_type() {
		return attack_type;
	}
	public void setAttack_type(int i) {
		this.attack_type = i;
	}
	public int getEffect_id() {
		return effect_id;
	}
	public void setEffect_id(int effect_id) {
		this.effect_id = effect_id;
	}
	public int getEffect_chance() {
		return effect_chance;
	}
	public void setEffect_chance(int effect_chance) {
		this.effect_chance = effect_chance;
	}
}
