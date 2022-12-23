package com.pcariou.view;

import javax.swing.*;
import com.pcariou.controller.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

public class StageCompletedPanel extends JPanel
{
    private Controller controller;
    private ImageIcon imageIcon;

    public StageCompletedPanel(Controller controller)
    {
        super();
        this.controller = controller;

        try {
            imageIcon = new ImageIcon(ImageIO.read(new File("view/resources/icons8-rpg-64.png")));
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

        JLabel label = new JLabel("STAGE COMPLETED");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel picLabel = new JLabel(imageIcon);

        add(label, gbc);
        add(Box.createRigidArea(new Dimension(0, 200)));
        add(picLabel, gbc);

        JButton buttonGoBack = new JButton("Go back to main menu");
        JButton buttonPlayAgain = new JButton("Play next stage");
        buttonGoBack.setFocusable(false);
        buttonPlayAgain.setFocusable(false);

        buttonGoBack.addActionListener(e -> controller.startGame());
        buttonPlayAgain.addActionListener(e -> controller.runGame());

        add(buttonGoBack, gbc);
        add(Box.createRigidArea(new Dimension(0, 80)));
        add(buttonPlayAgain, gbc);
    }
}
