package com.pcariou.view;

import java.util.*;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

import com.pcariou.controller.*;
import com.pcariou.model.*;

import java.awt.*;

public class GameMapPanel extends JPanel
{
    //private Color darkBlue;
    private Controller controller;
    private GameMap map;

    //private JPanel panelMap;
    private LinkedHashMap<String, ImageIcon> classes;
    private boolean setted;
    private JLabel heroIcon;


    private JProgressBar heroHealth;
    private JProgressBar heroExperience;

    public GameMapPanel(Controller controller)
    {
        super();
        this.controller = controller;
        
        classes = new LinkedHashMap<>();
        try {
            classes.put("Warrior", new ImageIcon(ImageIO.read(new File("view/resources/icons8-warrior-64.png"))));
            classes.put("Hunter", new ImageIcon(ImageIO.read(new File("view/resources/icons8-archer-64.png"))));
            classes.put("Wizard", new ImageIcon(ImageIO.read(new File("view/resources/icons8-wizard-64.png"))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //panelMap = new JPanel();
     //   darkBlue = new Color(49, 71, 92);
        setted = false;
    }

    public void setMap(GameMap map)
    {
        this.map = map;
        //System.out.println(map.getMap());
        if (!setted)
            setPanel();
        else
            updatePanel();
    }
   
    private void setPanel()
    {
        setted = true;
        //setBackground(19, 38, 92);
        setBackground(Color.DARK_GRAY);
        setLayout(null);


        int height = getHeight();
        int width = getWidth();

        // Draw Life Bar
        heroHealth = new JProgressBar(0, map.getHero().getMaxHitPoints());
        heroHealth.setValue(map.getHero().getHitPoints());
        heroHealth.setStringPainted(true);
        heroHealth.setForeground(Color.RED);
        heroHealth.setBounds(10, 10, 200, 20);
        heroHealth.setString("1000/1000 HP");
        add(heroHealth);

        // Draw Experience Bar
        heroExperience = new JProgressBar(0, 100, map.getHero().getMaxExperience());
        heroExperience.setValue(map.getHero().getExperience());
        heroExperience.setStringPainted(true);
        heroExperience.setForeground(Color.BLUE);
        heroExperience.setBounds(10, height - 40, width - 20, 20);
        heroExperience.setString("0/100 EXP");
        add(heroExperience);

        

        // Draw Hero
        ImageIcon imageIcon = classes.get(map.getHero().getHeroClass());
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);

        int beginX = (width / 3) - map.getHero().getPosition().get(1) * 11 + 2;
        int beginY = height - (height / 3) - 20;

        heroIcon = new JLabel(imageIcon);
        add(heroIcon);
        heroIcon.setBounds(beginX + 30 * map.getHero().getPosition().get(0), beginY - 11 * map.getHero().getPosition().get(1), 30, 30);

        //Draw map cubs
        for (int y = 0; y < map.getSizeMap(); y++) {
            for (int x = 0; x < map.getSizeMap(); x++) {
                JLabel label = new JLabel(new ImageIcon("view/resources/icons8-top-view-40.png"));
                add(label);
                //Beginning of the map at the bottom left
                beginX = (width / 3) - y * 11;
                beginY = height - (height / 3);
                label.setBounds(beginX + 30 * x, beginY -  11 * y, 40, 40);
            }
        }

        
        //Draw Cardinals
        JPanel chooseDirection = new JPanel(new BorderLayout());
        chooseDirection.setBackground(Color.DARK_GRAY);

        JButton north = new JButton(new ImageIcon("view/resources/icons8-north-30.png"));
        JButton south = new JButton(new ImageIcon("view/resources/icons8-south-30.png"));
        JButton west = new JButton(new ImageIcon("view/resources/icons8-west-30.png"));
        JButton east = new JButton(new ImageIcon("view/resources/icons8-east-30.png"));

        north.setSize(30, 30);
        south.setSize(30, 30);
        west.setSize(30, 30);
        east.setSize(30, 30);

        north.setMargin(new Insets(0, 0, 0, 0));
        south.setMargin(new Insets(0, 0, 0, 0));
        west.setMargin(new Insets(0, 0, 0, 0));
        east.setMargin(new Insets(0, 0, 0, 0));

        north.setBackground(Color.DARK_GRAY);
        south.setBackground(Color.DARK_GRAY);
        west.setBackground(Color.DARK_GRAY);
        east.setBackground(Color.DARK_GRAY);

        north.setBorder(BorderFactory.createEmptyBorder());
        south.setBorder(BorderFactory.createEmptyBorder());
        west.setBorder(BorderFactory.createEmptyBorder());
        east.setBorder(BorderFactory.createEmptyBorder());

        north.setFocusable(false);
        south.setFocusable(false);
        west.setFocusable(false);
        east.setFocusable(false);

        north.setFocusPainted(false);
        south.setFocusPainted(false);
        west.setFocusPainted(false);
        east.setFocusPainted(false);

        north.addActionListener(e -> controller.moveHero(Cardinal.North));
        south.addActionListener(e -> controller.moveHero(Cardinal.South));
        west.addActionListener(e -> controller.moveHero(Cardinal.West));
        east.addActionListener(e -> controller.moveHero(Cardinal.East));

        chooseDirection.add(north, BorderLayout.NORTH);
        chooseDirection.add(south, BorderLayout.SOUTH);
        chooseDirection.add(west, BorderLayout.WEST);
        chooseDirection.add(east, BorderLayout.EAST);
        add(chooseDirection);
        chooseDirection.setBounds(width - 120, 20, 100, 100);
    }

    public void updatePanel()
    {
        // Draw Hero
        
        System.out.println("Hero position : " + map.getHero().getPosition());

        int height = getHeight();
        int width = getWidth();

        int beginX = (width / 3) - map.getHero().getPosition().get(1) * 11 + 2;
        int beginY = height - (height / 3) - 20;

        heroIcon.setBounds(beginX + 30 * map.getHero().getPosition().get(0), beginY - 11 * map.getHero().getPosition().get(1), 30, 30);
        
        //TODO : erase old map
        //Draw map cubs
        for (int y = 0; y < map.getSizeMap(); y++) {
            for (int x = 0; x < map.getSizeMap(); x++) {
                JLabel label = new JLabel(new ImageIcon("view/resources/icons8-top-view-40.png"));
                add(label);
                //Beginning of the map at the bottom left
                beginX = (width / 3) - y * 11;
                beginY = height - (height / 3);
                label.setBounds(beginX + 30 * x, beginY -  11 * y, 40, 40);
            }
        }
    }

    public void updateHPAndXP()
    {
        heroHealth.setValue(map.getHero().getHitPoints());
        heroHealth.setString(map.getHero().getHitPoints() + "/" + map.getHero().getMaxHitPoints() + " HP");
        heroExperience.setMaximum(map.getHero().getMaxExperience());
        heroExperience.setValue(map.getHero().getExperience());
        heroExperience.setString(map.getHero().getExperience() + "/" + map.getHero().getMaxExperience() + " EXP");
    }
}
