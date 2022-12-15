package com.pcariou.view;

import javax.swing.*;
import java.awt.*;

import com.pcariou.controller.*;
import com.pcariou.model.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class GUIView extends JFrame implements View
{
    private Controller controller;
    JPanel panelCenter; 
    JPanel card1;
    JPanel card2;

    public GUIView(Controller controller)
    {
        super("SWINGY");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
        //getContentPane().setBackground(Color.darkGray);
        setVisible(true);

        setLayout(new BorderLayout());

        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel4 = new JPanel();
        panelCenter = new JPanel(new CardLayout());

        jPanel1.setBackground(Color.lightGray);
        jPanel2.setBackground(Color.lightGray);
        jPanel3.setBackground(Color.lightGray);
        jPanel4.setBackground(Color.lightGray);
        //panelCenter.setBackground(Color.darkGray);

        jPanel1.setPreferredSize(new Dimension(50, 50));
        jPanel2.setPreferredSize(new Dimension(50, 50));
        jPanel3.setPreferredSize(new Dimension(50, 50));
        jPanel4.setPreferredSize(new Dimension(50, 50));
        panelCenter.setPreferredSize(new Dimension(100, 100));

        add(jPanel1, BorderLayout.WEST);
        add(jPanel2, BorderLayout.EAST);
        add(jPanel3, BorderLayout.NORTH);
        add(jPanel4, BorderLayout.SOUTH);
        add(panelCenter, BorderLayout.CENTER);

        card1 = new JPanel();
        card2 = new JPanel();

        panelCenter.add(card1, "userNeedHero");
        panelCenter.add(card2, "askUserHeroInfos");

        this.controller = controller;
    }

    public void userNeedHero()
    {
        card1.setBackground(Color.DARK_GRAY);
        card1.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton buttonCreate = new JButton("Create new Hero");
        JButton buttonSelect = new JButton("Select one of my Heroes");
        buttonCreate.setFocusable(false);
        buttonSelect.setFocusable(false);

        buttonCreate.addActionListener(e -> controller.createHero());
        buttonSelect.addActionListener(e -> controller.selectPreviouslyCreatedHero());

        card1.add(buttonCreate, gbc);
        card1.add(Box.createRigidArea(new Dimension(0, 50)));
        card1.add(buttonSelect, gbc);
    }

    public void askUserHeroInfos()
    {
        BufferedImage myPicture;

        CardLayout cl = (CardLayout)panelCenter.getLayout();
        cl.show(panelCenter, "askUserHeroInfos");

        JPanel panelclass = new JPanel();
        panelclass.setBackground(Color.LIGHT_GRAY);
        panelclass.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        card2.setBackground(Color.DARK_GRAY);
        card2.setLayout(new GridBagLayout());
        panelclass.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        HeroFactory heroFactory = new HeroFactory();
        String specsWarrior = heroFactory.newHero("", "Warrior").getSpecString();
        JLabel classInfos = new JLabel("<html>" + specsWarrior.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");

        JPanel addName = new JPanel();

        String[] classes = {"Warrior", "Hunter", "Wizard"};
        JComboBox<String> comboBox = new JComboBox<>(classes);
        comboBox.addActionListener(e -> {
            String specs = heroFactory.newHero("", comboBox.getSelectedItem().toString()).getSpecString();
            classInfos.setText("<html>" + specs.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
            //panelclass.setBackground(Color.BLUE);
            //addName.setBackground(Color.BLUE);
        });

        panelclass.add(Box.createRigidArea(new Dimension(0, 50)));
        panelclass.add(comboBox, gbc);
        panelclass.add(Box.createRigidArea(new Dimension(0, 50)));

        panelclass.add(classInfos, gbc);

        JLabel labelName = new JLabel("Name: ");
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(80, 40));
        panelclass.add(Box.createRigidArea(new Dimension(0, 50)));

        addName.setBackground(Color.LIGHT_GRAY);
        addName.add(labelName);
        addName.add(textField);
        panelclass.add(addName, gbc);

        card2.add(panelclass);

        try {
            myPicture = ImageIO.read(new File("view/resources/icons8-iron-age-warrior-48.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            card2.add(picLabel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        JButton buttonCreateHero = new JButton("Create my Hero");
        card2.add(buttonCreateHero, gbc);
    }

    public void displayAllMyHeros()
    {

    }

    public void displayHeroStats(Hero hero)
    {

    }

    public void askUserToStartNewGame()
    {

    }
}
