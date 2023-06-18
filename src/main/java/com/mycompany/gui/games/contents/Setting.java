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
import com.mycompany.gui.music.Music;

public class Setting extends Content implements Choices 
{
    private JLabel titleLabel;
    private JLabel songLabel;
    private JLabel volumeLabel;

    private JButton volumeUpButton;
    private JButton volumeDownButton;
    private JButton nextButton;
    private JButton prevButton;
    private JButton turnButton;
    private JButton backButton;
    private JButton gameButton;

    private boolean visibility;

    public Setting(int size) 
    {
        super(size);
        cont = Engine.frame.getContentPane();

        JPanel titlePanel = new JPanel(new GridLayout(3, 1));
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        JPanel songPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        
        titleLabel  = new JLabel(" SETTINGS");
        songLabel   = new JLabel("Current song : " + Music.getCurrentSong());
        volumeLabel = new JLabel("Volume song : 100%");

        nextButton       = new JButton("NEXT");
        prevButton       = new JButton("PREV");
        turnButton       = new JButton("SOUND : ON");
        backButton       = new JButton("BACK MENU");
        volumeDownButton = new JButton("-");
        volumeUpButton   = new JButton("+");
        gameButton       = new JButton("CONTINUE GAME");

        titlePanel.setBounds(365, 90, 400, 190);
        songPanel.setBounds(348, 275, 302, 110);
        buttonPanel.setBounds(348, 420, 302, 170);

        SwingUtil.setFont(titleLabel, 60, Color.WHITE, Font.BOLD);
        SwingUtil.setFont(songLabel, 25, Color.WHITE, Font.BOLD);
        SwingUtil.setFont(volumeLabel, 25, Color.WHITE, Font.BOLD);
        
        SwingUtil.setInvisible(songPanel);
        SwingUtil.setInvisible(buttonPanel);
        SwingUtil.setInvisible(titlePanel);

        SwingUtil.setButtonColor(nextButton);
        SwingUtil.setButtonColor(prevButton);
        SwingUtil.setButtonColor(turnButton);
        SwingUtil.setButtonColor(backButton);
        SwingUtil.setButtonColor(volumeDownButton);
        SwingUtil.setButtonColor(volumeUpButton);
        SwingUtil.setButtonColor(gameButton);

        SwingUtil.setFont(nextButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(prevButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(turnButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(backButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(volumeDownButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(volumeUpButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(backButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(gameButton, 22, Color.BLACK, Font.BOLD);

        songPanel.add(volumeDownButton);
        songPanel.add(volumeUpButton);
        songPanel.add(prevButton);
        songPanel.add(nextButton);
        buttonPanel.add(turnButton);
        buttonPanel.add(backButton);
        buttonPanel.add(gameButton);

        titlePanel.add(titleLabel);
        titlePanel.add(songLabel);
        titlePanel.add(volumeLabel);

        cont.add(titlePanel);
        cont.add(songPanel);
        cont.add(buttonPanel);

        setPanels(new JPanel[]{titlePanel, buttonPanel, songPanel});

        setVisible(false);
        visibility = true;
        gameButton.setVisible(!visibility);
    }

    public void instantiateHandler() 
    {
        Controller settingHandler = new ChoiceController(this);
        nextButton.addActionListener(settingHandler);
        prevButton.addActionListener(settingHandler);
        turnButton.addActionListener(settingHandler);
        volumeDownButton.addActionListener(settingHandler);
        volumeUpButton.addActionListener(settingHandler);
        backButton.addActionListener(new NodeController(this));
        gameButton.addActionListener(new NodeController(this, 1));
    }

    public void updateButtons()
    {   
        songLabel.setText("Current song : " + Music.getCurrentSong());
        visibility = !visibility;
        prevButton.setVisible(visibility);
        nextButton.setVisible(visibility);
        gameButton.setVisible(!visibility);
    }

    public void actionHandling(String action)
    {
        switch (action) 
        {
            case "+" -> {
                Music.setVolume(Music.getVolume() + 0.1f);
                volumeLabel.setText("Volume song : " + String.format("%.0f%%", (Music.getVolume() * 100)));
            }
            case "-" -> {
                Music.setVolume(Music.getVolume() - 0.1f);
                volumeLabel.setText("Volume song : " + String.format("%.0f%%", (Music.getVolume() * 100)));
            }
            case "PREV" -> {
                Music.playPrev();
                songLabel.setText("Current song : " + Music.getCurrentSong());
            }
            case "NEXT" -> {
                Music.playNext();
                songLabel.setText("Current song : " + Music.getCurrentSong());
            }
            case "SOUND : ON" -> {
                Music.stop = true;
                Music.stop();
                turnButton.setText("SOUND : OFF");
            }
            case "SOUND : OFF" -> {
                Music.stop = false;
                Music.play();
                turnButton.setText("SOUND : ON");
            }
        }
    }
}
