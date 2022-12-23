package com.pcariou.controller;

import com.pcariou.model.*;

public interface Controller
{
	void setView(View view);

	void startGame();	
	void createHero();
	void selectPreviouslyCreatedHero();
	void createHeroWithInfos(String heroName, String choosenClass);
	void runGame(String heroName);
	void runGame();
	void moveHero(Cardinal cardinal);
	void fightMonster();
	void runAwayFromMonster();
	void resumeGame();
}
