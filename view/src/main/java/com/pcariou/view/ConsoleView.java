package com.pcariou.view;
import java.io.*;

import com.pcariou.controller.*;
import com.pcariou.model.*;

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

    public void displayAllMyHeros()
    {

    }

    public void displayHeroStats(Hero hero)
    {
        System.out.println(hero);
    }
}
