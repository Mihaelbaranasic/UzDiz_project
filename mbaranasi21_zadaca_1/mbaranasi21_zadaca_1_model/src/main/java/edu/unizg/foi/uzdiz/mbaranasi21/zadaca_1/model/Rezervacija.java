package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model;

import java.time.LocalDateTime;

/**
 * Predstavlja rezervaciju turističkog aranžmana za jednu osobu.
 * Rezervacija može biti u različitim stanjima: primljena, aktivna, na čekanju ili otkazana.
 * Datum prijema se ne može mijenjati, dok se stanje rezervacije može ažurirati.
 */
public class Rezervacija {

    private final Osoba osoba;
    private final String oznakaAranzmana;
    private final LocalDateTime datumVrijemePrijema;
    private StanjeRezervacije stanje;
    private LocalDateTime datumVrijemeOtkaza;

    /**
     * Konstruktor za kreiranje rezervacije.
     *
     * @param osoba Osoba koja rezervira aranžman
     * @param oznakaAranzmana Oznaka aranžmana za koji se vrši rezervacija
     * @param datumVrijemePrijema Datum i vrijeme kada je rezervacija primljena
     * @param stanje Početno stanje rezervacije (obično PRIMLJENA)
     */
    public Rezervacija(Osoba osoba, String oznakaAranzmana,
                      LocalDateTime datumVrijemePrijema, StanjeRezervacije stanje) {
        this.osoba = osoba;
        this.oznakaAranzmana = oznakaAranzmana;
        this.datumVrijemePrijema = datumVrijemePrijema;
        this.stanje = stanje;
    }

    /**
     * Dohvaća osobu koja je izvršila rezervaciju.
     *
     * @return Osoba koja rezervira
     */
    public Osoba getOsoba() {
        return osoba;
    }

    /**
     * Dohvaća oznaku aranžmana za koji je napravljena rezervacija.
     *
     * @return Oznaka aranžmana
     */
    public String getOznakaAranzmana() {
        return oznakaAranzmana;
    }

    /**
     * Dohvaća datum i vrijeme prijema rezervacije.
     *
     * @return Datum i vrijeme prijema
     */
    public LocalDateTime getDatumVrijemePrijema() {
        return datumVrijemePrijema;
    }

    /**
     * Dohvaća trenutno stanje rezervacije.
     *
     * @return Stanje rezervacije (PRIMLJENA, AKTIVNA, NA_CEKANJU, OTKAZANA)
     */
    public StanjeRezervacije getStanje() {
        return stanje;
    }

    /**
     * Dohvaća datum i vrijeme otkaza rezervacije.
     *
     * @return Datum i vrijeme otkaza ili null ako rezervacija nije otkazana
     */
    public LocalDateTime getDatumVrijemeOtkaza() {
        return datumVrijemeOtkaza;
    }

    /**
     * Postavlja novo stanje rezervacije.
     * Koristi se za ažuriranje stanja tijekom životnog ciklusa rezervacije.
     *
     * @param novoStanje Novo stanje rezervacije
     */
    public void postaviStanje(StanjeRezervacije novoStanje) {
        this.stanje = novoStanje;
    }

    /**
     * Označava rezervaciju kao otkazanu i postavlja datum i vrijeme otkaza.
     * Stanje rezervacije se mijenja u OTKAZANA.
     *
     * @param datumVrijeme Datum i vrijeme kada je rezervacija otkazana
     */
    public void otkazi(LocalDateTime datumVrijeme) {
        this.stanje = StanjeRezervacije.OTKAZANA;
        this.datumVrijemeOtkaza = datumVrijeme;
    }

    /**
     * Provjerava je li rezervacija u aktivnom stanju.
     *
     * @return true ako je rezervacija aktivna, inače false
     */
    public boolean jeAktivna() {
        return stanje == StanjeRezervacije.AKTIVNA;
    }

    /**
     * Provjerava je li rezervacija u primljenom stanju.
     *
     * @return true ako je rezervacija primljena, inače false
     */
    public boolean jePrimljena() {
        return stanje == StanjeRezervacije.PRIMLJENA;
    }

    /**
     * Provjerava je li rezervacija na čekanju.
     *
     * @return true ako je rezervacija na čekanju, inače false
     */
    public boolean jeNaCekanju() {
        return stanje == StanjeRezervacije.NA_CEKANJU;
    }

    /**
     * Provjerava je li rezervacija otkazana.
     *
     * @return true ako je rezervacija otkazana, inače false
     */
    public boolean jeOtkazana() {
        return stanje == StanjeRezervacije.OTKAZANA;
    }

    /**
     * Vraća tekstualnu reprezentaciju rezervacije u formatu
     * "Ime Prezime - oznaka_aranžmana (STANJE)".
     *
     * @return Tekstualna reprezentacija rezervacije
     */
    @Override
    public String toString() {
        return osoba.getPunoIme() + " - " + oznakaAranzmana + " (" + stanje + ")";
    }
}