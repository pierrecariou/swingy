package com.pcariou.view;

import javax.swing.*;
import com.pcariou.controller.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

public class GameOverPanel extends JPanel
{
    private Controller controller;
    private ImageIcon imageIcon;

    public GameOverPanel(Controller controller)
    {
        super();
        this.controller = controller;
        
        try {
            imageIcon = new ImageIcon(ImageIO.read(new File("view/resources/icons8-death-64.png")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        setPanel();
    }
   
    private void setPanel()
    {
        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JLabel label = new JLabel("GAME OVER");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel picLabel = new JLabel(imageIcon);

        add(label, gbc);
        add(Box.createRigidArea(new Dimension(0, 200)));
        add(picLabel, gbc);

        JButton buttonCreate = new JButton("Create new Hero");
        JButton buttonSelect = new JButton("Select one of my Heroes");
        buttonCreate.setFocusable(false);
        buttonSelect.setFocusable(false);

        buttonCreate.addActionListener(e -> controller.createHero());
        buttonSelect.addActionListener(e -> controller.selectPreviouslyCreatedHero());

        add(buttonCreate, gbc);
        add(Box.createRigidArea(new Dimension(0, 80)));
        add(buttonSelect, gbc);
    }
}
