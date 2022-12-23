package com.pcariou.view;

import javax.swing.*;
import com.pcariou.controller.*;
import com.pcariou.model.HeroFactory;
import java.util.LinkedHashMap;
import java.io.*;
import javax.imageio.ImageIO;

import java.awt.*;

public class CreateHeroPanel extends JPanel
{
    private Controller controller;
    private String heroClass;
    private HeroFactory heroFactory;
    private LinkedHashMap<String, ImageIcon> classes;
    JPanel panelClassInfos;

    public CreateHeroPanel(Controller controller)
    {
        super();
        this.controller = controller;
        heroClass = "Warrior";

        panelClassInfos = new JPanel();

        classes = new LinkedHashMap<>();
        try {
            classes.put("Warrior", new ImageIcon(ImageIO.read(new File("view/resources/icons8-warrior-64.png"))));
            classes.put("Hunter", new ImageIcon(ImageIO.read(new File("view/resources/icons8-archer-64.png"))));
            classes.put("Wizard", new ImageIcon(ImageIO.read(new File("view/resources/icons8-wizard-64.png"))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        setPanel();
    }
   
    private void setPanel()
    {
        panelClassInfos.setBackground(Color.LIGHT_GRAY);
        panelClassInfos.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());
        panelClassInfos.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        heroFactory = new HeroFactory();
        String specsWarrior = heroFactory.newHero("", "Warrior").getSpecString();
        JLabel classInfos = new JLabel("<html>" + specsWarrior.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");

        JPanel addName = new JPanel();
        
        JLabel picLabel = new JLabel();
        picLabel.setIcon(classes.get(heroClass));

        JComboBox<String> comboBox = new JComboBox<>(classes.keySet().toArray(new String[classes.size()]));
        comboBox.addActionListener(e -> {
            heroClass = comboBox.getSelectedItem().toString();
            String specs = heroFactory.newHero("", heroClass).getSpecString();
            classInfos.setText("<html>" + specs.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
            picLabel.setIcon(classes.get(heroClass));
        });

        panelClassInfos.add(Box.createRigidArea(new Dimension(0, 50)));
        panelClassInfos.add(comboBox, gbc);
        panelClassInfos.add(Box.createRigidArea(new Dimension(0, 50)));

        panelClassInfos.add(classInfos, gbc);


        JLabel labelName = new JLabel("Name: ");
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(80, 40));
        panelClassInfos.add(Box.createRigidArea(new Dimension(0, 50)));

        addName.setBackground(Color.LIGHT_GRAY);
        addName.add(labelName);
        addName.add(textField);
        panelClassInfos.add(addName, gbc);

        add(panelClassInfos);

        add(picLabel);

        JButton buttonCreateHero = new JButton("Create my Hero");
        buttonCreateHero.addActionListener(e -> controller.createHeroWithInfos(textField.getText(), heroClass) );
        add(buttonCreateHero, gbc);
    }
}
