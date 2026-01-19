package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

/**
 * Product interface za Factory Method pattern.
 * Definira sučelje za izvršavanje komandi.
 */
public interface Komanda {
    
    /**
     * Izvršava komandu.
     * 
     * @return true ako je komanda uspješno izvršena, false inače
     */
    boolean izvrsi();
    
    /**
     * Dohvaća opis komande.
     * 
     * @return Opis komande
     */
    String getOpis();
}