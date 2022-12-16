package com.pcariou.controller;

import com.pcariou.model.*;
import com.pcariou.DAO.*;

public class GameController implements Controller
{
	private View view;
	private Hero hero;
	private DAO<Hero> heroDAO;

	public GameController(DAO<Hero> heroDAO)
	{
		this.heroDAO = heroDAO;
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
		view.displayAllMyHeros();
	}

	public void createHeroWithInfos(String heroName, String choosenClass)
    { 
		hero = new HeroFactory().newHero(heroName, choosenClass);
		
		view.displayHeroStats(hero);
		view.askUserToStartNewGame();
    }

	public void runGame()
	{
		while (true)			
		{

		}
	}
}
