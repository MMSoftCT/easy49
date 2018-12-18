/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spielsteuerung;

/**
 *
 * @author pc
 */
public class Kasse
{

    private double GEWINNFAKTOR;
    private double SICHERUNGSFAKTOR;
    private double BESTAND;
    private double SICHERUNGSBESTAND;

    public Kasse()
    {

    }

    public void setGEWINNFAKTOR(double Gewinnfaktor)
    {
        this.GEWINNFAKTOR = Gewinnfaktor;
    }

    public double getGEWINNFAKTOR()
    {
        return this.GEWINNFAKTOR;
    }

    public void setSICHERUNGSFAKTOR(double Sicherungsfaktor)
    {
        this.SICHERUNGSFAKTOR = Sicherungsfaktor;
    }

    public double getSICHERUNGSFAKTOR()
    {
        return this.SICHERUNGSFAKTOR;
    }

    public void setBESTAND(double Bestand)
    {
        this.BESTAND = Bestand;
    }

    public double getBESTAND()
    {
        return this.BESTAND;
    }

    public void setSICHERUNGSBESTAND(double Sicherungsbestand)
    {
        this.SICHERUNGSBESTAND = Sicherungsbestand;
    }

    public double getSICHERUNGSBESTAND()
    {
        return this.SICHERUNGSBESTAND;
    }

    public void einzahlung(double betrag)
    {
        this.BESTAND += betrag * 0.5;
        this.SICHERUNGSBESTAND += betrag * 0.5;
    }

    public double auszahlung(int anzahlRichtige, double einsatz)
    {
        double aus = einsatz * anzahlRichtige * GEWINNFAKTOR;
        BESTAND -= aus;
        return aus;
    }
}
