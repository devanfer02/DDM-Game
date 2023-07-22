package com.mycompany.gui.games;

import javax.swing.JLabel;

import com.mycompany.gui.characters.Character;
import com.mycompany.gui.characters.Enemy;

public class Bot 
{
    private Character character;
    private JLabel label;
    private boolean[] attempts;

    public Bot(Character character, JLabel label) 
    {
        this.character = character;
        this.label     = label;
        attempts       = new boolean[4]; 
    }

    public void action(Character character1, Character character2)
    {
        if (isDead()) 
        {
            return;
        }

        String message = String.format("%s is delibeating...", character.getName());
        int choice     = makeChoice();
        
        switch (choice) 
        {
            case 1 -> {
                // attack
                message = attack(character1, character2);
            }
            case 2 -> {
                // heal
                if ((double)character.getHp() > (character.getBaseHp() / 0.6))
                {
                    attempts[1] = true;
                    action(character1, character2);
                    return;
                }
                else 
                {
                    message = heal();
                }
            }
            case 3 -> {
                // defense
                message = defense();
            }
            case 4 -> {
                // repair weapon
                if (!character.getWeapon().isBroken()) 
                {
                    attempts[3] = true;
                    action(character1, character2);
                    return;
                }
                else 
                {
                    message = repair();
                }
            }
        }   
        
        label.setText(message);
    }

    private String attack(Character character1, Character character2) 
    {
        int choice, calc;
        String message; 

        message = String.format("%s ", character.getName());

        if (character instanceof Enemy)
        {
            message += "MUNCHED ";
        }
        else 
        {
            message += "ATTACKED ";
        }

        choice = (int)(Math.random() * 2 + 1);

        switch (choice) 
        {
            case 1 -> 
            {
                calc =  character1.getHp();
                character.attack(character1);
                calc -= character1.getHp();
                message += String.format("%s WITH %d DAMAGE", character1.getName(), calc);
            }
            case 2 -> 
            {
                calc =  character2.getHp();
                character.attack(character2);
                calc -= character2.getHp();
                message += String.format("%s WITH %d DAMAGE", character2.getName(), calc);
            }
        }
        
        setFalse();
        return message;
    }

    private boolean isDead()
    {
        return character.getHp() < 1;
    }

    private String heal()
    {
        int calc; 

        calc = character.getHp();
        character.heal();
        calc = character.getHp() - calc;

        setFalse();
        return String.format("%s HEALED %d HP", character.getName(), calc);
    }

    private String defense()
    {
        int calc;

        calc = character.getDef();
        character.defense();
        calc = character.getDef() - calc;

        setFalse();
        return String.format("%s DEFENSE UP %d DEF", character.getName(), calc);
    }

    private String repair()
    {
        character.getWeapon().repair();

        setFalse();

        return String.format("%s REPAIRED THEIR WEAPON", character.getName());
    }

    private void setFalse()
    {
        for (int i = 0; i < 4; i++) 
        {
            attempts[i] = false;
        }
    }

    private int makeChoice()
    {
        int choice = (int)(Math.random() * 4 + 1);

        if (choice == 2 && attempts[1]) 
        {
            choice -= 1;
        }
        
        if (choice == 2 && attempts[3]) 
        {
            choice -= 1;
        }
        

        return choice;
    }
}
