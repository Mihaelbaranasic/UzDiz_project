package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.Komanda;

/**
 * Abstract Creator za Factory Method pattern.
 * Deklarira factory metodu koja vraća Product (Komanda).
 */
public abstract class KomandaCreator {
    
    protected Komanda komanda;
    
    /**
     * Factory Method - kreira konkretnu komandu.
     * Svaki ConcreteCreator implementira ovu metodu.
     * 
     * @param argumenti Argumenti potrebni za kreiranje komande
     * @return Kreirana komanda
     */
    public abstract Komanda kreirajKomandu(String[] argumenti);
    
    /**
     * Template metoda - kreira i izvršava komandu.
     * 
     * @param argumenti Argumenti komande
     * @return true ako je komanda uspješno izvršena, false inače
     */
    public boolean izvrsiKomandu(String[] argumenti) {
        komanda = kreirajKomandu(argumenti);
        
        if (komanda == null) {
            System.err.println("GREŠKA: Nije moguće kreirati komandu!");
            return false;
        }
        
        return komanda.izvrsi();
    }
    
    /**
     * Dohvaća kreiranu komandu.
     * 
     * @return Komanda
     */
    public Komanda getKomanda() {
        return komanda;
    }
    
    /**
     * Provjerava jesu li argumenti valjani (broj argumenata).
     * 
     * @param argumenti Argumenti za provjeru
     * @param minimalno Minimalni broj argumenata
     * @return true ako je broj argumenata dovoljan, false inače
     */
    protected boolean provjeriArgumente(String[] argumenti, int minimalno) {
        if (argumenti.length < minimalno) {
            System.err.println("GREŠKA: Nedostaju argumenti! Potrebno minimalno " 
                + minimalno + " argumenata.");
            return false;
        }
        return true;
    }
}