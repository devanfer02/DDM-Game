package com.mycompany.gui.games;

import java.awt.Container;

import com.mycompany.gui.games.contents.*;
import com.mycompany.gui.music.Music;

import java.util.ArrayList;
import java.util.List;

public class Engine implements GameSetting
{
    public static Difficulty level;
    public static EFrame frame; 
    private static boolean running;
    private List<Content> contents;
    
    public Engine()
    {
        Music.init();
        frame = new EFrame();
        level = Difficulty.EASY;

        // all instantiations
        contents = new ArrayList<>();
        contents.add(new Opening(1));
        contents.add(new Menu(3));
        contents.add(new Setting(2));
        contents.add(new About(1));
        contents.add(new Setup(2));
        contents.add(new Game(2));
        contents.add(new Result(2));

        // setting up next and before content
        contents.get(0).addContent(contents.get(1)); 
        contents.get(1).insertContent(contents.get(2)); 
        contents.get(1).insertContent(contents.get(3)); 
        contents.get(1).insertContent(contents.get(4)); 
        contents.get(4).addContent(contents.get(5));
        contents.get(5).insertContent(contents.get(2));
        contents.get(5).insertContent(contents.get(6));
        contents.get(6).addContent(contents.get(1));

        // doing instantiate action event controller
        for(Content content : contents)
        {
            if(content == null) break;
            content.instantiateHandler();
        }     

        Music.update();
        Music.play();   
        frame.setVisible(true);
    }

    public static void setContent(Container content)
    {   
        frame.setContentPane(content);
        frame.revalidate();
        frame.repaint();
    }

    public static boolean gameIsRunning()
    {
        return running;
    }

    public static void setGameRunning(boolean value)
    {
        running = value;
    }
}

