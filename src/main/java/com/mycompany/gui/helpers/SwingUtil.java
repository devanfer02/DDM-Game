package com.mycompany.gui.helpers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SwingUtil 
{
    public static boolean textFieldEmpty(JTextField field)
    {
        return field.getText().equals("");
    }

    public static void setButtonColor(JButton button)
    {
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK); 
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER); 
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setFocusPainted(false);
    }

    public static void setFont(JComponent text, int size, Color color, int type)    
    {
        text.setFont(new Font("Calibri",type, size)); 
        text.setForeground(color);
    }

    public static ImageIcon resizeImageIcon(ImageIcon icon, int width, int heigth)
    {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, heigth, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage); 
    }

    public static void setInvisible(JComponent panel)
    {
        panel.setOpaque(false);
    }
}
