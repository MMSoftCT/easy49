/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import inputfields.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author amederake
 */
public class LoginDialog extends JDialog implements FocusListener
{

    private LabeldTextField name;
    private LabeldTextField vorname;
    private LabeldDateInput g_datum;
    private JButton send;
    private JButton cancel;
    
    public LoginDialog()
    {
        initComp();
    }
    
    // intialize dialog objects
    private void initComp()
    {
        this.setLayout(new BorderLayout(0,5));
        JPanel main = new JPanel();
        main.setLayout(new GridLayout(5,1,5,5));
        vorname = new LabeldTextField("Vorname:");
        vorname.setText("");
        vorname.TextField().addFocusListener(this);
        name = new LabeldTextField("Name:");
        name.TextField().addFocusListener(this);
        name.setText("");
        g_datum = new LabeldDateInput("Geburtsdatum:");
       
        JLabel lblTitel = new JLabel("Easy49 Login");
        lblTitel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitel.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitel.setHorizontalTextPosition(SwingConstants.CENTER);
        Dimension dim = g_datum.getPreferredSize();
        main.setPreferredSize(new Dimension( (int) dim.getWidth() + 50, (int) (dim.getHeight() * 5) + 3*5));
        
        main.add(lblTitel);
        main.add(new JSeparator());
        main.add(vorname);
        main.add(name);
        main.add(g_datum);

        
        JPanel control = new JPanel();
        send = new JButton("Anmelden");
        send.setEnabled(false);
        cancel = new JButton("Abbrechen");
        control.setLayout(new GridLayout(1,2,5,5));
        control.add(send);
        control.add(cancel);

        this.add(main,BorderLayout.NORTH);
        this.add(new JSeparator(), BorderLayout.CENTER);
        this.add(control,BorderLayout.SOUTH);
        
        SwingUtilities.getRootPane(send).setDefaultButton(send);
        
        this.pack();
    }
    
    public void setOkBtnAction(ActionListener l)
    {
        send.addActionListener(l);
    }
    
    public void setCanelBtnAction(ActionListener l)
    {
        cancel.addActionListener(l);
    }
    
    public String getNachname()
    {
        return name.getText();
    }
    
    public String getVorname()
    {
        return vorname.getText();
    }
    
    public Calendar getDatum()
    {
        return g_datum.getDate();
    }

    @Override
    public void focusGained(FocusEvent fe)
    {
        
    }

    @Override
    public void focusLost(FocusEvent fe)
    {
        send.setEnabled(!name.getText().equals("") && ! vorname.getText().equals(""));
    }
}
