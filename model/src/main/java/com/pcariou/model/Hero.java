package com.pcariou.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import javax.validation.constraints.NotNull;

//import jakarta.validation.constraints.NotBlank;
import javax.validation.constraints.*;

public class Hero
{
	@NotBlank(message = "Hero name cannot be blank")
	protected String name;
	protected String heroClass;
	protected int level;
	protected int experience;
	protected int attack;
	protected int defense;
	protected int hitPoints;
	private List<Integer> position;
	private int maxExperience;
	private int maxHitPoints;

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

		this.maxExperience = level * 1000 + (level - 1) * 450;
		this.maxHitPoints = 1000;
	}

	public void navigates(Cardinal cardinal)
	{
		switch (cardinal)
		{
			case North:
				position = new ArrayList<Integer>(Arrays.asList(position.get(0), position.get(1) - 1));
				break;
			case East:
				position = new ArrayList<Integer>(Arrays.asList(position.get(0) + 1, position.get(1)));
				break;
			case West:
				position = new ArrayList<Integer>(Arrays.asList(position.get(0) - 1, position.get(1)));
				break;
			case South:
				position = new ArrayList<Integer>(Arrays.asList(position.get(0), position.get(1) + 1));
				break;
		}
	}

	/*
	public void	fight(Monster monster)
	{
		while (monster.life > 0 && hitPoints > 0) {
			monster.takeDamage(attack);
			monster.attack(this);
		}
	}
	*/

	public void attack(Monster monster)
	{
		monster.takeDamage(attack);
	}

	public void takeDamage(int monsterAttackPower)
	{
		hitPoints-=monsterAttackPower;
	}

	public boolean isAlive()
	{
		return hitPoints > 0;
	}
	
	public void	run()
	{

	}

	private void levelUp(int experience)
	{
		level++;
		this.experience = experience;
		maxExperience = level * 1000 + (level - 1) * 450;
		//maxHitPoints = 1000;
		hitPoints = maxHitPoints;
	}

	public void gainExperience(int monsterExperience)
	{
		experience += monsterExperience;
		if (experience >= maxExperience)
			levelUp(experience - maxExperience);
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
	
	public String toString()
	{
		return String.format("name: %s\n class: %s\n level: %d\n experience: %d\n attack: %d\n defense: %d\n HP: %d\n", 
			name, heroClass, level, experience, attack, defense, hitPoints);
	}

	public String getSpecString()
	{
		return String.format("attack: %d\n defense: %d\n", 
			attack, defense);
	}

	public String getName()
	{
		return name;
	}

	public String getHeroClass()
	{
		return heroClass;
	}

	public int getExperience()
	{
		return experience;
	}

	public int getAttack()
	{
		return attack;
	}

	public int getDefense()
	{
		return defense;
	}

	public int getHitPoints()
	{
		return hitPoints;
	}

	public int getMaxExperience()
	{
		return maxExperience;
	}

	public int getMaxHitPoints()
	{
		return maxHitPoints;
	}

	public void setHitPoints(int hitPoints)
	{
		this.hitPoints = hitPoints;
	}
}
