/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui.characters;

import com.mycompany.gui.weapons.*;
/**
 *
 * @author Devan
 */
public class Hero extends Character
{
    private static int baseAtk;

    public Hero(String name, Weapon weapon, int hp)
    {
        super(name, weapon, hp);
        baseAtk = 58;
    }

    public void attack(Character character)
    {
        int characterHp = character.getHp();
        int damage = this.getLevel() * baseAtk + this.getWeapon().getAtk() - character.getDef() + Weapon.randomAtk(this.getWeapon());
        if(damage < 0) { if(damage<10) levelUp(); damage = 0;}
        characterHp -= damage;
        if(characterHp < 0) characterHp = 0;
        character.setHp(characterHp);
    }

    public void defense()
    {
        int addDef = this.getDef() + baseAtk * this.getLevel() / 2; 
        if (addDef > getBaseDef())
            addDef = 0;
        this.setDef(this.getDef() + addDef);
    }

    public static int getBaseAtk()
    {
        return baseAtk;
    }
}

