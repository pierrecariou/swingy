package com.pcariou.controller;

import com.pcariou.model.*;
/**
 *
 *
 */
public interface View
{
    void displayAllMyHeros();
    void displayHeroStats(Hero hero);
    void askUserToStartNewGame();
    void changeScreen(String screen);
}
