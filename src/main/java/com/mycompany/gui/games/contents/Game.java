package com.mycompany.gui.games.contents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mycompany.gui.games.Bot;
import com.mycompany.gui.games.Engine;
import com.mycompany.gui.games.contents.panels.CPanel;
import com.mycompany.gui.games.controllers.ChoiceController;
import com.mycompany.gui.games.controllers.Controller;
import com.mycompany.gui.games.controllers.NodeController;
import com.mycompany.gui.helpers.Function;
import com.mycompany.gui.helpers.SwingUtil;
import com.mycompany.gui.music.Music;
import com.mycompany.gui.weapons.Armory;
import com.mycompany.gui.weapons.Weapon;
import com.mycompany.gui.characters.Character;
import com.mycompany.gui.characters.Enemy;
import com.mycompany.gui.characters.Hero;

public class Game extends Content implements Choices
{
    // entity objects
    private Character player;
    private Character npc;
    private Character enemy;

    // bots
    private Bot npcBot;
    private Bot enemyBot;

    // components
    private CPanel npcPanel;
    private CPanel enemyPanel;
    private CPanel playerPanel;

    private JButton[] actChoiceButtons;
    private JButton settingButton;

    private JLabel actionLabel;
    private JLabel[] infoLabels;

    public int index;

    public Game(int size)
    {
        super(size);
        cont = Engine.frame.getContentPane();
        
        player = new Hero("PLAYER",new Weapon("null", "weaponAtk +30", 10), 500); 
        npc = new Hero("NPC", new Weapon("null", "weaponAtk +30", 10), 500);
        enemy  = new Enemy("ENEMY", new Weapon("null", "weaponAtk +30", 10), 500);
        
        npcPanel = new CPanel(npc);
        enemyPanel = new CPanel(enemy);
        playerPanel = new CPanel(player);
        JPanel buttonsPanel    = new JPanel(new GridLayout(1,1));
        JPanel actChoicesPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        JPanel infoPanel       = new JPanel(new GridLayout(3, 1));

        actionLabel = new JLabel("ACTIONS");

        actChoiceButtons    = new JButton[4];
        actChoiceButtons[0] = new JButton("ATTACK");
        actChoiceButtons[1] = new JButton("HEAL");
        actChoiceButtons[2] = new JButton("DEFENSE");
        actChoiceButtons[3] = new JButton("REPAIR");
        settingButton       = new JButton(new ImageIcon("./src/icons/setting.png"));

        infoLabels    = new JLabel[3];
        infoLabels[0] = new JLabel("<HTML></HTML>");
        infoLabels[1] = new JLabel("<HTML></HTML>");
        infoLabels[2] = new JLabel("<HTML></HTML>");

        npcBot = new Bot(npc, infoLabels[1]);
        enemyBot = new Bot(enemy, infoLabels[2]); 

        npcPanel.setBounds(200,80,300,300);
        enemyPanel.setBounds(680, 80, 300, 300);;
        actChoicesPanel.setBounds(20,390,150,250);
        buttonsPanel.setBounds(5,5,56,56);
        infoPanel.setBounds(210, 470, 550, 150); 
        playerPanel.setBounds(740,480,300,200);

        SwingUtil.setInvisible(actChoicesPanel);
        SwingUtil.setInvisible(buttonsPanel);   
        SwingUtil.setInvisible(settingButton);
        SwingUtil.setInvisible(infoPanel);
        for (int i = 0; i < 4; i++)  
        {
            SwingUtil.setFont(actChoiceButtons[i], 24, Color.BLACK, Font.BOLD);
            SwingUtil.setButtonColor(actChoiceButtons[i]);
        }

        SwingUtil.setButtonColor(settingButton);

        SwingUtil.setFont(actionLabel, 24, Color.WHITE, Font.BOLD);
        for (JLabel label : infoLabels)
            SwingUtil.setFont(label, 26, Color.WHITE, Font.BOLD);

        actChoicesPanel.add(actionLabel, BorderLayout.CENTER);
        for (JButton button : actChoiceButtons)
            actChoicesPanel.add(button);
        for (JLabel label : infoLabels) 
        {
            infoPanel.add(label);
            label.setBorder(new EmptyBorder(7, 0, 7, 0));
        }
        buttonsPanel.add(settingButton);

        // special section
        settingButton.setBorderPainted(false);
        playerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); 
        actionLabel.setBorder(new EmptyBorder(0,32,0,0));
        playerPanel.setFontSize(24);

        cont.add(npcPanel); 
        cont.add(enemyPanel);
        cont.add(actChoicesPanel);
        cont.add(infoPanel);
        cont.add(buttonsPanel);
        cont.add(playerPanel);

        setPanels(new JPanel[]
        {npcPanel, enemyPanel, playerPanel, actChoicesPanel, infoPanel, buttonsPanel});

