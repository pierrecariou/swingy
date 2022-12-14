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
}
