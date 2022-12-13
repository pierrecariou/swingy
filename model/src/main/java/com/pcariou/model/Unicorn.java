package com.pcariou.model;

public class Unicorn extends Monster
{
	public Unicorn()
	{
		super("Unicorn", 50, 50);
	}

	public void attack(Hero hero)
	{
		hero.takeDamage(attackPower);
	}
}
