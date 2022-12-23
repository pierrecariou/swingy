package com.pcariou.view;

import java.util.*;

import com.pcariou.model.*;

import javax.swing.*;
import com.pcariou.controller.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class BattlePanel extends JPanel
{
    private Controller controller;
    private Monster monster;
    private Hero hero;

    private LinkedHashMap<String, ImageIcon> classes;
    private LinkedHashMap<String, ImageIcon> monsters;
    private JLabel iconHero;
    private JLabel iconMonster;

    private JPanel panelMonsterInfos;
    private JPanel panelHeroInfos;

    private JPanel panelButtons;
    private JButton buttonResume;
    private JTextArea textArea;

    public BattlePanel(Controller controller)
    {
        super();
        this.controller = controller;

        panelMonsterInfos = new JPanel();
        panelHeroInfos = new JPanel();

        classes = new LinkedHashMap<>();
        try {
            classes.put("Warrior", new ImageIcon(ImageIO.read(new File("view/resources/icons8-warrior-64.png"))));
            classes.put("Hunter", new ImageIcon(ImageIO.read(new File("view/resources/icons8-archer-64.png"))));
            classes.put("Wizard", new ImageIcon(ImageIO.read(new File("view/resources/icons8-wizard-64.png"))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        monsters = new LinkedHashMap<>();
        try {
            monsters.put("Bear", new ImageIcon(ImageIO.read(new File("view/resources/icons8-bear-64.png"))));
            monsters.put("Unicorn", new ImageIcon(ImageIO.read(new File("view/resources/icons8-unicorn-64.png"))));
            monsters.put("Dragon", new ImageIcon(ImageIO.read(new File("view/resources/icons8-dragon-64.png"))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        textArea = new JTextArea(10, 1);

        setPanel();
    }

    public void setHero(Hero hero)
    {
        this.hero = hero;
    }

    public void setMonster(Monster monster)
    {
        this.monster = monster;
        updatePanel();
    }
   
    private void setPanel()
    {
        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, gbc);

        panelMonsterInfos.setBackground(Color.LIGHT_GRAY);
        panelMonsterInfos.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        panelMonsterInfos.setLayout(new GridBagLayout());

        panelHeroInfos.setBackground(Color.LIGHT_GRAY);
        panelHeroInfos.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        panelHeroInfos.setLayout(new GridBagLayout());

        add(panelMonsterInfos);
        add(panelHeroInfos, gbc);
        add(Box.createRigidArea(new Dimension(0, 40)), gbc);

        ImageIcon imageHero = classes.get("Warrior");
        iconHero = new JLabel(imageHero);

        ImageIcon imageMonster = monsters.get("Dragon");
        iconMonster = new JLabel(imageMonster);

        panelMonsterInfos.add(iconMonster);
        panelHeroInfos.add(iconHero);

        panelButtons = new JPanel();
        panelButtons.setBackground(Color.DARK_GRAY);

        JButton buttonAttack = new JButton("Fight");
        buttonAttack.addActionListener(e -> controller.fightMonster());

        JButton buttonRun = new JButton("Run");
        buttonRun.addActionListener(e -> controller.runAwayFromMonster());

        panelButtons.add(buttonAttack);
        panelButtons.add(buttonRun);

        add(panelButtons, gbc);

        buttonResume = new JButton("Resume");
        buttonResume.addActionListener(e -> controller.resumeGame());
        buttonResume.setVisible(false);

        add(buttonResume, gbc);

        //GridBagConstraints gbc = new GridBagConstraints();
        //gbc.gridwidth = GridBagConstraints.REMAINDER;
        //gbc.anchor = GridBagConstraints.CENTER;
        //gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    private void updatePanel()
    {
        buttonResume.setVisible(false);
        panelButtons.setVisible(true);
        iconHero.setIcon(classes.get(hero.getHeroClass()));
        iconMonster.setIcon(monsters.get(monster.getType()));
        textArea.setText(monster.getType() + " says: " + monster.speak());
    }

    public void heroAttack()
    {
        textArea.append("\n" + hero.getName() +  " attacks " + monster.getType() + " for " + hero.getAttack() + " damage");
    }

    public void monsterAttack()
    {
        textArea.append("\n"+ monster.getType() + " attacks hero for " + monster.getAttackPower() + " damage");
    }

    public void fightWon()
    {
        panelButtons.setVisible(false);
        textArea.append("\nThe monster has been defeated - VICTORY");
        buttonResume.setVisible(true);
    }
}
