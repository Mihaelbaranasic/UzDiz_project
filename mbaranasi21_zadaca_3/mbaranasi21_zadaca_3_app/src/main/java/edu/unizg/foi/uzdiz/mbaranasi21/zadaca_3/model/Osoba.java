package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model;

/**
 * Predstavlja osobu koja ima rezervaciju.
 * Klasa je immutable - ime i prezime se ne mogu mijenjati nakon kreiranja.
 * Dvije osobe se smatraju jednakim ako imaju isto ime i prezime.
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
     * Vraća puno ime osobe u formatu "ime prezime".
     *
     * @return Puno ime osobe
     */
    public String getPunoIme() {
        return ime + " " + prezime;
    }

    /**
     * Uspoređuje ovu osobu s drugim objektom.
     * Dvije osobe su jednake ako imaju isto ime i isto prezime.
     *
     * @param obj Objekt za usporedbu
     * @return true ako su osobe jednake, inače false
     */
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

    /**
     * Vraća hash kod osobe temeljen na imenu i prezimenu.
     *
     * @return Hash kod osobe
     */
    @Override
    public int hashCode() {
        return ime.hashCode() + prezime.hashCode();
    }

    /**
     * Vraća tekstualnu reprezentaciju osobe (puno ime).
     *
     * @return Puno ime osobe
     */
    @Override
    public String toString() {
        return getPunoIme();
    }
}