package com.pcariou.controller;

import com.pcariou.model.*;
import java.util.*;
/**
 *
 *
 */
public interface View
{
    void displayAllMyHeros(List<Hero> heros);
    void displayHeroStats(Hero hero);
    void askUserToStartNewGame();
    void changeScreen(String screen);
    void displayError(String error);
    void displayMap(GameMap map);
    void updateMap();
    void aMonsterAppeared(Monster monster, Hero hero);
    void heroAttack();
    void monsterAttack();
    void fightWon();
    void gameOver();
    void resumeGame();
}
