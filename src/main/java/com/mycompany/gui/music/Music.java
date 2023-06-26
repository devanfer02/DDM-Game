package com.mycompany.gui.music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import com.mycompany.gui.helpers.console;

import java.io.File;

public class Music 
{
    private static Clip btnSound;
    private static Clip current;

    private static String[] backsoundSongs;
    private static String[] battleSongs;
    private static String titleCurrent;
    private static int listSize;
    private static int index;
    private static float volume;
    public static boolean stop;

    public static void init() 
    {
        btnSound = openAudio("./src/songs/effects/BtnSound.wav");
        volume = 1f;

        backsoundSongs = loadSongs("./src/songs/backgrounds");
        battleSongs    = loadSongs("./src/songs/battles"); 

        listSize = backsoundSongs.length;
        index = ((int) (Math.random() * (listSize-1)) + 0);
        titleCurrent = backsoundSongs[index];
    }

    private static String[] loadSongs(String path) 
    {
        File dir = new File(path); int size = 0;
        String[] arrays = dir.list();
        if(arrays == null) 
        {
            console.log("Files not loaded");
            console.log("Make sure the GUI-GAME is root directory");
            return arrays;   
        }
        size = arrays.length;
        for(int i = 0; i < size; i++) 
        {
            arrays[i] = "" + path + "/" + arrays[i];
        }
        return arrays;
    }

    public static void play()
    {
        if(isEmpty())
        {
            console.log("Music not loaded");
            return;
        }
        if(!stop) 
        {
            current.start();
            setVolume(Music.volume);
            current.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public static void stop()
    {
        if(isEmpty())
        {
            console.log("Music not loaded");
            return;
        }

        current.stop();
    }

    public static void update()
    {
        if(isEmpty())
        {
            console.log("Music not loaded");
            return;
        }
        titleCurrent = backsoundSongs[index];
        current = openAudio(backsoundSongs[index]);
    }

    public static void playNext()
    {
        if(isEmpty())
        {
            console.log("Music not loaded");;
            return;
        }

        incrementCurrSong();
        stop();
        update();
        play();
    }

    public static void playPrev()
    {
        if(isEmpty())
        {
            console.log("Music not loaded");
            return;
        }

        decrementCurrSong();
        stop();
        update();
        play();
    }

    public static void playBattleSong(int index)
    {
        current.stop();
        current = openAudio(battleSongs[index]);
        titleCurrent = battleSongs[index];
        play();
    }

    public static float getVolume()
    {
        FloatControl gainControl = (FloatControl) current.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public static void setVolume(float volume)
    {
        if(volume < 0f || volume > 1f)
            return;

        FloatControl gainControl = (FloatControl) current.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
        Music.volume = volume;
    }

    public static String getCurrentSong()
    {
        int firstIndex  = titleCurrent.indexOf("-") + 1;
        int secondIndex = titleCurrent.indexOf("wav") - 1;
        return titleCurrent.substring(firstIndex, secondIndex);
    }

    public static boolean isEmpty()
    {
        return (backsoundSongs == null ? true : false);
    }

    public static boolean notFinish()
    {
        return current.getMicrosecondLength() != current.getMicrosecondPosition(); 
    }
    
    private static void incrementCurrSong() 
    {
        index = ++index % listSize;
    }

    private static void decrementCurrSong()
    {
        index = (index - 1 < 0 ? listSize - 1 : index - 1);
    }

    public static void playButtonSound()
    {
        btnSound.setFramePosition(0);
        btnSound.start();
    }

    public static void playWinSound()
    {
        stop();
        current = openAudio("./src/songs/effects/Won-Winlandia.wav");
        play();
    }

    public static void playLoseSound()
    { 
        stop();
        current = openAudio("./src/songs/effects/Lose-Regression.wav");
        play();
    }

    private static Clip openAudio(String pathSong)
    {
        AudioInputStream audioStream;
        File file;
        Clip clip = null;
        try { 
            file = new File(pathSong);
            audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream); 
        } catch (Exception e) {
            console.log("Error : " + e.getMessage()); 
        }
        return clip;
    }
}
