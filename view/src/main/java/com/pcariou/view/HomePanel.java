package com.pcariou.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import com.pcariou.controller.*;
import java.awt.*;
import java.io.File;

public class HomePanel extends JPanel
{
    private Controller controller;
    private ImageIcon imageIcon;

    public HomePanel(Controller controller)
    {
        super();
        this.controller = controller;

        try {
            imageIcon = new ImageIcon(ImageIO.read(new File("view/resources/icons8-dragon-européen-96.png")));
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

        JLabel label = new JLabel("SWINGY");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel picLabel = new JLabel(imageIcon);
        
        add(label, gbc);
        add(Box.createRigidArea(new Dimension(0, 200)));
        add(picLabel, gbc);
    }
}
