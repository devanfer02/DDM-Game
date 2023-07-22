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

public class Opening extends Content 
{
    JLabel titleLabel;

    JButton startButton;
    JButton exitButton; 
    
    public Opening(int size)   
    {
        super(size);
        cont = Engine.frame.getContentPane(); 
        
        JPanel titlePanel  = new JPanel();
        JPanel buttonPanel = new JPanel(new GridLayout(2,1,0,10));
        
        titleLabel  = new JLabel("DDM GUI APP");
        
        startButton = new JButton(" CONTINUE ");
        exitButton  = new JButton("EXIT GAME"); 

        titlePanel.  setBounds(197, 150, 600, 150);
        buttonPanel. setBounds(347,430,300,110);

        SwingUtil.setButtonColor (exitButton);
        SwingUtil.setButtonColor (startButton);
        SwingUtil.setFont        (exitButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont        (startButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont        (titleLabel, 90, Color.WHITE, Font.BOLD);
        SwingUtil.setInvisible   (titlePanel); 
        SwingUtil.setInvisible   (buttonPanel);
        
        titlePanel.  add(titleLabel);
        buttonPanel. add(startButton);
        buttonPanel. add(exitButton); 

        cont.add(titlePanel);
        cont.add(buttonPanel);    

        setPanels(new JPanel[]{titlePanel, buttonPanel});
    }

    public void instantiateHandler()
    {   
        startButton.addActionListener (new NodeController(this));
        exitButton.addActionListener  (new ExitController()); 
    }
}
