package com.mycompany.gui.games.controllers;

import java.awt.event.ActionEvent;

import com.mycompany.gui.music.Music;

public class ExitController extends Controller
{
    public void actionPerformed(ActionEvent event)
    {
        Music.playButtonSound();
        System.exit(0);
    }
}
