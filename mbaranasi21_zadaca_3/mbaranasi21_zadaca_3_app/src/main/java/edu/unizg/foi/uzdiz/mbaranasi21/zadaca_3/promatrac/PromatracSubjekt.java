package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.promatrac;

/**
 * Subject interface za Observer pattern.
 * Definira sučelje za objekte koji obavještavaju promatrače o promjenama.
 */
public interface PromatracSubjekt {
    
    /**
     * Prijavljuje promatrača.
     * 
     * @param promatrac Promatrač koji se prijavljuje
     */
    void prijaviPromatraca(TuristickaPromatrac promatrac);
    
    /**
     * Odjavljuje promatrača.
     * 
     * @param promatrac Promatrač koji se odjavljuje
     */
    void odjaviPromatraca(TuristickaPromatrac promatrac);
    
    /**
     * Obavještava sve promatrače o promjeni.
     * 
     * @param poruka Poruka o promjeni
     */
    void obavijesti(String poruka);
}