package com.mycompany.gui.weapons;

public class Bow extends Weapon
{
    public Bow(String name, String passive, int atk)
    {
        super(name, passive, atk); 
    }

    @Override
    public int use()
    {
        if(this.isBroken())
        {
            return 0;
        }
        this.setCondition(getCondition()-20);
        if(this.getCondition() < 1) 
        {
            setBroken(true);
        }
        return this.getAtk() + (int)((this.getAtk() * 0.5)); 
    }
}
