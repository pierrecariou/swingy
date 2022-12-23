package com.pcariou.view;
import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.pcariou.controller.*;
import com.pcariou.model.*;

public class GUIView extends JFrame implements View
{
    JPanel panelCenter; 
    JPanel panelBorderEAST;
    JPanel panelBorderNorth;
    JPanel panelBorderSouth;
    JPanel panelBorderWest;
    JPanel card0;
    JPanel card1;
    JPanel card2;
    JPanel card3;
    JPanel card4;
    JPanel card5;
    JPanel card6;
    JPanel card7;
    String previousScreen;

    public GUIView(Controller controller)
    {
        super("SWINGY");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
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

        card0 = new HomePanel(controller);
        card1 = new UserNeedHeroPanel(controller);
        card2 = new CreateHeroPanel(controller);
        card3 = new AllMyHeroesPanel(controller);
        card4 = new GameMapPanel(controller);
        card5 = new BattlePanel(controller);
        card6 = new StageCompletedPanel(controller);
        card7 = new GameOverPanel(controller);

        panelCenter.add(card0, PanelViews.HOME.toString());
        panelCenter.add(card1, PanelViews.USER_NEED_AN_HERO.toString());
        panelCenter.add(card2, PanelViews.CREATE_A_NEW_HERO.toString());
        panelCenter.add(card3, PanelViews.DISPLAY_ALL_MY_HEROES.toString());
        panelCenter.add(card4, PanelViews.GAME_MAP.toString());
        panelCenter.add(card5, PanelViews.BATTLE.toString());
        panelCenter.add(card6, PanelViews.STAGE_COMPLETE.toString());
        panelCenter.add(card7, PanelViews.GAME_OVER.toString());

        constructPanelBorderNorth();
    }

    public void changeScreen(String screen)
    {
        CardLayout cardLayout = (CardLayout)panelCenter.getLayout();
        cardLayout.show(panelCenter, screen);
        previousScreen = PanelViews.USER_NEED_AN_HERO.toString();
    }

    public void displayAllMyHeros(List<Hero> heroes)
    {
        ((AllMyHeroesPanel)card3).setHeroes(heroes);
        changeScreen(PanelViews.DISPLAY_ALL_MY_HEROES.toString());
    }

    public void displayHeroStats(Hero hero)
    {
        System.out.println(hero);
    }

    public void askUserToStartNewGame()
    {
        System.out.println("Do you want to start a new Game?");
    }

    public void displayError(String constraint)
    {
        JOptionPane.showMessageDialog(null, constraint, "InfoBox: constraint", JOptionPane.INFORMATION_MESSAGE);
    }

    private void constructPanelBorderNorth()
    {
        JButton button = new JButton("Back");
        button.addActionListener(e -> changeScreen(previousScreen));
        panelBorderNorth.add(button);
    }

    public void displayMap(GameMap map)
    {
        ((GameMapPanel)card4).setMap(map);
        changeScreen(PanelViews.GAME_MAP.toString());
    }

    public void updateMap()
    {
        ((GameMapPanel)card4).updatePanel();
    }

    public void heroAttack()
    {
        ((BattlePanel)card5).heroAttack();
    }

    public void monsterAttack()
    {
        ((BattlePanel)card5).monsterAttack();
    }

    public void aMonsterAppeared(Monster monster, Hero hero)
    {
        ((BattlePanel)card5).setHero(hero);
        ((BattlePanel)card5).setMonster(monster);
        changeScreen(PanelViews.BATTLE.toString());
    }

    public void fightWon()
    {
        ((BattlePanel)card5).fightWon();
    }

    public void resumeGame()
    {
       ((GameMapPanel)card4).updateHPAndXP();
        changeScreen(PanelViews.GAME_MAP.toString());
    }

    public void gameOver()
    {
    }
}
