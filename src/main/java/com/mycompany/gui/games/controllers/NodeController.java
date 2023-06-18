package com.mycompany.gui.games.controllers;

import java.awt.event.ActionEvent;

import com.mycompany.gui.games.Engine;
import com.mycompany.gui.games.contents.Content;
import com.mycompany.gui.games.contents.Game;
import com.mycompany.gui.games.contents.Menu;
import com.mycompany.gui.games.contents.Result;
import com.mycompany.gui.games.contents.Setting;
import com.mycompany.gui.games.contents.Setup;
import com.mycompany.gui.helpers.console;
import com.mycompany.gui.music.Music;

public class NodeController extends Controller
{
    protected Content current;
    protected int index;

    public NodeController(Content current)
    {
        this.current = current;
        index = 0;
    }

    public NodeController(Content current, int index)
    {
        this.current = current;
        this.index = index;
    }

    public void actionPerformed(ActionEvent event)
    {
        Music.playButtonSound();

        if (current.getContents()[index] == null)
        {
            console.log("Next content has yet to be set properly");
            return;
        }

        current.setVisible(false);
        current.getContents()[index].setVisible(true);
        
        menuToSetupCase();
        settingToMenuGameRunCase();
        setupToGameCase();
        resultToGameCase();
        resultToMenuCase();
        Engine.setContent(current.getContents()[index].getContent());
    }

    private void settingToMenuGameRunCase()
    {
        boolean isSetting = current instanceof Setting;
        boolean isMenu = current.getContents()[index] instanceof Menu;
        
        if((!isSetting || !isMenu) || !Engine.gameIsRunning())
            return;
        
        Music.stop();
        Music.update();
        Music.play();
        ((Setting)current).updateButtons();
        Engine.setGameRunning(false);
    }

    private void setupToGameCase()
    {
        boolean isSetup = current instanceof Setup;
        boolean isGame = current.getContents()[index] instanceof Game;

        if (!isSetup || !isGame)
            return;

        
        // Setup setup = (Setup) current;
        Game game = (Game)current.getContents()[index];
        Setting setting = (Setting)game.getContents()[0];
        Music.stop();
        
        game.adjust();
        game.update();
        Music.playBattleSong(game.index);
        Engine.setGameRunning(true);
        setting.updateButtons();
    }

    private void menuToSetupCase()
    {
        boolean isMenu = current instanceof Menu; 
        boolean isSetup = current.getContents()[index] instanceof Setup;
        if(!isMenu || !isSetup) 
            return;
            
        Setup setup = (Setup)current.getContents()[index];
        setup.setDefault();
        setup.stop();
        setup.run();
    }
    
    private void resultToGameCase()
    {
        boolean isResult = current instanceof Result; 
        boolean isGame = current.getContents()[index] instanceof Game;

        if(!isGame || !isResult)
            return;
        
        Game game = (Game)current.getContents()[index];
        game.adjust();
        game.update();
        Music.playBattleSong(game.index);
        Engine.setGameRunning(true);
    }

    private void resultToMenuCase()
    {
        boolean isResult = current instanceof Result; 
        boolean isMenu = current.getContents()[index] instanceof Menu;

        if(!isMenu || !isResult)
            return;

        Music.stop();
        Music.update();
        Music.play();
        ((Setting)current.getContents()[index].getContents()[0]).updateButtons();
    }
}
