package com.mycompany.gui.games.contents.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mycompany.gui.characters.Character;
import com.mycompany.gui.characters.Enemy;
import com.mycompany.gui.characters.Hero;
import com.mycompany.gui.helpers.SwingUtil;

public class CPanel extends JPanel
{
    private JLabel[] labels;
    private Character character;
    private int baseAtk;

    public CPanel(Character character)
    {
        super(new GridLayout(4,1));
        this.character = character;

        if (character instanceof Hero)
            baseAtk = Hero.getBaseAtk();
        else 
            baseAtk = Enemy.getBaseAtk();

        labels      = new JLabel[4];
        labels[0]   = new JLabel(character.getName());
        labels[1]   = new JLabel(String.valueOf(character.getHp()));
        labels[2]   = new JLabel(String.valueOf(character.getWeapon().getAtk()));
        labels[3]   = new JLabel(String.valueOf(character.getDef()));
    
        for (JLabel label : labels)
        {
            label.setBorder(new EmptyBorder(0, 15, 0, 15));
            SwingUtil.setFont(label,30,Color.WHITE, Font.BOLD);
            add(label); 
        }
        SwingUtil.setInvisible(this);   
    }

    public void update()
    {
        labels[0].setText(character.getName() + " (" + character.getLevel() +")");
        labels[1].setText("HP  : " + String.valueOf(character.getHp()));
        labels[2].setText("ATK : " + String.valueOf(character.getWeapon().getAtk() + baseAtk));
        labels[3].setText("DEF : " + String.valueOf(character.getDef()));
    }

    public void setFontSize(int size)
    {
        for(JLabel label : labels)
            SwingUtil.setFont(label,22,Color.WHITE, Font.BOLD);
    }
}
