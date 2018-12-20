/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import inputfields.LabeldTextField;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import Spieler.*;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;

/**
 *
 * @author amederake
 */
public class View extends JFrame
{
    // class variales
    
    // UI related
    private JButton btnEinsatz;
    private JButton btnEinzahlen;
    private JButton btnEnde;
    private JButton btnZiehung;
    private JButton btnRegeln;
    private JLabel lblTipreihen;
    private JLabel lblTitel;
    private JPanel titlePanel;
    private JPanel mainPanel;
    private JPanel einsatzPanel;
    private JPanel guthabenPanel;
    private JPanel tippHPanel;
    private JPanel pTipreihen;
    private JPanel controllPanel;
    private JPanel statusPanel;
    private JSpinner spTipreihen;
    private JLabel tEuro1;
    private JLabel tEuro2;
    private JLabel playerCount;
    private JLabel jackpott;
    private LabeldTextField txtName;
    private LabeldTextField txtEinsatz;
    private LabeldTextField txtGuthaben;
    private TippReihe[] tipps;
    
    // internal fields
    private Benutzer SPIELER;
    private int players;
    private int tippreihenCount;

    /**
     * constructor
     */
    public View()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        initComponents();
    }

    /**
     * initalize gui objects
     */
    private void initComponents()
    {
        SPIELER = new Benutzer();
        tippreihenCount = 0;
        
        // create Tippreihe array
        tipps = new TippReihe[5];
        for (int i = 0; i < 5; i++)
        {
            tipps[i] = new TippReihe(i);
            tipps[i].setEnabled(false);
        }
        setTippreiheEnabled(0, true);

        lblTitel = new JLabel();
        titlePanel = new JPanel();
        mainPanel = new JPanel();
        einsatzPanel = new JPanel();
        guthabenPanel = new JPanel();
        tippHPanel = new JPanel();
        pTipreihen = new JPanel();
        controllPanel = new JPanel();
        statusPanel = new JPanel();
        playerCount = new JLabel();
        jackpott = new JLabel();
        txtName = new LabeldTextField();
        txtEinsatz = new LabeldTextField();
        tEuro1 = new JLabel();
        btnEinsatz = new JButton();
        lblTipreihen = new JLabel();
        spTipreihen = new JSpinner();
        txtGuthaben = new LabeldTextField();
        btnEinzahlen = new JButton();
        tEuro2 = new JLabel();
        btnZiehung = new JButton();
        btnEnde = new JButton();
        btnRegeln = new JButton();

        // disable Ziehung button
        btnZiehung.setEnabled(false);

        lblTitel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTitel.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitel.setText("Easy 49");
        lblTitel.setHorizontalTextPosition(SwingConstants.CENTER);

        txtName.setText("Name");
        txtName.TextField().setEditable(false);
        txtName.setFieldWidth(200);
        txtName.setLabel("Benutzer:");
        txtName.setLblAlign(SwingConstants.LEFT);
        txtName.setLblWidth(60);

        txtEinsatz.setText("0.00");
        txtEinsatz.TextField().setEditable(false);
        txtEinsatz.setFieldWidth(50);
        txtEinsatz.TextField().setHorizontalAlignment(SwingConstants.RIGHT);
        txtEinsatz.setLabel("Einsatz:");
        txtEinsatz.setLblWidth(60);
        txtEinsatz.setLblAlign(SwingConstants.LEFT);

        tEuro1.setText("€");

        txtGuthaben.setText("0.00");
        txtGuthaben.TextField().setEditable(false);
        txtGuthaben.setFieldWidth(50);
        txtGuthaben.TextField().setHorizontalAlignment(SwingConstants.RIGHT);
        txtGuthaben.setLabel("Guthaben:");
        txtGuthaben.setLblWidth(60);
        txtGuthaben.setLblAlign(SwingConstants.LEFT);

        tEuro2.setText("€");

        btnEinsatz.setText("Einsatz");

        btnEinzahlen.setText("Einzahlen");

        btnZiehung.setText("Ziehung starten");

        btnEnde.setText("Spiel beenden");

        btnRegeln.setText("Spielregeln");

        lblTipreihen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTipreihen.setLabelFor(spTipreihen);
        lblTipreihen.setText("Tipreihen");

        SpinnerModel model = new SpinnerNumberModel(1, 1, 5, 1);
        spTipreihen.setModel(model);

        playerCount.setText("Aktuelle Spieleranzahl: 1000");

        jackpott.setText("Aktueller Jackpott: 500.000,00€");

        // panel for title
        GroupLayout pTitle = new GroupLayout((titlePanel));
        titlePanel.setLayout(pTitle);

        pTitle.setHorizontalGroup(
                pTitle.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pTitle.createSequentialGroup()
                                .addComponent(lblTitel, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                        )
        );

        pTitle.setVerticalGroup(
                pTitle.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pTitle.createSequentialGroup()
                                .addComponent(lblTitel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
        );

        // panel for Einsatz
        GroupLayout pEinsatz = new GroupLayout(einsatzPanel);
        einsatzPanel.setLayout(pEinsatz);

        // horizontal group
        pEinsatz.setHorizontalGroup(
                pEinsatz.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pEinsatz.createSequentialGroup()
                                .addComponent(txtEinsatz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(tEuro1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(38)
                                .addComponent(btnEinsatz, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        )
        );

        // vertical group
        pEinsatz.setVerticalGroup(
                pEinsatz.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pEinsatz.createSequentialGroup()
                                .addGroup(pEinsatz.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(txtEinsatz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tEuro1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnEinsatz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                        )
        );

        // panel for Guthaben
        GroupLayout pGuthaben = new GroupLayout(guthabenPanel);
        guthabenPanel.setLayout(pGuthaben);

        // horizontal group
        pGuthaben.setHorizontalGroup(
                pGuthaben.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pGuthaben.createSequentialGroup()
                                .addComponent(txtGuthaben, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(tEuro2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(38)
                                .addComponent(btnEinzahlen, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        )
        );

        // vertical group
        pGuthaben.setVerticalGroup(
                pGuthaben.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pGuthaben.createSequentialGroup()
                                .addGroup(pGuthaben.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(txtGuthaben, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tEuro2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnEinzahlen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                        )
        );

        // panel Tippreihen header
        GroupLayout pTippH = new GroupLayout(tippHPanel);
        tippHPanel.setLayout(pTippH);

        pTippH.setHorizontalGroup(
                pTippH.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(pTippH.createSequentialGroup()
                                .addGap(100)
                                .addComponent(lblTipreihen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(spTipreihen, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        )
        );

        pTippH.setVerticalGroup(
                pTippH.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pTippH.createSequentialGroup()
                                .addGroup(pTippH.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTipreihen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spTipreihen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                        )
        );

        // panel for tippreihen
        GroupLayout pTipreihenLayout = new GroupLayout(pTipreihen);
        pTipreihen.setLayout(pTipreihenLayout);

        // horizontal group
        pTipreihenLayout.setHorizontalGroup(
                pTipreihenLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pTipreihenLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pTipreihenLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(tipps[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tipps[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tipps[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tipps[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tipps[4], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addContainerGap())
        );

        // vertical group
        pTipreihenLayout.setVerticalGroup(
                pTipreihenLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pTipreihenLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tipps[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(tipps[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(tipps[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(tipps[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(tipps[4], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
        );

        // panel for buttons
        GroupLayout pControl = new GroupLayout(controllPanel);
        controllPanel.setLayout(pControl);

        pControl.setHorizontalGroup(
                pControl.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pControl.createSequentialGroup()
                                .addGap(15)
                                .addComponent(btnZiehung, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(btnEnde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(btnRegeln, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
        );

        pControl.setVerticalGroup(
                pControl.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pControl.createSequentialGroup()
                                .addGroup(pControl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(btnZiehung, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnEnde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnRegeln, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        )
        );

        // main panel Layout
        GroupLayout mainLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainLayout);

        // horizontal group
        mainLayout.setHorizontalGroup(
                mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainLayout.createSequentialGroup()
                                .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(mainLayout.createSequentialGroup()
                                .addComponent(guthabenPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(mainLayout.createSequentialGroup()
                                .addComponent(tippHPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(mainLayout.createSequentialGroup()
                                .addComponent(pTipreihen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(mainLayout.createSequentialGroup()
                                .addComponent(einsatzPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(mainLayout.createSequentialGroup()
                                .addComponent(controllPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
        );

        // vertical group
        mainLayout.setVerticalGroup(
                mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(guthabenPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(tippHPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(pTipreihen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(einsatzPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(controllPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                        )
        );

        // status panel
        GroupLayout status = new GroupLayout(statusPanel);
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        statusPanel.setLayout(status);
        
        int height = (int) jackpott.getPreferredSize().getHeight() + 5;
        int mainWidth = (int) mainPanel.getPreferredSize().getWidth()- 10;
        statusPanel.setPreferredSize(new Dimension(mainWidth,height));
        
        // horizontal group
        status.setHorizontalGroup(
                status.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(status.createSequentialGroup()
                                .addComponent(playerCount, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(jackpott, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
        );

        // vertical group
        status.setVerticalGroup(
                status.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(status.createSequentialGroup()
                                .addGroup(status.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(playerCount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jackpott, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                        )
        );

        // global layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        // horizontal group
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(statusPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
        );
        // vertical group
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
        );

        pack();
    }

    // listener setter
    
    /**
     * set action listener for Ziehung button
     *
     * @param l new action listener
     */
    public void setZiehungAction(ActionListener l)
    {
        this.btnZiehung.addActionListener(l);
    }

    /**
     * set action listener for Ende button
     *
     * @param l new action listener
     */
    public void setEndeAction(ActionListener l)
    {
        this.btnEnde.addActionListener(l);
    }

    /**
     * set action lister for Spielregeln button
     *
     * @param l
     */
    public void setRegelnAction(ActionListener l)
    {
        this.btnRegeln.addActionListener(l);
    }

    /**
     * set action listener for Einsatz button
     *
     * @param l new action listener
     */
    public void setEinsatzAction(ActionListener l)
    {
        this.btnEinsatz.addActionListener(l);
    }

    /**
     * set action listener for Einzahlen button
     *
     * @param l new action listener
     */
    public void setEinzahlenAction(ActionListener l)
    {
        this.btnEinzahlen.addActionListener(l);
    }

    /**
     * set change listener for tippreihen spinner
     *
     * @param l new change listener
     */
    public void setSpinnerListener(ChangeListener l)
    {
        this.spTipreihen.addChangeListener(l);
    }

    /**
     * set action listener for tippreihe
     *
     * @param nr number of tippreihe to use
     * @param a  new actionlistener for change button
     * @param b  new action listener for random button
     */
    public void setTippreihenListener(int nr, ActionListener a, ActionListener b)
    {
        if (nr < 5)
        {
            this.tipps[nr].setCangeBtnListener(a);
            this.tipps[nr].setRandBtnListener(b);
        }
    }

    // setter/getter
    
    /**
     * enabler for tippreihe
     *
     * @param nr number of tippreihe
     * @param en true or false
     */
    public void setTippreiheEnabled(int nr, boolean en)
    {
        if (nr >= 0 && nr < 5)
        {
            tipps[nr].setEnabled(en);
            if (en)
            {
                tippreihenCount++;
            } else
            {
                tippreihenCount--;
            }
        }
    }

    /**
     * set Tippreihen numers
     * @param nr number of Tippreihe
     * @param tip new numbers
     */
    public void setTippreihenZahlen(int nr, int[] tip)
    {
        tipps[nr].setZahlen(tip);
        tipps[nr].setBtnTxt();
        getPlayer().setTIPPREIHE(nr, tip);
        UpdateUI();
    }
    
    public void setTippreihenGewinn(int nr,String gew)
    {
        tipps[nr].setErgebnis(gew);
    }

    /**
     * check if a Tippreihe is enabled
     *
     * @param nr number of Tippreihe
     * @return true/false
     */
    public boolean isTippreiheEnabled(int nr)
    {
        if (nr > 0 && nr < 5)
        {
            return tipps[nr].isEnabled();
        }
        return false;
    }

    /**
     * return the count of enabled Tippreiehen
     *
     * @return number of enabled Tippreihen
     */
    public int getTippreiheCount()
    {
        return tippreihenCount;
    }

    /**
     * set the curren player
     *
     * @param nr index of the current player
     */
    public void setPlayerCount(int nr)
    {
        players = nr;
        UpdateUI();
    }

    /**
     * returns the current player object
     *
     * @return Benutzer object
     */
    public Benutzer getPlayer()
    {
        return SPIELER;
    }

    /**
     * set jpSize;
     *
     * @param bestand new jpSize
     */
    public void setJackpott(double bestand)
    {
        jackpott.setText("Aktueller Jackpott: " + String.format("%.2f", bestand) + " €");
    }

    /**
     * add a new plaer to SPIELER
     *
     * @param benutzer new player
     */
    public void setSpieler(Benutzer benutzer)
    {
        SPIELER = benutzer;
    }

    // UI related methodes
    
    /**
     * udate the user interface
     */
    public void UpdateUI()
    {
        Benutzer benutzer = getPlayer();

        txtName.setText(benutzer.getVORNAME() + " " + benutzer.getNAME());
        txtEinsatz.setText(String.valueOf(benutzer.getEINSATZ()));
        txtGuthaben.setText(String.valueOf(benutzer.getKONTOSTAND()));

        // enable/disable Ziehung button
        if (benutzer.getEINSATZ() == 0.0 || benutzer.getTIPPREIHE(0)[0] == 0)
        {
            btnZiehung.setEnabled(false);
        } else
        {
            btnZiehung.setEnabled(true);
        }

        // enable/disable Einsatz button
        if (benutzer.getKONTOSTAND() == 0.0)
        {
            btnEinsatz.setEnabled(false);
        } else
        {
            btnEinsatz.setEnabled(true);
        }

        // enable/disable Tippreihen
        for (int i = 4; i >= 1; i--)
        {
            // Tippreihe is enabled but no numbers
            if (tipps[i].isEnabled() && tipps[i].getZahlen()[0] == 0)
            {
                // disable Tippreihe
                if ((Integer) spTipreihen.getValue() == i + 1)
                {
                    spTipreihen.setValue(i);
                }
            }
        }
        playerCount.setText("Aktuelle Spieler: " + players);
        
    }

}
