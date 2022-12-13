package com.pcariou.model;

public class Dragon extends Monster
{
	public Dragon()
	{
		super("Dragon", 500, 100);
	}

	public void attack(Hero hero)
	{
		hero.takeDamage(attackPower);
	}
}
