/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy49;

import GUI.Spiel;
import java.awt.SplashScreen;

/**
 *
 * @author amederake
 */
public class easy49
{

    private static Spiel control;
    private static final int SHOW_FOR = 3000;	// timer length

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        control = new Spiel();
        Thread t = new Thread()
        {
            public void run()
            {
                SplashScreen splash = SplashScreen.getSplashScreen();
                if (splash == null)
                {
                    System.out.println("SplashScreen kann nicht erzeugt werden.");
                    return;
                }
                try
                {
                    Thread.sleep(SHOW_FOR);
                } catch (InterruptedException e)
                {
                    System.err.println("Thread unterbrochen");
                }
                splash.close();
                control.run();
            }
        };
        t.start();
    }

}
