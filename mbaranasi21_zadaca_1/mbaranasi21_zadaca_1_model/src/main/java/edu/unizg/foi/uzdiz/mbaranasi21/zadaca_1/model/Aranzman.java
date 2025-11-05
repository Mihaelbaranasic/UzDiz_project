package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Predstavlja turistički aranžman sa svim podacima.
 * Klasa je immutable (nepromjenjiva) - svi atributi su final.
 * Instance se kreiraju isključivo kroz AranzmanBuilder.
 */
public class Aranzman {
    
    private final String oznaka;
    private final String naziv;
    private final String program;
    private final LocalDate pocetniDatum;
    private final LocalDate zavrsniDatum;
    private final LocalTime vrijemeKretanja;
    private final LocalTime vrijemePovratka;
    private final double cijena;
    private final int minBrojPutnika;
    private final int maksBrojPutnika;
    private final int brojNocenja;
    private final Double doplataZaJednokrevetnuSobu;
    private final String prijevoz;
    private final int brojDorucka;
    private final int brojRuckova;
    private final int brojVecera;
    
    /**
     * Package-private konstruktor - poziva se samo iz AranzmanBuilder-a.
     * Kreira novi aranžman sa svim atributima.
     *
     * @param oznaka Jedinstvena oznaka aranžmana
     * @param naziv Naziv turističkog aranžmana
     * @param program Detaljan program aranžmana
     * @param pocetniDatum Datum početka aranžmana
     * @param zavrsniDatum Datum završetka aranžmana
     * @param vrijemeKretanja Vrijeme polaska na aranžman
     * @param vrijemePovratka Vrijeme povratka s aranžmana
     * @param cijena Cijena aranžmana u eurima
     * @param minBrojPutnika Minimalni broj putnika za realizaciju aranžmana
     * @param maksBrojPutnika Maksimalni broj putnika koji mogu ići na aranžman
     * @param brojNocenja Broj noćenja tijekom aranžmana
     * @param doplataZaJednokrevetnuSobu Doplata za jednokrevetnu sobu (može biti null)
     * @param prijevoz Vrste prijevoza korištene tijekom aranžmana
     * @param brojDorucka Broj doručaka uključenih u aranžman
     * @param brojRuckova Broj ručkova uključenih u aranžman
     * @param brojVecera Broj večera uključenih u aranžman
     */
    Aranzman(String oznaka, String naziv, String program,
            LocalDate pocetniDatum, LocalDate zavrsniDatum,
            LocalTime vrijemeKretanja, LocalTime vrijemePovratka,
            double cijena, int minBrojPutnika, int maksBrojPutnika,
            int brojNocenja, Double doplataZaJednokrevetnuSobu,
            String prijevoz, int brojDorucka, int brojRuckova, 
            int brojVecera) {
        this.oznaka = oznaka;
        this.naziv = naziv;
        this.program = program;
        this.pocetniDatum = pocetniDatum;
        this.zavrsniDatum = zavrsniDatum;
        this.vrijemeKretanja = vrijemeKretanja;
        this.vrijemePovratka = vrijemePovratka;
        this.cijena = cijena;
        this.minBrojPutnika = minBrojPutnika;
        this.maksBrojPutnika = maksBrojPutnika;
        this.brojNocenja = brojNocenja;
        this.doplataZaJednokrevetnuSobu = doplataZaJednokrevetnuSobu;
        this.prijevoz = prijevoz;
        this.brojDorucka = brojDorucka;
        this.brojRuckova = brojRuckova;
        this.brojVecera = brojVecera;
    }
    
    /**
     * Dohvaća oznaku aranžmana.
     *
     * @return Oznaka aranžmana
     */
    public String getOznaka() {
        return oznaka;
    }
    
    /**
     * Dohvaća naziv aranžmana.
     *
     * @return Naziv aranžmana
     */
    public String getNaziv() {
        return naziv;
    }
    
    /**
     * Dohvaća program aranžmana.
     *
     * @return Program aranžmana ili null ako nije definiran
     */
    public String getProgram() {
        return program;
    }
    
    /**
     * Dohvaća početni datum aranžmana.
     *
     * @return Datum početka aranžmana
     */
    public LocalDate getPocetniDatum() {
        return pocetniDatum;
    }
    
    /**
     * Dohvaća završni datum aranžmana.
     *
     * @return Datum završetka aranžmana
     */
    public LocalDate getZavrsniDatum() {
        return zavrsniDatum;
    }
    
    /**
     * Dohvaća vrijeme kretanja na aranžman.
     *
     * @return Vrijeme polaska ili null ako nije definirano
     */
    public LocalTime getVrijemeKretanja() {
        return vrijemeKretanja;
    }
    
    /**
     * Dohvaća vrijeme povratka s aranžmana.
     *
     * @return Vrijeme povratka ili null ako nije definirano
     */
    public LocalTime getVrijemePovratka() {
        return vrijemePovratka;
    }
    
    /**
     * Dohvaća cijenu aranžmana.
     *
     * @return Cijena aranžmana u eurima
     */
    public double getCijena() {
        return cijena;
    }
    
    /**
     * Dohvaća minimalni broj putnika potreban za realizaciju aranžmana.
     *
     * @return Minimalni broj putnika
     */
    public int getMinBrojPutnika() {
        return minBrojPutnika;
    }
    
    /**
     * Dohvaća maksimalni broj putnika koji mogu ići na aranžman.
     *
     * @return Maksimalni broj putnika
     */
    public int getMaksBrojPutnika() {
        return maksBrojPutnika;
    }
    
    /**
     * Dohvaća broj noćenja tijekom aranžmana.
     *
     * @return Broj noćenja
     */
    public int getBrojNocenja() {
        return brojNocenja;
    }
    
    /**
     * Dohvaća doplatu za jednokrevetnu sobu.
     *
     * @return Doplata u eurima ili null ako nije definirana
     */
    public Double getDoplataZaJednokrevetnuSobu() {
        return doplataZaJednokrevetnuSobu;
    }
    
    /**
     * Dohvaća vrste prijevoza korištene tijekom aranžmana.
     *
     * @return Prijevoz ili null ako nije definiran
     */
    public String getPrijevoz() {
        return prijevoz;
    }
    
    /**
     * Dohvaća broj doručaka uključenih u cijenu.
     *
     * @return Broj doručaka
     */
    public int getBrojDorucka() {
        return brojDorucka;
    }
    
    /**
     * Dohvaća broj ručkova uključenih u cijenu.
     *
     * @return Broj ručkova
     */
    public int getBrojRuckova() {
        return brojRuckova;
    }
    
    /**
     * Dohvaća broj večera uključenih u cijenu.
     *
     * @return Broj večera
     */
    public int getBrojVecera() {
        return brojVecera;
    }
    
    /**
     * Provjerava preklapa li se ovaj aranžman s drugim aranžmanom po datumu.
     * Preklapanje postoji ako početak prvog aranžmana nije nakon kraja drugog
     * i kraj prvog nije prije početka drugog.
     *
     * @param drugi Drugi aranžman za provjeru preklapanja
     * @return true ako se aranžmani preklapaju po datumu, inače false
     */
    public boolean preklapa(Aranzman drugi) {
        return !this.pocetniDatum.isAfter(drugi.zavrsniDatum) &&
               !this.zavrsniDatum.isBefore(drugi.pocetniDatum);
    }
    
    /**
     * Vraća tekstualnu reprezentaciju aranžmana u formatu "oznaka - naziv".
     *
     * @return Tekstualna reprezentacija aranžmana
     */
    @Override
    public String toString() {
        return oznaka + " - " + naziv;
    }
}