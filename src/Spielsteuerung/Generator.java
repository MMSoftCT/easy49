/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spielsteuerung;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 *
 * @author pc
 */
public class Generator {
    private int[] ZahlenTipp;
    
    
    
    public Generator(){
    
    }
    
    
    public void setZahlenTipp(int[] Zahlentipp){
        this.ZahlenTipp = Zahlentipp;
    }
    
    public int[] getZahlenTipp(){
        return this.ZahlenTipp;
    }
    
    public void zufallsZahlenEingabe(){
    
    }
    
    public void manuelleZahlenEingabe(){
    
    
    }
    
    public int[] zufallsZahlenAusgabe(){
    
        int[] lottobereich = new int[49];
        int[] lottozahlen = new int[6];
        SecureRandom rand = new SecureRandom();
        
        // Array mit den Zahlen 1 - 49 auffüllen
        for(int i = 0;i<lottobereich.length;i++){
            lottobereich[i] = (i + 1);
        }
        
        //  Array zufällig durchmischen
        for(int i = 0;i<lottobereich.length;i++){
            int index = rand.nextInt(49);
            int tmp = lottobereich[i];
            lottobereich[i] = lottobereich[index];
            lottobereich[index] = tmp;
        }
        
        // die ersten 6 Zahlen in ein neues Array kopieren
        for(int i = 0;i<lottozahlen.length;i++){
            lottozahlen[i] = lottobereich[i];
        }
        
        // Array in aufsteigender Reihenfolge sortieren
        Arrays.sort(lottozahlen);
        
        return lottozahlen;
        
    }
    
//    public Spielreihen<> erstelleSpielreihen(Benutzer otto){
//        return null;
//    } 
    
    
    
}
