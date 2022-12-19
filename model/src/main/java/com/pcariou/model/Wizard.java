package com.pcariou.model;

public class Wizard extends Hero
{
	public Wizard(String name)
	{
		super(name, "Wizard", 1, 0, 200, 100, 1000);
	}

	public Wizard(String name, int level, int experience, int attack, int defense, int hitPoints)
	{
		super(name, "Wizard", level, experience, attack, defense, hitPoints);
	}
}
