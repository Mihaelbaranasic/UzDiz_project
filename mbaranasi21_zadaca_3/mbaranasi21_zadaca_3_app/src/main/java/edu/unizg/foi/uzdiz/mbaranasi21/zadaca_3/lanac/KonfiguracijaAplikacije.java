package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lanac;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.strategija.JednaRezervacijaStrategija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.strategija.NullRezervacijaStrategija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.strategija.RezervacijaStrategija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.strategija.ViseRezervacijaStrategija;

/**
 * Klasa koja drži konfiguraciju aplikacije parsiranu iz argumenata.
 */
public class KonfiguracijaAplikacije {
    
    private String datotekaAranzmani;
    private String datotekaRezervacije;
    private RezervacijaStrategija strategija;
    
    public KonfiguracijaAplikacije() {
        this.strategija = new NullRezervacijaStrategija();
    }
    
    public String getDatotekaAranzmani() {
        return datotekaAranzmani;
    }
    
    public void setDatotekaAranzmani(String datotekaAranzmani) {
        this.datotekaAranzmani = datotekaAranzmani;
    }
    
    public String getDatotekaRezervacije() {
        return datotekaRezervacije;
    }
    
    public void setDatotekaRezervacije(String datotekaRezervacije) {
        this.datotekaRezervacije = datotekaRezervacije;
    }
    
    public RezervacijaStrategija getStrategija() {
        return strategija;
    }
    
    public void setStrategija(RezervacijaStrategija strategija) {
        this.strategija = strategija;
    }
    
    /**
     * Provjerava jesu li svi obavezni argumenti postavljeni.
     */
    public boolean jeValjan() {
        return datotekaAranzmani != null && datotekaRezervacije != null;
    }
    private boolean jdrPostavljen = false;
    private boolean vdrPostavljen = false;

    public void postaviJdr() {
        if (vdrPostavljen) {
            System.err.println("GREŠKA: Ne mogu se koristiti --jdr i --vdr istovremeno!");
            System.exit(1);
        }
        this.jdrPostavljen = true;
        this.strategija = new JednaRezervacijaStrategija();
    }

    public void postaviVdr() {
        if (jdrPostavljen) {
            System.err.println("GREŠKA: Ne mogu se koristiti --jdr i --vdr istovremeno!");
            System.exit(1);
        }
        this.vdrPostavljen = true;
        this.strategija = new ViseRezervacijaStrategija();
    }
}