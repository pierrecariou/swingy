package com.pcariou.games;

import com.pcariou.controller.*;
import com.pcariou.view.*;
import com.pcariou.DAO.*;

/**
 *
 *
 */
public class Games
{
    public static void main(String[] args)
    {
        View view = null;
        if (args.length != 1) {
            System.out.println("You need to provide a game mode: console or gui");
            System.out.println("Example: java -jar swingy.jar gui");
            return;
        }

        Controller controller = new GameController(new HeroDAO("jdbc:mariadb://localhost:3306/SWINGY", "root", "root"));

        if (args[0].equals("console"))
            view = new ConsoleView(controller);
        else if (args[0].equals("gui"))
            view = new GUIView(controller);
        else {
            System.out.println("You need to provide a game mode: console or gui");
            System.out.println("Example: java -jar swingy.jar gui");
            return;
        }
        controller.setView(view);
        controller.startGame();
    }
}
