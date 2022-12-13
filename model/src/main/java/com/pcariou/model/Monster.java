package com.pcariou.model;

public abstract class Monster
{
	protected String type;
	protected int life;
	protected int attackPower;

	public Monster(String type, int life, int attackPower)
	{
		this.type = type;
		this.life = life;
		this.attackPower = attackPower;
	}

	public void takeDamage(int heroAttackPower)
	{
		life-=heroAttackPower;
	}

	public abstract void attack(Hero hero);
}
