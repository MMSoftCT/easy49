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
public class Auswertung
{
//    private Spiel SPIELER;

    public Auswertung()
    {

    }

//    public void setSPIELER(Spiel Spieler){
//        this.SPIELER = Spieler;
//    }
//    public Spiel getSPIELER(){
//        return this.SPIELER;
//    }
    public int countGewinnReihen(int[] tipp, int[] zahlen)
    {
        int count = 0;

        for (int i = 0; i < tipp.length; i++)
        {
            for (int j = 0; j < tipp.length; j++)
            {
                if (tipp[i] == zahlen[j])
                {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

//    public void checkGewinn(Benutzer otto){
//    
//    }
}
