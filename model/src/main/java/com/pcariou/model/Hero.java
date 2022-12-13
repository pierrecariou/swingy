package com.pcariou.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hero
{
	protected String name;
	protected String heroClass;
	protected int level;
	protected int experience;
	protected int attack;
	protected int defense;
	protected int hitPoints;
	private List<Integer> position;

	public Hero(String name, String heroClass, int level, int experience, int attack, int defense, int hitPoints)	
	{
		this.name = name;
		this.heroClass = heroClass;
		this.level = level;
		this.experience = experience;
		this.attack = attack;
		this.defense = defense;
		this.hitPoints = hitPoints;
		this.position = null;
	}

	public void navigates(Cardinal cardinal)
	{
		switch (cardinal)
		{
			case North:
				position = new ArrayList<Integer>(Arrays.asList(position.get(0), position.get(1) - 1));
			case East:
				position = new ArrayList<Integer>(Arrays.asList(position.get(0) + 1, position.get(1)));
			case West:
				position = new ArrayList<Integer>(Arrays.asList(position.get(0) - 1, position.get(1) + 1));
			case South:
				position = new ArrayList<Integer>(Arrays.asList(position.get(0), position.get(1) + 1));
		}
	}

	public void	fight(Monster monster)
	{
		while (monster.life > 0 && hitPoints > 0) {
			monster.takeDamage(attack);
			monster.attack(this);
		}
	}

	public void takeDamage(int monsterAttackPower)
	{
		hitPoints-=monsterAttackPower;
	}

	public void	run()
	{

	}

	public int getLevel()
	{
		return level;
	}

	public List<Integer> getPosition()
	{
		return position;
	}

	public void setPosition(List<Integer> position)
	{
		this.position = position;	
	}
}
