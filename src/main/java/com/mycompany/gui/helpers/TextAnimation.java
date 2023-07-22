package com.mycompany.gui.helpers;

import javax.swing.JLabel;
import javax.swing.Timer;

public class TextAnimation
{
    private JLabel label;
    private String message;
    private Timer timer;
    private int delay;
    private int index;
    private boolean finish;

    public TextAnimation(JLabel label, String message)
    {
        this.label = label;
        this.message = message;
        this.timer = null;
        this.delay = 75;
        this.index = 0;
        finish = false;
    }

    public void startAnimate()
    {
        if (timer != null)
        {
            return;
        }
        
        index = 0; finish = false;
        label.setText("<HTML></HTML>");

        timer = new Timer(delay, (event) -> {
            if(index >= message.length())
            { stopAnimate(); return; }
            String display = message.substring(0, ++index);
            label.setText("<HTML>" + display + "</HTML>");
        });

        timer.start();
    }

    public void stopAnimate()
    {
        if(timer != null)
        {
            timer.stop();
        }
        
        timer = null;
        finish = true;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public boolean isFinish()
    {
        return finish;
    }
}
