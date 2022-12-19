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
}
