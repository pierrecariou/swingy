package com.pcariou.controller;

import com.pcariou.model.*;

public class GameController implements Controller
{
	private View view;
	private Hero hero;

	public void setView(View view)
	{
		this.view = view;
	}

	public void startGame()
	{
		view.userNeedHero();
	}

	public void createHero()
	{
		view.askUserHeroInfos();
	}

	public void selectPreviouslyCreatedHero()
	{
		view.displayAllMyHeros();
	}

	public void createHeroWithInfos(String heroName, String choosenClass)
    { 
		hero = new HeroFactory().newHero(heroName, choosenClass);
		view.displayHeroStats(hero);
    }
}
