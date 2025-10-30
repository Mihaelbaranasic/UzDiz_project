package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model;

/**
 * Predstavlja osobu koja ima rezervaciju.
 */
public class Osoba {
    
    private final String ime;
    private final String prezime;
    
    /**
     * Konstruktor za kreiranje osobe.
     *
     * @param ime Ime osobe
     * @param prezime Prezime osobe
     */
    public Osoba(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }
    
    /**
     * Dohvaća ime osobe.
     *
     * @return Ime osobe
     */
    public String getIme() {
        return ime;
    }
    
    /**
     * Dohvaća prezime osobe.
     *
     * @return Prezime osobe
     */
    public String getPrezime() {
        return prezime;
    }
    
    /**
     * Vraća puno ime osobe (ime + prezime).
     *
     * @return Puno ime osobe
     */
    public String getPunoIme() {
        return ime + " " + prezime;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Osoba osoba = (Osoba) obj;
        return ime.equals(osoba.ime) && prezime.equals(osoba.prezime);
    }
    
    @Override
    public int hashCode() {
        return ime.hashCode() + prezime.hashCode();
    }
    
    @Override
    public String toString() {
        return getPunoIme();
    }
}