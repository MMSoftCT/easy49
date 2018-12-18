/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

/**
 * tipp dialog with 49 checkboxes
 *
 * @author amederake
 */
public class TippDialog extends JDialog implements ItemListener
{

    private JCheckBox[] zahlen;
    private JButton send, rst, cancel;
    private int count = 0;
    private boolean enabled = true;
    private int[] tips;

    /**
     * constructor
     */
    public TippDialog()
    {
	tips = new int[6];
	// create panel for check boxes
	JPanel main = new JPanel();
	main.setLayout(new GridLayout(7, 7, 3, 3));
	// create panel for buttons
	JPanel controls = new JPanel();
	controls.setLayout(new GridLayout(1, 3, 2, 0));

	// calculate and set dimension for check box panel
	JCheckBox t = new JCheckBox("49");
	Dimension dim = t.getPreferredSize();
	main.setPreferredSize(new Dimension((int) (dim.getWidth() * 7) + 3 * 7, (int) (dim.getHeight() * 7) + 3 * 7));

	// create check boxes
	zahlen = new JCheckBox[49];
	for (int i = 0; i < 49; i++)
	{
	    zahlen[i] = new JCheckBox(String.valueOf(i + 1));
	    zahlen[i].addItemListener(this);
	    zahlen[i].setBackground(Color.red);
	    zahlen[i].setForeground(Color.white);
	    main.add(zahlen[i]);
	}

	// create buttons an add to control panel
	send = new JButton("Bestätigen");
	send.setEnabled(false);

	rst = new JButton("Zurücksetzen");
	rst.addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		reset();
	    }
	});
	rst.setEnabled(false);

	cancel = new JButton("Abbrechen");

	controls.add(send);
	controls.add(rst);
	controls.add(cancel);

	this.add(main, BorderLayout.NORTH);
	this.add(new JSeparator(), BorderLayout.CENTER);
	this.add(controls, BorderLayout.SOUTH);
	this.pack();
    }

    // listener setter for MVC-Architecture controller
    
    public void setSendBtnAction(ActionListener l)
    {
	send.addActionListener(l);
    }

    public void setCanelBtnAction(ActionListener l)
    {
	cancel.addActionListener(l);
    }

    // getter/setter
    
    /**
     * get selected numbers
     * 
     * @return int array with 6 numbers
     */
    public int[] getTip()
    {
	int[] ret = new int[6];
	int anz = 0;
	for (int i = 0; i < 49; i++)
	{
	    if (zahlen[i].isSelected())
	    {
		ret[anz] = i + 1;
		anz++;
	    }
	}
	return ret;
    }

    /**
     * set selected numbers
     * 
     * @param tip new array with nubers
     */
    public void setTip(int[] tip)
    {
	reset();
	tips = tip;
	for (int i = 0; i < 6; i++)
	{
	    zahlen[tips[i] + 1].setSelected(true);
	    count++;
	}
	send.setEnabled(true);
	enabled = false;

    }

    /**
     * reset all checkboxes
     */
    public void reset()
    {
	for (int i = 0; i < 49; i++)
	{
	    zahlen[i].setSelected(false);
	    count = 0;
	}
	rst.setEnabled(false);
    }

    /**
     * state listener for checkboxes
     * 
     * @param e Item Event
     */
    @Override
    public void itemStateChanged(ItemEvent e)
    {
	// check state
	if (e.getStateChange() == ItemEvent.SELECTED)
	{
	    count++;
	} else
	{
	    count--;
	}

	// set checkboxes 
	if (count == 6 && enabled)
	{
	    enabled = false;
	    for (int i = 0; i < 49; i++)
	    {
		if (!zahlen[i].isSelected())
		{
		    zahlen[i].setEnabled(enabled);
		}
	    }
	    send.setEnabled(true);
	} else if (count < 6 && !enabled)
	{
	    enabled = true;
	    for (int i = 0; i < 49; i++)
	    {
		if (!zahlen[i].isSelected())
		{
		    zahlen[i].setEnabled(enabled);
		}
	    }
	    send.setEnabled(false);
	}
	if (count > 0)
	{
	    rst.setEnabled(true);
	}
    }

}
