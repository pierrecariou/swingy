package com.pcariou.controller;

import com.pcariou.model.*;

import com.pcariou.DAO.*;
import java.util.*;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
//import jakarta.validation.ValidatorFactory;

public class GameController implements Controller
{
	private View view;
	private Hero hero;
	private List<Hero> heroes;
	private GameMap map;

	private DAO<Hero> heroDAO;

	Validator validator;

	public GameController(DAO<Hero> heroDAO)
	{
		this.heroDAO = heroDAO;

		heroes = heroDAO.readAll();
		hero = heroes.get(0);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	public void setView(View view)
	{
		this.view = view;
	}

	public void startGame()
	{
		view.changeScreen(PanelViews.HOME.toString());
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e);
		}
		view.changeScreen(PanelViews.USER_NEED_AN_HERO.toString());
	}

	public void createHero()
	{
		view.changeScreen(PanelViews.CREATE_A_NEW_HERO.toString());
	}

	public void selectPreviouslyCreatedHero()
	{
		heroes = heroDAO.readAll();
		view.displayAllMyHeros(heroes);
	}

	public void createHeroWithInfos(String heroName, String choosenClass)
    { 
		hero = new HeroFactory().newHero(heroName, choosenClass);
		if (!validateInput(hero))
			return;

		heroDAO.create(hero);
		
		heroes = heroDAO.readAll();
		heroes.add(0, hero);
		heroes.remove(heroes.size() - 1);

		view.displayAllMyHeros(heroes);
    }

	public void runGame(String heroName)
	{
		//get hero from heroes list by name
		hero = heroes.stream()
			.filter(hero -> hero.getName().equals(heroName))
			.findFirst()
			.orElse(null);
		map = new GameMap(hero);
		view.displayMap(map);
	}

	public void runGame()
	{
		map = new GameMap(hero);
		view.displayMap(map);
	}

	public void moveHero(Cardinal cardinal)
	{
		hero.navigates(cardinal);
		if (map.heroIsOutside()) {
			hero.setHitPoints(hero.getMaxHitPoints());
			heroDAO.update(hero);
			view.changeScreen(PanelViews.STAGE_COMPLETE.toString());
			return;
		}
		view.updateMap();
		if (map.isMonsterHere()) {
			view.aMonsterAppeared(map.getMonsterHere(), hero);
			//view.changeScreen(PanelViews.BATTLE.toString());
		}
	}

	public void fightMonster()
	{
		while (hero.isAlive() && map.getMonsterHere().isAlive()) {
			hero.attack(map.getMonsterHere());
			view.heroAttack();
			if (map.getMonsterHere().isAlive()) {
				map.getMonsterHere().attack(hero);
				view.monsterAttack();
			}
		}
		if (hero.isAlive()) {
			hero.gainExperience(map.getMonsterHere().getExperience());
			map.removeMonsterHere();
			//view.changeScreen(PanelViews.GAME_MAP.toString());
			view.fightWon();
		} else {
			view.gameOver();
			System.out.println("Game Over");
		}
	}

	public void runAwayFromMonster()
	{
		view.changeScreen(PanelViews.GAME_MAP.toString());
	}

	public void resumeGame()
	{
		view.resumeGame();
	}
	
	private boolean validateInput(Object hero) {
    	Set<ConstraintViolation<Object>> violations = validator.validate(hero);
    	if (!violations.isEmpty()) {
			view.displayError(violations.iterator().next().getMessage());
			return false;
		}
		return true;
  	}
}
