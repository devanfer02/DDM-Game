package com.mycompany.gui.games.contents;

import java.awt.Container;

import javax.swing.JPanel;

public abstract class Content 
{
    private Content[] contents;
    protected JPanel[] panels;
    protected Container cont;    
    public final int SIZE;
    private int index;

    public Content(int size)
    {
        this.SIZE = size; 
        contents = new Content[size];
        index = 0;
    }

    public abstract void instantiateHandler();

    public Container getContent()
    {
        return cont;
    }

    public void setPanels(JPanel[] panels)
    {
        this.panels = panels;
    }   

    public Content[] getContents() 
    {
        return contents;
    }

    public void addContent(Content content)
    {
        if (index == SIZE) return;
        contents[index++] = content;
    }

    public void insertContent(Content content)
    {
        this.addContent(content);
        content.addContent(this);
    }

    public void setVisible(boolean value)
    {
        for(JPanel panel : panels)
        {
            panel.setVisible(value);
        }
    }
}
