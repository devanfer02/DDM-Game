package com.mycompany.gui.games.contents;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.mycompany.gui.games.Engine;
import com.mycompany.gui.games.controllers.NodeController;
import com.mycompany.gui.helpers.SwingUtil;

public class About extends Content 
{
    private JLabel titleLabel;
    private JLabel prodiLabel;
    private JLabel facultyLabel;

    private JTextArea textDesc;

    private ImageIcon prodiImage;
    private ImageIcon facultyImage;

    private JButton backButton;

    public About(int size) 
    {
        super(size);
        cont = Engine.frame.getContentPane();

        JPanel titlePanel  = new JPanel();
        JPanel mainPanel   = new JPanel();
        JPanel buttonPanel = new JPanel(new GridLayout(1, 1));
        JPanel imagesPanel = new JPanel(new GridLayout(1, 2));

        prodiImage   = new ImageIcon("./src/icons/pti_white.png");
        facultyImage = new ImageIcon("./src/icons/filkom.png");
        facultyImage = SwingUtil.resizeImageIcon(facultyImage, 230, 67);
        prodiImage   = SwingUtil.resizeImageIcon(prodiImage, 197, 234);

        prodiLabel   = new JLabel(prodiImage);
        facultyLabel = new JLabel(facultyImage);

        titleLabel  = new JLabel("ABOUT");

        backButton  = new JButton("BACK MENU");

        textDesc = new JTextArea
        (
        """
            This gui app game was made to fullfill study case project PBO class.
            Huge thanks to our college lecturer who made the class fun exciting
            and make the course understandable for us.
            The game was made by Devan, Dinda and Mustika. Hence the game
            app title is called DDM, resembling us who made this game.
            If you want to contribute to this project, you can go to the following
            https://github.com/devanfer02/GUI-Game
        """
        );

        textDesc.setEditable(false);
        textDesc.setLineWrap(true);

        titlePanel.  setBounds(197, 75, 600, 90);
        mainPanel.   setBounds(197, 160, 600, 174);
        buttonPanel. setBounds(376, 490, 250, 50);
        imagesPanel. setBounds(150, 350, 640, 150);
        textDesc.    setBounds(100, 140, 600, 250);

        SwingUtil.setInvisible(titlePanel);
        SwingUtil.setInvisible(mainPanel);
        SwingUtil.setInvisible(imagesPanel);
        SwingUtil.setInvisible(textDesc);
        
        SwingUtil.setButtonColor(backButton);

        SwingUtil.setFont(backButton, 24, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(textDesc, 20, Color.WHITE, Font.PLAIN);
        SwingUtil.setFont(titleLabel, 60, Color.WHITE, Font.BOLD);

        titlePanel.  add(titleLabel);
        mainPanel.   add(textDesc);
        buttonPanel. add(backButton);
        imagesPanel. add(prodiLabel);
        imagesPanel. add(facultyLabel);

        cont.add(titlePanel);
        cont.add(mainPanel);
        cont.add(buttonPanel);
        cont.add(imagesPanel);

        setPanels(new JPanel[]{titlePanel, mainPanel, buttonPanel, imagesPanel});

        setVisible(false);
    }

    public void instantiateHandler() 
    {
        backButton.addActionListener(new NodeController(this));
    }
}
