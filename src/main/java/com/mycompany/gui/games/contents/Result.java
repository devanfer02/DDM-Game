package com.mycompany.gui.games.contents;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mycompany.gui.games.Engine;
import com.mycompany.gui.games.controllers.NodeController;
import com.mycompany.gui.helpers.SwingUtil;

public class Result extends Content
{
    private JButton playButton;
    private JButton menuButton;

    private JLabel resultLabel;

    public Result(int size)
    {
        super(size);
        cont = Engine.frame.getContentPane();

        JPanel titlePanel   = new JPanel();
        JPanel buttonsPanel = new JPanel(new GridLayout(2,1,0,10));

        resultLabel = new JLabel("");

        playButton  = new JButton("PLAY AGAIN"); 
        menuButton  = new JButton("BACK MENU"); 

        titlePanel.   setBounds(197, 150, 600, 150);
        buttonsPanel. setBounds(347,430,300,110);

        SwingUtil.setButtonColor(playButton);
        SwingUtil.setButtonColor(menuButton);

        SwingUtil.setFont(playButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(menuButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(resultLabel,70,Color.WHITE, Font.BOLD);

        SwingUtil.setInvisible(titlePanel);
        SwingUtil.setInvisible(buttonsPanel);

        titlePanel.   add(resultLabel);
        buttonsPanel. add(playButton);
        buttonsPanel. add(menuButton); 

        cont.add(titlePanel);
        cont.add(buttonsPanel);
 
        setPanels(new JPanel[]{titlePanel, buttonsPanel});  
        setVisible(false);
    } 

    public void instantiateHandler()    
    {
        playButton.addActionListener(new NodeController(this, 0));
        menuButton.addActionListener(new NodeController(this, 1));
    }

    public void updateText(String text)
    {
        resultLabel.setText(text);
    }
}
