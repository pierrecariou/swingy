package com.pcariou.controller;

public interface Controller
{
	void setView(View view);

	void startGame();	
	void createHero();
	void selectPreviouslyCreatedHero();
	void createHeroWithInfos(String heroName, String choosenClass);
	void runGame();
}
