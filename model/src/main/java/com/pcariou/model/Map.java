package com.pcariou.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Map
{
	private Hero hero;
	private int sizeMap;
	private ArrayList<ArrayList<Integer>> newMap;
	private HashMap<List<Integer>, Monster> monsters;
	private MonsterFactory monsterFactory;

	public Map(Hero hero)
	{
		newMap = new ArrayList<>();
		monsters = new HashMap<>();
		Random random = new Random();
		monsterFactory = new MonsterFactory();
		int mapElement;

		this.hero = hero;
		sizeMap = (hero.getLevel() - 1) * 5 + 10 - hero.getLevel();

		for (int y = 0; y < sizeMap; y++) {
			newMap.add(new ArrayList<>());
			for (int x = 0; x < sizeMap; x++) {
				mapElement = random.nextInt(2);
				newMap.get(y).add(mapElement);
				if (mapElement == 1) {
					monsters.put(Arrays.asList(x, y), monsterFactory.newMonster());
				}
			}
		}

		hero.setPosition(Arrays.asList(sizeMap/2, sizeMap/2));
	}
}
