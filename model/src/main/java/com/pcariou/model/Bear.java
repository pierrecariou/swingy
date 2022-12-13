package com.pcariou.model;

public class Bear extends Monster
{
	public Bear()
	{
		super("Bear", 200, 80);
	}

	public void attack(Hero hero)
	{
		hero.takeDamage(attackPower);
	}
}
