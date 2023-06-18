package com.mycompany.gui.weapons;

import java.util.*;
import java.io.*;

public class Armory
{
        private static Weapon[] weapons = initData("./src/data/armory.txt");

	public static Weapon getWeapon(int index)
	{
                return new Weapon(weapons[index].getNamaWeapon(), weapons[index].getPassive(), weapons[index].getAtk());
	}

	private static Weapon[] initData(String pathFile)
	{
                String temp;
                Weapon[] weapons = new Weapon[10];
                String[] atribut;
                File data = new File(pathFile);
                Scanner cin; int countWpn = 0, tmp;
                try {
                cin = new Scanner(data);
                while(cin.hasNextLine())
                {				
                        temp = cin.nextLine();
                        atribut = temp.split(";");
                        tmp = Integer.parseInt(atribut[2]);
                        if(atribut[0].contains("C"))
                        {
                                weapons[countWpn++] = new Bow(atribut[0],atribut[1],tmp);
                        } else if(atribut[0].contains("Java"))
                        {
                                weapons[countWpn++] = new Sword(atribut[0],atribut[1],tmp);
                        } else 
                        {
                                weapons[countWpn++] = new Weapon(atribut[0],atribut[1],tmp);
                        }
                        atribut = new String[]{"","",""};
                }
        } catch(Exception e)
        {
            System.err.printf("%s\n",e.getMessage());
            System.exit(0);
        }
                return weapons;
	}
}