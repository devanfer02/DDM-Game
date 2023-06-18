package com.mycompany.gui.games.contents;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mycompany.gui.games.Engine;
import com.mycompany.gui.games.controllers.*;
import com.mycompany.gui.helpers.SwingUtil;

public class Menu extends Content 
{
    private JLabel titleLabel;

    private JButton startButton;
    private JButton aboutButton;
    private JButton settingButton;
    private JButton exitButton;

    public Menu(int size) 
    {
        super(size);
        cont = Engine.frame.getContentPane();

        JPanel titlePanel = new JPanel();
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 0, 10));
        
        titleLabel = new JLabel("GAME GUI APP");
        
        startButton = new JButton("START GAME");
        aboutButton = new JButton("ABOUT GAME");
        settingButton = new JButton(" SETTINGS ");
        exitButton = new JButton(" EXIT APP ");
        
        buttonPanel.setBounds(347, 240, 302, 250);
        titlePanel.setBounds(197, 75, 600, 100);

        SwingUtil.setButtonColor(startButton);
        SwingUtil.setButtonColor(aboutButton);
        SwingUtil.setButtonColor(settingButton);
        SwingUtil.setButtonColor(exitButton);

        SwingUtil.setFont(startButton, 24, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(aboutButton, 24, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(settingButton, 24, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(exitButton, 24, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(titleLabel, 70, Color.WHITE, Font.BOLD);
        
        SwingUtil.setInvisible(buttonPanel);
        SwingUtil.setInvisible(titlePanel);

        titlePanel.add(titleLabel);
        buttonPanel.add(startButton);
        buttonPanel.add(aboutButton);
        buttonPanel.add(settingButton);
        buttonPanel.add(exitButton);

        cont.add(titlePanel);
        cont.add(buttonPanel);

        setPanels(new JPanel[]{titlePanel, buttonPanel});

        setVisible(false);
    }

    public void instantiateHandler() 
    {
        startButton.addActionListener(new NodeController(this, 2));
        aboutButton.addActionListener(new NodeController(this, 1));
        settingButton.addActionListener(new NodeController(this,0));
        exitButton.addActionListener(new ExitController());
    }
}