/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Spieler.*;
import Spielsteuerung.*;
import inputfields.LabeldDoubleInput;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * MVC-Architektur Controller
 *
 * @author amederake
 */
public class Spiel
{
    // class variables
    private View main;                  // Hauptfenster
    private final LoginDialog login;    // the login
    private final TippDialog tipp;      // the tipp selection dialog
    private final Generator gen;        // interface to generator class
    private final Kasse cash;           // interface to Kasse
    private final Auswertung calc;      // interface to Auswertung
    private int activeTipp;		// store the active tippreihe

    /**
     * constructor for MVC-Architektur Controller
     */
    public Spiel()
    {
        main = new View();
        Dimension dim = main.getPreferredSize();
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        int posX = (int) (scrSize.getWidth() - dim.getWidth()) / 2;
        int posY = (int) (scrSize.getHeight() - dim.getHeight()) / 2;
        main.setLocation(posX, posY);
        BufferedImage img = null;
        try
        {
            URL resource = getClass().getResource("easy49.png");
            img = ImageIO.read(resource);
            main.setIconImage(img);
        } catch (IOException iOException)
        {
        }

        login = new LoginDialog();
        login.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        login.setLocationRelativeTo(null);
        login.setIconImage(img);
        login.setAlwaysOnTop(true);

        tipp = new TippDialog();
        tipp.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        tipp.setLocationRelativeTo(main);
        tipp.setIconImage(img);
        activeTipp = -1;

        gen = new Generator();
        cash = new Kasse();
        cash.setBESTAND(5000.00);
        cash.setSICHERUNGSBESTAND(5000.00);
        calc = new Auswertung();

        setListener();
        createBots(20);
    }

    /**
     * start application with a login
     */
    public void run()
    {
        login.setVisible(true);
    }

    /**
     * create virtual user for simulation
     */
    private void createBots(int count)
    {
        for (int i = 0; i < count; i++)
        {
            main.addSpieler(new Benutzer(String.valueOf(i), String.valueOf(i), new Date()));
            main.getPlayer().setKONTOSTAND(50.0);
            //double eins = rand.nextInt(9) + rand.nextInt(9) / 10;
            //main.getPlayer().setEINSATZ(eins);
            main.getPlayer().setTIPPREIHE(0, gen.zufallsZahlenAusgabe());
            //cash.einzahlung(eins);
            //main.setJackpott(cash.getBESTAND());
        }
    }

    /**
     * check if new user is 18 years old
     *
     * @param d date of bith
     * @return true/false
     */
    private boolean checkAlter(Calendar d)
    {
        Calendar min = Calendar.getInstance();
        min.add(Calendar.YEAR, -18);
        return min.get(Calendar.YEAR) >= d.get(Calendar.YEAR);
    }

    /**
     * set all listener
     */
    private void setListener()
    {
        // set action listener for main window buttons
        main.setEndeAction(new MainEndeAction());
        main.setZiehungAction(new MainZiehungAction());
        main.setRegelnAction(new MainRegelnAction());
        main.setEinsatzAction(new MainEinsatzAction());
        main.setEinzahlenAction(new MainEinzahlenAction());
        main.setSpinnerListener(new MainSpinnerListener());
        main.setTippreihenListener(0, new MainTippreihenCangeAction(), new MainTippreihenRandAction());
        main.setTippreihenListener(1, new MainTippreihenCangeAction(), new MainTippreihenRandAction());
        main.setTippreihenListener(2, new MainTippreihenCangeAction(), new MainTippreihenRandAction());
        main.setTippreihenListener(3, new MainTippreihenCangeAction(), new MainTippreihenRandAction());
        main.setTippreihenListener(4, new MainTippreihenCangeAction(), new MainTippreihenRandAction());

        // set action listener for login dialog
        login.setOkBtnAction(new LoginOkButtonAction());
        login.setCanelBtnAction(new LoginCanelButtonAction());

        // set action listener for TippDialog
        tipp.setCanelBtnAction(new TippCanelButtonAction());
        tipp.setSendBtnAction(new TippSendButtonAction());
    }

    // internal listener classes
    
    // view related listener
    
    /**
     * action listener for main Ende button
     */
    class MainEndeAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

