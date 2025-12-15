package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;

/**
 * State interface za stanja rezervacije.
 * Definira operacije koje ovise o stanju rezervacije.
 */
public interface RezervacijaStanje {
    
    /**
     * Prelazi rezervaciju u stanje PRIMLJENA.
     * 
     * @param rezervacija Kontekst rezervacije
     * @return true ako je prijelaz uspješan, false inače
     */
    boolean primljena(Rezervacija rezervacija);
    
    /**
     * Prelazi rezervaciju u stanje AKTIVNA.
     * 
     * @param rezervacija Kontekst rezervacije
     * @return true ako je prijelaz uspješan, false inače
     */
    boolean aktiviraj(Rezervacija rezervacija);
    
    /**
     * Prelazi rezervaciju u stanje NA_CEKANJU.
     * 
     * @param rezervacija Kontekst rezervacije
     * @return true ako je prijelaz uspješan, false inače
     */
    boolean stavi_na_cekanje(Rezervacija rezervacija);
    
    /**
     * Prelazi rezervaciju u stanje ODGOĐENA.
     * 
     * @param rezervacija Kontekst rezervacije
     * @return true ako je prijelaz uspješan, false inače
     */
    boolean odgodi(Rezervacija rezervacija);
    
    /**
     * Prelazi rezervaciju u stanje OTKAZANA.
     * 
     * @param rezervacija Kontekst rezervacije
     * @return true ako je prijelaz uspješan, false inače
     */
    boolean otkazi(Rezervacija rezervacija);
    
    /**
     * Dohvaća naziv stanja.
     * 
     * @return Naziv stanja
     */
    String getNazivStanja();
}