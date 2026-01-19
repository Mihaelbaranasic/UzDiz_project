package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.dekorator;

/**
 * Component interface za Decorator pattern.
 * Definira operacije za generiranje izvještaja.
 */
public interface IzvjestajKomponenta {
    
    /**
     * Generira izvještaj.
     */
    void generiraj();
    
    /**
     * Dohvaća tekstualni sadržaj izvještaja.
     * 
     * @return Sadržaj izvještaja
     */
    String dohvatiSadrzaj();
}