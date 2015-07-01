package Start;

import Materialien.Logindaten;
import Nutzeroberflaeche.MainWindow;

/**
 * Der Startpunkt des Programms
 */
public class Start {
    
    public static void main(String[] args)
    {
        // Verbinde zur Datenbank
        Logindaten.setzeLogindaten();
        
        MainWindow ui = new MainWindow();
    }
    
}
