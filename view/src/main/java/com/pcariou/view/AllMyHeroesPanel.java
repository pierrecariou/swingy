package com.pcariou.view;

import com.pcariou.controller.*;
import com.pcariou.model.*;
import javax.swing.*;
import java.util.LinkedHashMap;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.List;

import java.awt.*;

public class AllMyHeroesPanel extends JPanel
{
    private Controller controller;
    private List<Hero> heroes;
    private Hero hero;
    private String heroName;
    private LinkedHashMap<String, ImageIcon> classes;
    JPanel panelHeroInfos;
    JComboBox<String> comboBox;
    boolean setted;

    public AllMyHeroesPanel(Controller controller)
    {
        super();
        this.controller = controller;

        panelHeroInfos = new JPanel();

        classes = new LinkedHashMap<>();
        try {
            classes.put("Warrior", new ImageIcon(ImageIO.read(new File("view/resources/icons8-warrior-64.png"))));
            classes.put("Hunter", new ImageIcon(ImageIO.read(new File("view/resources/icons8-archer-64.png"))));
            classes.put("Wizard", new ImageIcon(ImageIO.read(new File("view/resources/icons8-wizard-64.png"))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        setted = false;
        //setPanel();
    }

    public void setHeroes(List<Hero> heroes)
    {
        this.heroes = heroes;
        heroName = heroes.get(0).getName();
        hero = heroes.get(0);
        if (!setted)
            setPanel();
        else
            updatePanel();
    }
   
    private void setPanel()
    {
        setted = true;
        panelHeroInfos.setBackground(Color.LIGHT_GRAY);
        panelHeroInfos.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());
        panelHeroInfos.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        hero = heroes.get(0);
        String specsHero = hero.toString();
        JLabel heroInfos = new JLabel("<html>" + specsHero.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");

        JLabel picLabel = new JLabel();
        picLabel.setIcon(classes.get(hero.getHeroClass()));

        comboBox = new JComboBox<>(heroes.stream().map(Hero::getName).toArray(String[]::new));
        comboBox.addActionListener(e -> {
            if (comboBox.getSelectedItem() == null)
                return;
            heroName = comboBox.getSelectedItem().toString();
            Hero selectedHero = heroes.stream().filter(h -> h.getName().equals(heroName)).findFirst().get();
            String specs = selectedHero.toString();
            heroInfos.setText("<html>" + specs.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
            picLabel.setIcon(classes.get(selectedHero.getHeroClass()));
        });

        panelHeroInfos.add(Box.createRigidArea(new Dimension(0, 50)));
        panelHeroInfos.add(comboBox, gbc);
        panelHeroInfos.add(Box.createRigidArea(new Dimension(0, 50)));

        panelHeroInfos.add(heroInfos, gbc);

        add(panelHeroInfos);

        add(picLabel);

        JButton buttonPlay = new JButton("Play");
        buttonPlay.addActionListener(e -> {
            controller.runGame(heroName);
        });
        add(buttonPlay, gbc);
    }

    private void updatePanel()
    {
        comboBox.removeAllItems();
        heroes.forEach(hero -> comboBox.addItem(hero.getName()));
        comboBox.setSelectedItem(heroName);
    }
}
