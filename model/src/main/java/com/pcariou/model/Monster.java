package com.pcariou.model;

public class Monster
{
	protected String type;
	protected int life;
	protected int attackPower;
	protected String speak;
	protected int experience;

	public Monster(String type, int life, int attackPower, String speak, int experience)
	{
		this.type = type;
		this.life = life;
		this.attackPower = attackPower;
		this.speak = speak;
		this.experience = experience;
	}

	public void takeDamage(int heroAttackPower)
	{
		life-=heroAttackPower;
	}

	public void attack(Hero hero)
	{
		hero.takeDamage(attackPower);
	}

	public boolean isAlive()
	{
		return life > 0;
	}

	public String getType()
	{
		return type;
	}
	
	public int getAttackPower()
	{
		return attackPower;
	}

	public int getLife()
	{
		return life;
	}

	public String speak()
	{
		return speak;
	}

	public int getExperience()
	{
		return experience;
	}
}