    /**
     * action listener for Regeln button
     */
    class MainRegelnAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
           
        }

    }

    /**
     * action listener for Ziehung button
     */
    class MainZiehungAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            SecureRandom rand = new SecureRandom();

            // Gewinnzahlen ermitteln
            int[] gewinZ = gen.zufallsZahlenAusgabe();

            ArrayList<Benutzer> users = main.getPlayers();
            Benutzer current = main.getPlayer();

            users.forEach((b) ->
            {
                // zufälligen einsatz und tipp für simulation setzen
                if (b != current)
                {
                    b.setEINSATZ(Math.round((rand.nextInt(9) + rand.nextInt(9) / 10) * 100) / 100.0);
                    b.setTIPPREIHE(0, gen.zufallsZahlenAusgabe());
                }
                // Einzahlung und Kontostand aktualisieren
                cash.einzahlung(b.getEINSATZ() * b.getTIPPREIHEN().size());
                b.setKONTOSTAND(b.getKONTOSTAND() - (b.getEINSATZ() * b.getTIPPREIHEN().size()));
            });

            int eins = 0, zwei = 0, drei = 0, vier = 0, fuenf = 0, sechs = 0;
            ArrayList<int[]> tippR;

            // gewinnreihen ermitteln
            for (Benutzer b : users)
            {
                tippR = b.getTIPPREIHEN();
                for (int[] t : tippR)
                {
                    switch (calc.countGewinnReihen(t, gewinZ))
                    {
                        case 1:
                            eins++;
                            break;
                        case 2:
                            zwei++;
                            break;
                        case 3:
                            drei++;
                            break;
                        case 4:
                            vier++;
                            break;
                        case 5:
                            fuenf++;
                            break;
                        case 6:
                            sechs++;
                            break;
                    }
                }
            }

            // gewinn faktor errechnen
            int gewinner = (eins + zwei + drei + vier + fuenf + sechs);
            int gewinne = ((1 * eins) + (2 * zwei) + (3 * drei) + (4 * vier) + (5 * fuenf) + (6 * sechs));
            double faktor = (double) gewinner / (double) gewinne;
            faktor = (double) Math.round(faktor * 100) / 100.0;
            cash.setGEWINNFAKTOR(faktor);

            // gewinne auszahlen
            users.forEach((b) ->
            {
                if (b != current)
                {
                    b.setKONTOSTAND(b.getKONTOSTAND() + cash.auszahlung(calc.countGewinnReihen(b.getTIPPREIHE(0), gewinZ), b.getEINSATZ()));
                }
            });

            // gewinn für den aktuellen Benutzer feststellen
            tippR = current.getTIPPREIHEN();
            double gewinn = 0.0;
            for (int i = 0; i < tippR.size(); i++)
            {
                int r = calc.countGewinnReihen(tippR.get(i), gewinZ);
                double g = cash.auszahlung(r, current.getEINSATZ());
                gewinn += g;
                main.setTippreihenGewinn(i, String.valueOf(r) + " Richtige (" + String.format("%.2f", g) + "€)");
            }
            Object[] options =
            {
                "Ok"
            };
            if (gewinn > 0.0)
            {
                JPanel pnl = new JPanel();
                pnl.setLayout(new BoxLayout(pnl, BoxLayout.PAGE_AXIS));

                JLabel msg1 = new JLabel("Sie haben " + String.format("%.2f €", gewinn) + " gewonnen!");
                msg1.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel msg2 = new JLabel("Der Gewinn wurde Ihrem Konto gutgeschrieben!.");
                msg2.setAlignmentX(Component.CENTER_ALIGNMENT);

                pnl.add(Box.createRigidArea(new Dimension(0, 5)));
                pnl.add(msg1);
                pnl.add(msg2);
                pnl.add(Box.createRigidArea(new Dimension(0, 10)));

                JOptionPane.showOptionDialog(main,
                        pnl,
                        "Sie haben Gewonnen !",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]);
                current.setKONTOSTAND(current.getKONTOSTAND() + gewinn);
            } else
            {
                JOptionPane.showOptionDialog(main,
                        "Kein Gewinn bei diesem Spiel!",
                        "Sie haben nicht Gewonnen",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]);
            }
            current.setEINSATZ(0.0);
            main.setJackpott(cash.getBESTAND() + cash.getSICHERUNGSBESTAND());
            main.UpdateUI();
        }
    }

    /**
     * action listener for Einsatz button, show input dialog
     */
    class MainEinsatzAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            double old = main.getPlayer().getEINSATZ(); // save old Einsatz;

            // create Einsatz dialog
            JPanel pnl = new JPanel();
            pnl.setLayout(new BoxLayout(pnl, BoxLayout.PAGE_AXIS));

            JLabel msg1 = new JLabel("Geben Sie bitte Ihren Einsatz ein!");
            msg1.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel msg2 = new JLabel("( mindestens 0,5 €, maximal 10 €)");
            msg2.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel msg3 = new JLabel("Ihr Einsatz gilt pro Tippreihe");
            msg3.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel msg4 = new JLabel("und wird vor der Ziehung von Ihrem Guthaben abgezogen.");
            msg4.setAlignmentX(Component.CENTER_ALIGNMENT);

            LabeldDoubleInput input = new LabeldDoubleInput("IhrEinsatz: ");
            input.setValue(0.5);

            pnl.add(Box.createRigidArea(new Dimension(0, 5)));
            pnl.add(msg1);
            pnl.add(msg2);
            pnl.add(msg3);
            pnl.add(msg4);
            pnl.add(Box.createRigidArea(new Dimension(0, 10)));
            pnl.add(input);
            
            Object[] options = {"OK"};
            
            int okCxl = JOptionPane.showOptionDialog(main,
                    pnl,
                    "Ihr Einsatz",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (okCxl == JOptionPane.OK_OPTION)
            {
                double einsatz = input.getValue();
                if (einsatz < 0.5 || einsatz > 10.0)
                {
                    JOptionPane.showMessageDialog(main,
                            "Ihr Einsatz entspricht nicht den Vorgaben",
                            "Fehleingabe !",
                            JOptionPane.WARNING_MESSAGE);
                } else if (einsatz * main.getTippreiheCount() > main.getPlayer().getKONTOSTAND())
                {
                    JOptionPane.showMessageDialog(main,
                            "Ihr Guthaben reicht nicht aus!",
                            "Guthaben erhöhen !",
                            JOptionPane.WARNING_MESSAGE);
                } else
                {
                    main.getPlayer().setEINSATZ(einsatz);
                    main.UpdateUI();
                }
            }
        }
    }

    /**
     * action listener for Einzahlen button
     */
    class MainEinzahlenAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            // create Einzahle dialog
            JPanel pnl = new JPanel();
            pnl.setLayout(new BoxLayout(pnl, BoxLayout.PAGE_AXIS));

            JLabel msg1 = new JLabel("Geben Sie bitte den Betrag ein,");
            msg1.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel msg2 = new JLabel("um den Sie Ihr Guthaben erhöhen wollen.");
            msg2.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel msg3 = new JLabel("Mindest Einzahlung 5.00€");
            msg3.setAlignmentX(Component.CENTER_ALIGNMENT);

            LabeldDoubleInput input = new LabeldDoubleInput("Einzahlungsbetrag: ");
            input.setValue(0.5);
            input.setLblWidth(150);

            pnl.add(Box.createRigidArea(new Dimension(0, 5)));
            pnl.add(msg1);
            pnl.add(msg2);
            pnl.add(msg3);
            pnl.add(Box.createRigidArea(new Dimension(0, 10)));
            pnl.add(input);

            Object[] options = {"OK"};
            
            int okCxl = JOptionPane.showOptionDialog(main,
                    pnl,
                    "Ihre Einzahlung",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);


            // update Benutzer and GUI
            if (okCxl == JOptionPane.OK_OPTION)
            {
                if (input.getValue() > 4.99)
                {
                    main.getPlayer().setKONTOSTAND(main.getPlayer().getKONTOSTAND() + input.getValue());
                    main.UpdateUI();
                } else
                {
                    JOptionPane.showMessageDialog(main,
                            "Ihre Einzahlung entspricht nicht den Vorgaben",
                            "Fehleingabe !",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    /**
     * change listener for Tippreihen spinner
     */
    class MainSpinnerListener implements ChangeListener
    {

        @Override
        public void stateChanged(ChangeEvent e)
        {
            JSpinner sp = (JSpinner) e.getSource();
            SpinnerModel model = sp.getModel();
            if (model instanceof SpinnerNumberModel)
            {
                int val = (Integer) ((SpinnerNumberModel) model).getNumber();
                if (main.getTippreiheCount() > val)
                {
                    main.setTippreiheEnabled(val, false);
                } else
                {
                    main.setTippreiheEnabled(val - 1, true);
                }
            }
        }
    }

    /**
     * action listener for Tippreihen Ändern/Eintragen button
     */
    class MainTippreihenCangeAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            activeTipp = Integer.parseInt(e.getActionCommand());
            tipp.reset();
            if (main.getPlayer().getTIPPREIHE(activeTipp)[0] != 0)
            {
                tipp.setTip(main.getPlayer().getTIPPREIHE(activeTipp));
            }
            tipp.setVisible(true);
        }
    }

    /**
     * action listener for Tippreihen Zufällige button
     */
    class MainTippreihenRandAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            activeTipp = Integer.parseInt(e.getActionCommand());
            main.setTippreihenZahlen(activeTipp, gen.zufallsZahlenAusgabe());
        }
    }

    // login related listener
    
    /**
     * action listener for login Anmelden button
     */
    class LoginOkButtonAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            Calendar gdate = login.getDatum();
            if (checkAlter(gdate))
            {
                login.setVisible(false);
                main.addSpieler(new Benutzer(login.getNachname(), login.getVorname(), login.getDatum().getTime()));
                login.dispose();
                main.setJackpott(cash.getBESTAND() + cash.getSICHERUNGSBESTAND());
                main.setVisible(true);
            } else
            {
                login.setVisible(false);
                JOptionPane.showMessageDialog(null, "Sie sind noch keine 18 Jahre alt !", "Zugang verweigert!", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
        }
    }

    /**
     * action listener for login Abbrechen button
     */
    class LoginCanelButtonAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

    // tipp dialog related listener
    /**
     * action listener for Tipp dialog Bestätigen button
     */
    class TippSendButtonAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            main.setTippreihenZahlen(activeTipp, tipp.getTip());
            tipp.setVisible(false);
        }
    }

    /**
     * action listener for Tipp dialog Abbrechen button
     */
    class TippCanelButtonAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            tipp.setVisible(false);
            main.UpdateUI();
        }
    }

}
