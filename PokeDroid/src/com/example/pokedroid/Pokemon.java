package com.example.pokedroid;

public class Pokemon {
	private int id;
	private String name;
	private double height;
	private double weight;
	private int base_exp;
	private int defence;
	private int attack;
	private int hp;
	private int special_attack;
	private int special_defence;
	private int speed;
	private String type1;
	private String type2;
	private byte[] image;
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

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

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getBase_exp() {
		return base_exp;
	}

	public void setBase_exp(int base_exp) {
		this.base_exp = base_exp;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSpecial_attack() {
		return special_attack;
	}

	public void setSpecial_attack(int special_attack) {
		this.special_attack = special_attack;
	}

	public int getSpecial_defence() {
		return special_defence;
	}

	public void setSpecial_defence(int special_defence) {
		this.special_defence = special_defence;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setType1(String type) {
		this.type1 = type;
	}
	
	public String getType1() {
		return type1;
	}
	
	public void setType2(String type) {
		this.type2 = type;
	}
	
	public String getType2() {
		return type2;
	}
	
	public Pokemon(String name) {
		super();
		this.name = name;
	}
	
}
