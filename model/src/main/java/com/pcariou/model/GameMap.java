package com.pcariou.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class GameMap
{
	private Hero hero;
	private int sizeMap;
	private ArrayList<ArrayList<Integer>> map;
	private HashMap<List<Integer>, Monster> monsters;
	private MonsterFactory monsterFactory;

	public GameMap(Hero hero)
	{
		map = new ArrayList<>();
		monsters = new HashMap<>();
		Random random = new Random();
		monsterFactory = new MonsterFactory();
		int mapElement;

		this.hero = hero;
		sizeMap = (hero.getLevel() - 1) * 5 + 10 - hero.getLevel();

		for (int y = 0; y < sizeMap; y++) {
			map.add(new ArrayList<>());
			for (int x = 0; x < sizeMap; x++) {
				mapElement = random.nextInt(2);
				map.get(y).add(mapElement);
				if (mapElement == 1 && (x != sizeMap/2 || y != sizeMap/2)) {
					monsters.put(Arrays.asList(x, y), monsterFactory.newMonster());
				}
			}
		}
		hero.setPosition(Arrays.asList(sizeMap/2, sizeMap/2));
		map.get(sizeMap/2).set(sizeMap/2, 2);
	}

	public boolean isMonsterHere()
	{
		return monsters.containsKey(hero.getPosition());
	}

	public Monster getMonsterHere()
	{
		return monsters.get(hero.getPosition());
	}

	public void removeMonsterHere()
	{
		monsters.remove(hero.getPosition());
	}

	public boolean heroIsOutside()
	{
		return hero.getPosition().get(0) < 0 || hero.getPosition().get(0) >= sizeMap ||
			hero.getPosition().get(1) < 0 || hero.getPosition().get(1) >= sizeMap;
	}

	public int getSizeMap()
	{
		return sizeMap;
	}

	public ArrayList<ArrayList<Integer>> getMap()
	{
		return map;
	}

	public Hero getHero()
	{
		return hero;
	}


}
