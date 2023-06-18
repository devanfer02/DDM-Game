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
public abstract class Character 
{
    private String name;
    private Weapon weapon;
    private int baseHp;
    private int hp;
    private int def;
    private int level;

    public Character(String name, Weapon weapon, int hp)
    {
        setName(name);
        setWeapon(weapon);
        setBaseHp(hp);
        setHp(hp);
        setLevel(1); 
        setDef(hp/2);
        readPassive();
    }

    protected void readPassive()
    {
        String passive = weapon.getPassive();
        int stats = Weapon.readNumber(passive);
        switch(passive.charAt(0))
        {
            case 'w':
                weapon.setAtk(weapon.getAtk() + stats);
                break;
            case 'd':
                setDef(getDef()+stats);
                break;
            case 'h':
                setBaseHp(getBaseHp()+stats);
                break;
        }
    }

    // abstract methods
    public abstract void attack(Character character);

    public abstract void defense();

    // setter and getters
    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final Weapon getWeapon() {
        return weapon;
    }

    public final void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public final int getBaseHp() {
        return baseHp;
    }

    public final void setBaseHp(int baseHp) {
        this.baseHp = baseHp;
        this.hp = baseHp;
    }

    public final int getHp() {
        return hp;
    }

    public final void setHp(int hp) {
        this.hp = hp;
    }

    public final int getDef() {
        return def;
    }

    public final void setDef(int def) {
        this.def = def;
    }

    public final int getLevel() {
        return level;
    }

    public final void setLevel(int level) {
        this.level = level;
    }
}

