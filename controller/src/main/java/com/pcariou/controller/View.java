package com.pcariou.controller;

import com.pcariou.model.*;
/**
 *
 *
 */
public interface View
{
    void userNeedHero();
    void askUserHeroInfos();
    void displayAllMyHeros();
    void displayHeroStats(Hero hero);
    void askUserToStartNewGame();
}
