package com.pcariou.model;

public class HeroFactory
{
	public Hero newHero(String name, String choosenClass)
	{
		switch (choosenClass)
		{
			case "Warrior":
				return new Warrior(name);
			case "Hunter":
				return new Hunter(name);
			case "Wizard":
				return new Wizard(name);
		}
		return null;
	}

	public Hero newHero(String name, String heroClass, int level, int experience, int attack, int defense, int hitPoints)
	{
		switch (heroClass)
		{
			case "Warrior":
				return new Warrior(name, level, experience, attack, defense, hitPoints);
			case "Hunter":
				return new Hunter(name, level, experience, attack, defense, hitPoints);
			case "Wizard":
				return new Wizard(name, level, experience, attack, defense, hitPoints);
		}
		return null;
	}
}
