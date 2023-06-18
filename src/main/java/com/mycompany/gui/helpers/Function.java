package com.mycompany.gui.helpers;

public class Function 
{
    public static int getRandomIndex(int modulo)
    {
        return (int)(Math.random() * 100 + 1) % modulo;
    }
}
