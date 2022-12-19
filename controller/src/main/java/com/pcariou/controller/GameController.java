package com.pcariou.controller;

import com.pcariou.model.*;

import com.pcariou.DAO.*;
import java.util.*;

public class GameController implements Controller
{
	private View view;
	private Hero hero;
	private List<Hero> heroes;
	private DAO<Hero> heroDAO;

	public GameController(DAO<Hero> heroDAO)
	{
		this.heroDAO = heroDAO;

		heroes = heroDAO.readAll();
		hero = heroes.get(0);
	}

	public void setView(View view)
	{
		this.view = view;
	}

	public void startGame()
	{
		view.changeScreen(PanelViews.USER_NEED_AN_HERO.toString());
	}

	public void createHero()
	{
		view.changeScreen(PanelViews.CREATE_A_NEW_HERO.toString());
	}

	public void selectPreviouslyCreatedHero()
	{
		view.displayAllMyHeros(heroes);
	}

	public void createHeroWithInfos(String heroName, String choosenClass)
    { 
		hero = new HeroFactory().newHero(heroName, choosenClass);

		heroDAO.create(hero);
		
		heroes.add(0, hero);
		view.displayAllMyHeros(heroes);
    }

	public void runGame()
	{
		while (true)			
		{

		}
	}
}
