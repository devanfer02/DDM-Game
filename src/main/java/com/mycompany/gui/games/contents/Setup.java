package com.mycompany.gui.games.contents;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.ToolTipManager;
import javax.swing.text.AbstractDocument;

import com.mycompany.gui.games.Difficulty;
import com.mycompany.gui.games.Engine;
import com.mycompany.gui.games.controllers.ChoiceController;
import com.mycompany.gui.games.controllers.Controller;
import com.mycompany.gui.games.controllers.NodeController;
import com.mycompany.gui.helpers.TextAnimation;
import com.mycompany.gui.helpers.LengthFilter;
import com.mycompany.gui.helpers.SwingUtil;

public class Setup extends Content implements Choices
{
    private JTextField formField;

    private JLabel titleLabel;
    private JLabel selectLabel;
    private JLabel informLabel;

    private JButton easyButton;
    private JButton normalButton;
    private JButton hardButton;
    private JButton extremeButton;
    private JButton startButton;
    private JButton backButton;

    // for animation
    private TextAnimation animate1;
    private TextAnimation animate2;
    private String[] message1;
    private int index;

    public Setup(int size)    
    {
        super(size);
        cont = Engine.frame.getContentPane();

        JPanel configPanel  = new JPanel(new GridLayout(1, 4,10,0));
        JPanel buttonsPanel = new JPanel(new GridLayout(2,1,0,10));
        JPanel informPanel  = new JPanel(new GridLayout(1, 1));
        JPanel titlePanel   = new JPanel();
        JPanel formPanel    = new JPanel();
        JPanel infoPanel    = new JPanel();

        titleLabel  = new JLabel("SETUP");
        selectLabel = new JLabel("<HTML></HTML>");
        informLabel = new JLabel("<HTML></HTML>");

        formField   = new JTextField(12);

        easyButton    = new JButton("EASY");
        normalButton  = new JButton("NORMAL");
        hardButton    = new JButton("HARD");
        extremeButton = new JButton("EXTREME");
        startButton   = new JButton("START GAME");
        backButton    = new JButton("BACK MENU");

        message1    = new String[5]; index = 0;
        message1[0] = "By what name do you bear ?";
        message1[1] = "Speak again, i can't hear you";
        message1[2] = "Did i hear something ?";
        message1[3] = "Can you speak a little louder";
        message1[4] = "This takes longer than i expect"; 

        animate1 = new TextAnimation(selectLabel, message1[0]);
        animate2 = new TextAnimation(informLabel, 
        "And what level of challenge shall your path tread upon ?");


        // limiting character input to 15 characters only
        AbstractDocument document = (AbstractDocument) formField.getDocument();
        document.setDocumentFilter(new LengthFilter(15));

        // making sure user knows to press enter
        ToolTipManager.sharedInstance().setInitialDelay(0);
        formField.setToolTipText("Press enter");
        JToolTip toolTip = formField.createToolTip();
        toolTip.setTipText(formField.getToolTipText());
        toolTip.setVisible(true);

        titlePanel.   setBounds(197, 75, 600, 90);
        informPanel.  setBounds(380,150,500,90);
        formPanel.    setBounds(210,220,600,100);
        configPanel.  setBounds(210,370,600,54);
        buttonsPanel. setBounds(380,470,260,104);
        infoPanel.    setBounds(200, 310,600,200);
        formField.    setBounds(210,280,440,100); 

        SwingUtil.setInvisible(titlePanel);
        SwingUtil.setInvisible(informPanel);
        SwingUtil.setInvisible(formPanel); 
        SwingUtil.setInvisible(configPanel);
        SwingUtil.setInvisible(buttonsPanel);
        SwingUtil.setInvisible(infoPanel);
        SwingUtil.setInvisible(formField);

        SwingUtil.setFont(titleLabel, 70, Color.WHITE, Font.BOLD);        
        SwingUtil.setFont(formField, 30, Color.WHITE, Font.BOLD);
        SwingUtil.setFont(selectLabel, 22, Color.WHITE, Font.BOLD); 
        SwingUtil.setFont(informLabel, 22, Color.WHITE, Font.BOLD);
        SwingUtil.setFont(easyButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(normalButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(hardButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(extremeButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(startButton, 22, Color.BLACK, Font.BOLD);
        SwingUtil.setFont(backButton, 22, Color.BLACK, Font.BOLD);

        SwingUtil.setButtonColor(easyButton    );
        SwingUtil.setButtonColor(normalButton  );
        SwingUtil.setButtonColor(hardButton    );
        SwingUtil.setButtonColor(extremeButton );
        SwingUtil.setButtonColor(startButton   );
        SwingUtil.setButtonColor(backButton    );

        titlePanel.   add(titleLabel); 
        informPanel.  add(selectLabel);
        infoPanel.    add(informLabel);
        formPanel.    add(formField);
        configPanel.  add(easyButton);
        configPanel.  add(normalButton);
        configPanel.  add(hardButton);
        configPanel.  add(extremeButton); 
        buttonsPanel. add(startButton);
        buttonsPanel. add(backButton);

        cont.add(titlePanel);
        cont.add(informPanel);
        cont.add(formPanel);
        cont.add(infoPanel);
        cont.add(configPanel);
        cont.add(buttonsPanel);

        setPanels(new JPanel[]{titlePanel, informPanel, formPanel, configPanel, buttonsPanel, infoPanel}); 

        startButton.setVisible(false);
        setVisible(false);
    }

    public void run()
    {
        animate1.startAnimate();
    }

    public void stop()
    {
        animate1.stopAnimate();
        animate2.stopAnimate();
    }

    public void setDefault()
    {
        repaintAll(); index = 0;
        animate1.setMessage(message1[index]);
        informLabel.setText("<HTML></HTML>");
        formField.setText("");
        panels[3].setVisible(false);
        startButton.setVisible(false);
        Engine.level = null;
    }

    public void instantiateHandler()
    {
        // ActionClasses
        Controller selectHandler = new ChoiceController(this);

        easyButton.    addActionListener(selectHandler);
        normalButton.  addActionListener(selectHandler);
        hardButton.    addActionListener(selectHandler);
        extremeButton. addActionListener(selectHandler);
        startButton.   addActionListener(new NodeController(this,1));
        backButton.    addActionListener(new NodeController(this,0));
        formField.     addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event)
            {
                int code = event.getKeyCode();
                boolean isEnter = code == KeyEvent.VK_ENTER;
                if(!isEnter) 
                    return;
                if (isEnter && SwingUtil.textFieldEmpty(formField))
                {
                    animate1.setMessage(message1[++index % message1.length]);
                    animate1.startAnimate();
                    return;
                }
                panels[1].setVisible(true);
                panels[3].setVisible(true);
                animate2.startAnimate();
            }
        });
    }

    public void repaintAll()
    {
        easyButton.    setBackground(Color.LIGHT_GRAY);
        normalButton.  setBackground(Color.LIGHT_GRAY);
        hardButton.    setBackground(Color.LIGHT_GRAY);
        extremeButton. setBackground(Color.LIGHT_GRAY);
    }

    public void actionHandling(String action)
    {
        repaintAll();
        switch (action)
        {
            case "EASY" -> 
            {
                Engine.level = Difficulty.EASY;   
                easyButton.setBackground(Color.GREEN);
            }
            case "NORMAL" -> 
            {
                Engine.level = Difficulty.NORMAL;
                normalButton.setBackground(Color.YELLOW);
            }
            case "HARD" -> 
            {
                Engine.level = Difficulty.HARD;
                hardButton.setBackground(Color.ORANGE);
            }
            case "EXTREME" -> 
            {
                Engine.level = Difficulty.EXTREME;
                extremeButton.setBackground(Color.RED);
            }
        }
        startButton.setVisible(true);
    }

    public boolean isFinishAnimate()
    {
        return animate1.isFinish();
    }

    public JLabel getSelectLabel()
    {
        return selectLabel;
    }

    public JPanel getConfigPanel()
    {
        return panels[3]; 
    }
}
