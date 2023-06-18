package com.mycompany.gui.games.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener
{
    public abstract void actionPerformed(ActionEvent event);
}
