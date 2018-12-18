/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spieler;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author pc
 */
public class Benutzer
{

    private String NAME;
    private String VORNAME;
    private int SPIELKER_ID;
    private double KONTOSTAND;
    private double EINSATZ;
    private ArrayList<int[]> TIPPREIHEN;
    private Date GEB_DAT;

    public Benutzer()
    {

    }

    public Benutzer(String Name, String Vorname, Date Geb_Dat)
    {
        this.NAME = Name;
        this.VORNAME = Vorname;
        this.GEB_DAT = Geb_Dat;
        this.EINSATZ = 0.0;
        this.KONTOSTAND = 0.0;
        this.TIPPREIHEN = new ArrayList<int[]>();
    }

    public void setNAME(String Name)
    {
        this.NAME = Name;
    }

    public String getNAME()
    {
        return this.NAME;
    }

    public void setVORNAME(String Vorname)
    {
        this.VORNAME = Vorname;
    }

    public String getVORNAME()
    {
        return this.VORNAME;
    }

    public void setSPIELER_ID(int Spieler_ID)
    {
        this.SPIELKER_ID = Spieler_ID;
    }

    public int getSPIELER_ID()
    {
        return this.SPIELKER_ID;
    }

    public void setKONTOSTAND(double Kontostand)
    {
        this.KONTOSTAND = Kontostand;
    }

    public double getKONTOSTAND()
    {
        return this.KONTOSTAND;
    }

    public void setEINSATZ(double Einsatz)
    {
        this.EINSATZ = Einsatz;
    }

    public double getEINSATZ()
    {
        return this.EINSATZ;
    }

    public void setTIPPREIHE(int nr, int[] zahlen)
    {
        if (nr < 5)
        {
            if (nr + 1 > this.TIPPREIHEN.size())
            {
                this.TIPPREIHEN.add(zahlen);
            } else
            {
                this.TIPPREIHEN.set(nr, zahlen);
            }
        }
    }

    public int[] getTIPPREIHE(int nr)
    {
        if(nr < TIPPREIHEN.size())
        {
            return this.TIPPREIHEN.get(nr);
        }
        return new int[6];
    }
    
    public ArrayList<int[]> getTIPPREIHEN()
    {
        return TIPPREIHEN;
    }
    
    public void setGEB_DAT(Date Geb_Dat)
    {
        this.GEB_DAT = Geb_Dat;
    }

    public Date getGEB_DAT()
    {
        return this.GEB_DAT;
    }
}
