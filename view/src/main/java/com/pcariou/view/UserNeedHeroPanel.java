package com.pcariou.view;

import javax.swing.*;
import com.pcariou.controller.*;
import java.awt.*;

public class UserNeedHeroPanel extends JPanel
{
    private Controller controller;

    public UserNeedHeroPanel(Controller controller)
    {
        super();
        this.controller = controller;
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

        JButton buttonCreate = new JButton("Create new Hero");
        JButton buttonSelect = new JButton("Select one of my Heroes");
        buttonCreate.setFocusable(false);
        buttonSelect.setFocusable(false);

        buttonCreate.addActionListener(e -> controller.createHero());
        buttonSelect.addActionListener(e -> controller.selectPreviouslyCreatedHero());

        add(buttonCreate, gbc);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(buttonSelect, gbc);
    }
}