        setVisible(false);
    }

    public void instantiateHandler()
    {
        Controller actHandler = new ChoiceController(this);
        for(JButton button : actChoiceButtons)
            button.addActionListener(actHandler);
        settingButton.addActionListener(new NodeController(this, 0));
    }

    public void adjust() 
    {
        for (JLabel label : infoLabels) 
            label.setText("");
        String npcName, enemyName;
        int npcMp, enemyMp;
        int rand; Weapon weapon;

        adjustLevel();
        npcName   = Engine.npcNames[index];
        npcMp     = Engine.npcMultiplier[index];
        enemyName = Engine.enemyNames[index];
        enemyMp   = Engine.enemyMutlipler[index]; 

        rand = Function.getRandomIndex(10);
        weapon = Armory.getWeapon(rand);

        npc.setName(npcName);
        npc.setWeapon(weapon);
        npc.setBaseHp(100 * npcMp);
        npc.setDef(Hero.getBaseAtk() + weapon.getAtk() / 2);

        weapon = Armory.getWeapon(rand);

        enemy.setName(enemyName);
        enemy.setWeapon(weapon);
        enemy.setBaseHp(100 * enemyMp);
        enemy.setDef(Enemy.getBaseAtk() + weapon.getAtk() / 2);

        weapon = Armory.getWeapon(rand);

        player.setName("YOU");
        player.setWeapon(weapon);
        player.setBaseHp((int)(100 * npcMp * 0.75)); 
        player.setDef(Hero.getBaseAtk() + weapon.getAtk() / 2);
    }

    private void adjustLevel()
    {
        switch(Engine.level)
        {
            case EASY -> {
                index = 0;
            }
            case NORMAL -> {
                index = 1;
            }
            case HARD -> {
                index = 2;
            }
            case EXTREME -> {
                index = 3;
            }
            default -> {
                throw new IllegalArgumentException("Unexpected value: " + Engine.level);
            }
        }
    }

    public void actionHandling(String action)
    {
        int damage; String attacked; Character temp;
        boolean attackNpc = action.equals(npc.getName());
        boolean attackEnemy = action.equals(enemy.getName());
        String status1 = "<HTML></HTML>";

        if (attackNpc || attackEnemy)
        {
            if (attackNpc)  
            { damage = npc.getHp(); player.attack(npc); temp = npc; }
            else 
            { damage = enemy.getHp(); player.attack(enemy); temp = enemy; }
            damage -= temp.getHp();
            attacked = temp.getName();
            status1 = String.format("YOU ATTACKED %s WITH %d DAMAGE!", attacked, damage);
            infoLabels[0].setText(status1);
            npcBot.action(player,enemy); enemyBot.action(player, npc);
            update();
            return;
        }

        switch (action) 
        {
            case "ATTACK" -> {
                actionLabel.setText("ATTACK");
                actChoiceButtons[0].setText(npc.getName());
                actChoiceButtons[1].setText(enemy.getName());
                actChoiceButtons[2].setText("BACK");
                actChoiceButtons[3].setVisible(false);
                if (npc.getHp() <= 0)
                    actChoiceButtons[0].setVisible(false);
                if (enemy.getHp() <= 0)
                    actChoiceButtons[1].setVisible(false);
            }
            case "HEAL" -> {
                int calc = player.getHp();
                player.heal();
                calc = player.getHp() - calc;
                status1 = String.format("YOU HEALED %d HP", calc); 
                infoLabels[0].setText(status1);
                npcBot.action(player,enemy); enemyBot.action(player, npc);
                update();
            }
            case "DEFENSE" -> {
                int calc = player.getDef();
                player.defense();
                calc = player.getDef() - calc;
                status1 = String.format("YOU DEFENSE UP %d DEF", calc); 
                infoLabels[0].setText(status1);
                npcBot.action(player,enemy); enemyBot.action(player, npc);
                update();
            }
            case "REPAIR" -> {
                status1 = "YOU REPAIRED YOUR WEAPON";
                infoLabels[0].setText(status1);
                player.getWeapon().repair();
                npcBot.action(player,enemy); enemyBot.action(player, npc);
                update();
            }
            case "BACK" -> {
                update();
            }
        }
        
    }

    public void update() 
    {
        if (isOver())
        {
            gotoResult();
            return;
        }
        
        actionLabel.setText("ACTIONS");
        actChoiceButtons[0].setText("ATTACK");
        actChoiceButtons[1].setText("HEAL");
        actChoiceButtons[2].setText("DEFENSE");
        actChoiceButtons[0].setVisible(true);
        actChoiceButtons[1].setVisible(true);
        actChoiceButtons[3].setVisible(true);
        npcPanel.update();
        enemyPanel.update();
        playerPanel.update();
    }

    private void gotoResult()
    {
        this.setVisible(false);
        this.getContents()[1].setVisible(true);
        ((Result)this.getContents()[1]).updateText(getResult());
        if(getResult().equals("YOU WIN"))
            Music.playWinSound();
        else 
            Music.playLoseSound();
        Engine.setContent(getContents()[1].getContent());
        Engine.setGameRunning(false);
    }

    private boolean isOver()
    {
        return (npc.getHp() <= 0 && enemy.getHp() <= 0) || (player.getHp() <= 0); 
    }

    public String getResult()
    {
        if(npc.getHp() <= 0 && enemy.getHp() <= 0)
            return "YOU WIN";
        return "YOU LOSE";
    }
}
