package com.pcariou.view;
import java.io.*;

import com.pcariou.controller.*;
import com.pcariou.model.*;
import java.util.*;

/**
 *
 *
 */
public class ConsoleView implements View
{
    private Controller controller;
    private BufferedReader reader;

    public ConsoleView(Controller controller)
    {
        this.controller = controller;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void userNeedHero()
    {
        String choice = null;
        System.out.println( "***   SWINGY   ***\n\n");
        System.out.println("Choose what you want to do (Select the corresponding id):\n");
        System.out.println("1 - Create a Hero");
        System.out.print("2 - Select a Hero already created\n>> ");
        try {
            while (true) {
                choice = reader.readLine();
                if (choice.equals("1")) {
                    controller.createHero();
                    return;
                } else if (choice.equals("2")) {
                    controller.selectPreviouslyCreatedHero();
                    return;
                } else
                    System.out.println("Please enter a valid input: 1 or 2");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void askUserHeroInfos()
    {
        String choosenClass = null;
        String choice = null;
        String heroName = null;
        System.out.println("Choose a Class for your Hero:");
        System.out.println("1 - Warrior");
        System.out.println("2 - Hunter");
        System.out.println("3 - Wizard");
        try {
            while (true) {
                choice = reader.readLine();
                if (choice.equals("1")) {
                    choosenClass = "Warrior";
                    break;
                }
                if (choice.equals("2")) {
                    choosenClass = "Hunter";
                    break;
                }
                if (choice.equals("3")) {
                    choosenClass = "Wizard";
                    break;
                }
            }
            System.out.println(String.format("Choose a Name for your %s:", choosenClass));
            while (true) {
                heroName = reader.readLine();
                if (!heroName.isEmpty())
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        controller.createHeroWithInfos(heroName, choosenClass);
    }

    public void displayAllMyHeros(List<Hero> heroes)
    {
        System.out.println(heroes);
    }

    public void displayHeroStats(Hero hero)
    {
        System.out.println(hero);
    }


    public void askUserToStartNewGame()
    {
        String choice = null;

        System.out.println("Start Game? Y|N");
        try {
            while (true) {
                choice = reader.readLine();
                if (choice.equalsIgnoreCase("y")) {
                    controller.runGame(null);
                    return ;
                }
                if (choice.equalsIgnoreCase("n")) {
                    controller.startGame();
                }
                else {
                    System.out.println("Please type y or Y for yes, n or N for no");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeScreen(String screen)
    {

    }

    public void displayError(String constraint)
    {
        System.out.println(constraint);
    }

    public void displayMap(GameMap map)
    {
        System.out.println(map);
    }

    public void updateMap()
    {

    }

    public void aMonsterAppeared(Monster monster, Hero hero)
    {

    }

    public void heroAttack()
    {

    }

    public void monsterAttack()
    {

    }

    public void fightWon()
    {

    }

    public void gameOver()
    {

    }

    public void resumeGame()
    {

    }
}
