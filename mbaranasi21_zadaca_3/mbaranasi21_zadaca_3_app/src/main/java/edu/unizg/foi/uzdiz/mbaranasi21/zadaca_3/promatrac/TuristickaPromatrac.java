package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.promatrac;

/**
 * Observer interface za Observer pattern.
 * Definira sučelje za objekte koji žele biti obaviješteni o promjenama.
 */
public interface TuristickaPromatrac {
    
    /**
     * Ažurira promatrača s porukom o promjeni.
     * 
     * @param poruka Poruka o promjeni
     */
    void azuriraj(String poruka);
}