package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Builder klasa za postupno kreiranje Aranzmana.
 */
public class AranzmanBuilder {
    
    private final String oznaka;
    private final String naziv;
    private final LocalDate pocetniDatum;
    private final LocalDate zavrsniDatum;
    private final double cijena;
    private final int minBrojPutnika;
    private final int maksBrojPutnika;
    
    private String program = "";
    private LocalTime vrijemeKretanja = null;
    private LocalTime vrijemePovratka = null;
    private int brojNocenja = 0;
    private Double doplataZaJednokrevetnuSobu = null;
    private String prijevoz = "";
    private int brojDorucka = 0;
    private int brojRuckova = 0;
    private int brojVecera = 0;
    
    /**
     * Konstruktor sa obaveznim parametrima.
     *
     * @param oznaka Jedinstvena oznaka aranžmana
     * @param naziv Naziv aranžmana
     * @param pocetniDatum Datum početka
     * @param zavrsniDatum Datum završetka
     * @param cijena Cijena aranžmana
     * @param minBrojPutnika Minimalan broj putnika
     * @param maksBrojPutnika Maksimalan broj putnika
     */
    public AranzmanBuilder(String oznaka, String naziv, LocalDate pocetniDatum, 
                          LocalDate zavrsniDatum, double cijena, 
                          int minBrojPutnika, int maksBrojPutnika) {
        this.oznaka = oznaka;
        this.naziv = naziv;
        this.pocetniDatum = pocetniDatum;
        this.zavrsniDatum = zavrsniDatum;
        this.cijena = cijena;
        this.minBrojPutnika = minBrojPutnika;
        this.maksBrojPutnika = maksBrojPutnika;
    }
    
    /**
     * Postavlja program aranžmana.
     *
     * @param program Program aranžmana
     * @return Builder za daljnje pozive
     */
    public AranzmanBuilder program(String program) {
        this.program = program;
        return this;
    }
    
    /**
     * Postavlja vrijeme kretanja.
     *
     * @param vrijeme Vrijeme kretanja
     * @return Builder za daljnje pozive
     */
    public AranzmanBuilder vrijemeKretanja(LocalTime vrijeme) {
        this.vrijemeKretanja = vrijeme;
        return this;
    }
    
    /**
     * Postavlja vrijeme povratka.
     *
     * @param vrijeme Vrijeme povratka
     * @return Builder za daljnje pozive
     */
    public AranzmanBuilder vrijemePovratka(LocalTime vrijeme) {
        this.vrijemePovratka = vrijeme;
        return this;
    }
    
    /**
     * Postavlja broj noćenja.
     *
     * @param broj Broj noćenja
     * @return Builder za daljnje pozive
     */
    public AranzmanBuilder brojNocenja(int broj) {
        this.brojNocenja = broj;
        return this;
    }
    
    /**
     * Postavlja doplatu za jednokrevetnu sobu.
     *
     * @param doplata Doplata u eurima
     * @return Builder za daljnje pozive
     */
    public AranzmanBuilder doplataZaJednokrevetnuSobu(Double doplata) {
        this.doplataZaJednokrevetnuSobu = doplata;
        return this;
    }
    
    /**
     * Postavlja vrstu prijevoza.
     *
     * @param prijevoz Vrsta prijevoza
     * @return Builder za daljnje pozive
     */
    public AranzmanBuilder prijevoz(String prijevoz) {
        this.prijevoz = prijevoz;
        return this;
    }
    
    /**
     * Postavlja broj doručka.
     *
     * @param broj Broj doručka
     * @return Builder za daljnje pozive
     */
    public AranzmanBuilder brojDorucka(int broj) {
        this.brojDorucka = broj;
        return this;
    }
    
    /**
     * Postavlja broj ručkova.
     *
     * @param broj Broj ručkova
     * @return Builder za daljnje pozive
     */
    public AranzmanBuilder brojRuckova(int broj) {
        this.brojRuckova = broj;
        return this;
    }
    
    /**
     * Postavlja broj večera.
     *
     * @param broj Broj večera
     * @return Builder za daljnje pozive
     */
    public AranzmanBuilder brojVecera(int broj) {
        this.brojVecera = broj;
        return this;
    }
    
    /**
     * Kreira konačni objekt Aranzman sa postavljenim atributima.
     *
     * @return Novi aranžman
     */
    public Aranzman build() {
        return new Aranzman(oznaka, naziv, program, pocetniDatum, zavrsniDatum,
                           vrijemeKretanja, vrijemePovratka, cijena, 
                           minBrojPutnika, maksBrojPutnika, brojNocenja,
                           doplataZaJednokrevetnuSobu, prijevoz, 
                           brojDorucka, brojRuckova, brojVecera);
    }
}