package com.pcariou.model;

import java.util.Random;

public class MonsterFactory
{
	public Monster newMonster()
	{
		Random random = new Random();
		int randomValue = random.nextInt(3);

		if (randomValue == 1)
			return new Dragon();
		if (randomValue == 2)
			return new Unicorn();
		if (randomValue == 3)
			return new Bear();
		return null;
	}
}
