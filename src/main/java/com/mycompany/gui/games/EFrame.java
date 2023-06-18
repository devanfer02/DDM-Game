package com.mycompany.gui.games;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EFrame extends JFrame 
{
    private String iconPath;
    private String backgroundPath;

    private Image backgroundImage;
    private Image iconImage;

    public EFrame()
    {
        iconPath = "./src/icons/pti_black.png";
        backgroundPath = "./src/images/pic3.png"; 

        backgroundImage = new ImageIcon(backgroundPath).getImage();
        iconImage = new ImageIcon(iconPath).getImage(); 

        setSize(1000,720); 

        JPanel contentPanel = new JPanel() {
            protected void paintComponent(Graphics graphic)
            {
                super.paintComponent(graphic);
                graphic.drawImage(backgroundImage,0,0,null);
            }
        };

        setContentPane(contentPanel);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("DDM GUI APP");
        setIconImage(iconImage);
    }

}
