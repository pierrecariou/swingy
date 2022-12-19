package com.pcariou.model;

public class Hunter extends Hero
{
	public Hunter(String name)
	{
		super(name, "Hunter", 1, 0, 150, 150, 1000);
	}

	public Hunter(String name, int level, int experience, int attack, int defense, int hitPoints)
	{
		super(name, "Hunter", level, experience, attack, defense, hitPoints);
	}
}
