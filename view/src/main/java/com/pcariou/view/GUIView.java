package com.pcariou.view;

import javax.swing.JFrame;

import com.pcariou.controller.*;
import com.pcariou.model.*;

public class GUIView implements View
{
    private Controller controller;

    public GUIView(Controller controller)
    {
        this.controller = controller;
    }

    public void userNeedHero()
    {
        JFrame window = new JFrame("swingy");
        window.setSize(800, 600);
        window.setVisible(true);
    }

    public void askUserHeroInfos()
    {

    }

    public void displayAllMyHeros()
    {

    }

    public void displayHeroStats(Hero hero)
    {

    }
}
