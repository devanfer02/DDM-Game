package com.mycompany.gui.weapons;

public class Weapon 
{
    private int atk;
    private int condition;
    private String namaWeapon;
    private String passive;
    private boolean broken;

    public Weapon(String namaWeapon, String passive, int atk) 
    {
        setNamaWeapon(namaWeapon);
        setPassive(passive);
        setAtk(atk);
        setBroken(false);
        setCondition(100);
    }

    public void repair() 
    {
    	setCondition(100);
        setBroken(false); 
    }

    public int use() 
    {
    	if(isBroken())
    	{
            return 0;
    	}
    	setCondition(getCondition() - 10);
    	if(getCondition() < 1) setBroken(true);
        return getAtk();
    }

    public static int randomAtk(Weapon weapon) 
    {
        double percentage = Math.random() * (0.3 - 0.1) + 0.1;
        return (int)(percentage * weapon.use()); 
    }

    public static int readNumber(String passive)
	{
            String temp = "", res = "";
            int len = passive.length();
            for(int i = len-1; i >= 0 && passive.charAt(i) != '+'; i--)
            {
                    temp += passive.charAt(i);
            }
            len = temp.length();
            for(int i = len-1; i >= 0; i--)
            {
                    res += temp.charAt(i);
            }
            return Integer.parseInt(res);
	}

    // setter and getter
    public int getAtk()
    {
        return atk;
    }
    
    public int getCondition()
    {
        return condition;
    }

    public String getNamaWeapon()
    {
        return namaWeapon;
    }

    public String getPassive() 
    {
        return passive;
    }

    public boolean isBroken()
    {
        return broken;
    }

    public void setAtk(int atk)
    {
        this.atk = atk;
    }
    
    public void setCondition(int condition)
    {
        this.condition = condition;
    }

    public void setNamaWeapon(String namaWeapon)
    {
        this.namaWeapon = namaWeapon;
    }

    public void setPassive(String passive)
    {
        this.passive = passive;
    }

    public void setBroken(boolean broken)
    {
        this.broken = broken;
    }
}
