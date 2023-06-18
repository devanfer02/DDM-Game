package com.mycompany.gui.games.controllers;

import java.awt.event.ActionEvent;

import com.mycompany.gui.games.contents.Choices;
import com.mycompany.gui.games.contents.Content;
import com.mycompany.gui.music.Music;

public class ChoiceController extends NodeController 
{
    public ChoiceController(Content current)    
    {
        super(current);
    }

    public void actionPerformed(ActionEvent event)
    {
        Music.playButtonSound();
        String action = event.getActionCommand();
        ((Choices)current).actionHandling(action);
    }
}
