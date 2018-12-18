/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * panel to display tipps an manage this
 * 
 * @author amederake
 */
public class TippReihe extends JPanel
{

    // class variables
    private JTextField[] zahlen;    // TextField array für Tipp zahlen
    private JLabel ergebnis;        // Label für das Ziehungsergebnis
    private JButton chng;           // Zahlen ändern/auswählen
    private JButton rand;           // Zahlen zufällig wählen

    /** 
     * constuctor
     * @param id id of this object
     */
    public TippReihe(int id)
    {
        this(new int[6], id);
    }

    /**
     *
     * constructor
     *
     * @param tipp array with numbers
     * @param id   id of this object
     */
    public TippReihe(int[] tipp, int id)
    {
        this.setLayout(new BorderLayout());
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(1, 6, 0, 5));
        JPanel control = new JPanel();

        zahlen = new JTextField[6];
        zahlen[0] = new JTextField("49");
        Dimension dim = zahlen[0].getPreferredSize();
        grid.setPreferredSize(new Dimension((int) (dim.getWidth() * 6) + 3 * 5, (int) (dim.getHeight())));

        for (int i = 0; i < 6; i++)
        {
            if (tipp[i] == 0)
            {
                zahlen[i] = new JTextField("");
            } else
            {
                zahlen[i] = new JTextField(String.valueOf(tipp[i]));
            }
            zahlen[i].setEditable(false);
            grid.add(zahlen[i]);
        }

        chng = new JButton();
        chng.setActionCommand(String.valueOf(id));
        setBtnTxt();
        

        rand = new JButton("Zufällige");
        rand.setActionCommand(String.valueOf(id));
        
        ergebnis = new JLabel("");
        //ergebnis.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        GroupLayout ctrl = new GroupLayout(control);
        control.setLayout(ctrl);
        ctrl.setHorizontalGroup(
                ctrl.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(ctrl.createSequentialGroup()
                                .addComponent(chng, GroupLayout.PREFERRED_SIZE, chng.getPreferredSize().width, GroupLayout.PREFERRED_SIZE)
                                .addGap(2)
                                .addComponent(rand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(3)
                                .addComponent(ergebnis, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        )
        );
        ctrl.setVerticalGroup(
                ctrl.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(ctrl.createSequentialGroup()
                                .addGroup(ctrl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(chng, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ergebnis, GroupLayout.PREFERRED_SIZE, (int) rand.getPreferredSize().getHeight(), GroupLayout.PREFERRED_SIZE)
                                )
                        )
        );

        this.add(grid, BorderLayout.WEST);
        this.add(control, BorderLayout.EAST);
    }

    /**
     * return array with numbers
     *
     * @return
     */
    public int[] getZahlen()
    {
        int[] ret = new int[6];
        for (int i = 0; i < 6; i++)
        {
            if (zahlen[i].getText().equals(""))
            {
                ret[i] = 0;
            } else
            {
                ret[i] = Integer.parseInt(zahlen[i].getText());
            }
        }
        return ret;
    }

    /**
     * set numbers in textfields
     *
     * @param tip
     */
    public void setZahlen(int[] tip)
    {
        for (int i = 0; i < 6; i++)
        {
            if (tip[i] == 0)
            {
                zahlen[i].setText("");
            } else
            {
                zahlen[i].setText(String.valueOf(tip[i]));
            }
        }
    }

    /**
     * set Ziehungsergebnis
     * 
     * @param erg new Ergebnis 
     */
    public void setErgebnis(String erg)
    {
        ergebnis.setText(erg);
    }
    
    /**
     * enable/disable this Object
     * 
     * @param en true/false
     */
    @Override
    public void setEnabled(boolean en)
    {
        super.setEnabled(en);
        chng.setEnabled(en);
        rand.setEnabled(en);
    }

    /**
     * set the action listener for change button
     *
     * @param l new action listener
     */
    public void setCangeBtnListener(ActionListener l)
    {
        chng.addActionListener(l);
    }

    /**
     * set the action listener for random button
     *
     * @param l
     */
    public void setRandBtnListener(ActionListener l)
    {
        rand.addActionListener(l);
    }

    /**
     * change button text
     */
    public void setBtnTxt()
    {
        if (zahlen[0].getText().equals(""))
        {
            chng.setText("Auswählen");
        } else
        {
            chng.setText("Ändern");
        }
    }
}
