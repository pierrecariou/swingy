package com.pcariou.model;

public class Warrior extends Hero
{
	public Warrior(String name)
	{
		super(name, "Warrior", 1, 0, 100, 200, 1000);
	}

	public Warrior(String name, int level, int experience, int attack, int defense, int hitPoints)
	{
		super(name, "Warrior", level, experience, attack, defense, hitPoints);
	}
}
