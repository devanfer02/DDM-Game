package com.mycompany.gui.helpers;

import javax.swing.text.AttributeSet;
import javax.swing.text.DocumentFilter;

public class LengthFilter extends DocumentFilter 
{
    private int maxLength;

    public LengthFilter(int maxLength) 
    {
        this.maxLength = maxLength;
    }

    public void insertString(FilterBypass filterBypass, int offset, String text, AttributeSet attrs) 
    {
        if ((filterBypass.getDocument().getLength() + text.length()) <= maxLength) 
        {
            try {
                super.insertString(filterBypass, offset, text, attrs);
            } catch (Exception error) {
                console.log(error.getMessage());
            }   
        }
    }

    public void replace(FilterBypass filterBypass, int offset, int length, String text, AttributeSet attrs) 
    {
        if ((filterBypass.getDocument().getLength() - length + text.length()) <= maxLength) 
        {
            try {
                super.replace(filterBypass, offset, length, text, attrs);
            } catch (Exception error) {
                console.log(error.getMessage());
            }
            
        }
    }    
}
