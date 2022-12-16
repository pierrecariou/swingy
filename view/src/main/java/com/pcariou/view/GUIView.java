package com.pcariou.view;
import javax.swing.*;
import java.awt.*;

import com.pcariou.controller.*;
import com.pcariou.model.*;

public class GUIView extends JFrame implements View
{
    JPanel panelCenter; 
    JPanel panelBorderEAST;
    JPanel panelBorderNorth;
    JPanel panelBorderSouth;
    JPanel panelBorderWest;
    JPanel card1;
    JPanel card2;

    public GUIView(Controller controller)
    {
        super("SWINGY");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
        setVisible(true);

        panelCenter = new JPanel(new CardLayout());

        setLayout(new BorderLayout());
        panelBorderEAST = new JPanel();
        panelBorderNorth = new JPanel();
        panelBorderSouth = new JPanel();
        panelBorderWest = new JPanel();

        panelBorderEAST.setBackground(Color.lightGray);
        panelBorderWest.setBackground(Color.lightGray);
        panelBorderNorth.setBackground(Color.lightGray);
        panelBorderSouth.setBackground(Color.lightGray);

        panelBorderEAST.setPreferredSize(new Dimension(50, 50));
        panelBorderWest.setPreferredSize(new Dimension(50, 50));
        panelBorderNorth.setPreferredSize(new Dimension(50, 50));
        panelBorderSouth.setPreferredSize(new Dimension(50, 50));
        panelCenter.setPreferredSize(new Dimension(100, 100));

        add(panelBorderWest, BorderLayout.WEST);
        add(panelBorderEAST, BorderLayout.EAST);
        add(panelBorderNorth, BorderLayout.NORTH);
        add(panelBorderSouth, BorderLayout.SOUTH);
        add(panelCenter, BorderLayout.CENTER);

        card1 = new UserNeedHeroPanel(controller);
        card2 = new CreateHeroPanel(controller);

        panelCenter.add(card1, PanelViews.USER_NEED_AN_HERO.toString());
        panelCenter.add(card2, PanelViews.CREATE_A_NEW_HERO.toString());
    }

    public void changeScreen(String screen)
    {
        CardLayout cardLayout = (CardLayout)panelCenter.getLayout();
        cardLayout.show(panelCenter, screen);
    }

    public void displayAllMyHeros()
    {

    }

    public void displayHeroStats(Hero hero)
    {
        System.out.println(hero);
    }

    public void askUserToStartNewGame()
    {
        System.out.println("Do you want to start a new Gamne?");
    }
}
