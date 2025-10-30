package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Predstavlja turistički aranžman sa svim podacima.
 * 
 * <p>Koristi Builder uzorak za kreiranje zbog velikog broja atributa
 * od kojih su mnogi opcionalni.</p>
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
     * Privatni konstruktor - koristi se samo iz Buildera.
     *
     * @param builder Builder koji sadrži sve podatke
     */
    private Aranzman(Builder builder) {
        this.oznaka = builder.oznaka;
        this.naziv = builder.naziv;
        this.program = builder.program;
        this.pocetniDatum = builder.pocetniDatum;
        this.zavrsniDatum = builder.zavrsniDatum;
        this.vrijemeKretanja = builder.vrijemeKretanja;
        this.vrijemePovratka = builder.vrijemePovratka;
        this.cijena = builder.cijena;
        this.minBrojPutnika = builder.minBrojPutnika;
        this.maksBrojPutnika = builder.maksBrojPutnika;
        this.brojNocenja = builder.brojNocenja;
        this.doplataZaJednokrevetnuSobu = builder.doplataZaJednokrevetnuSobu;
        this.prijevoz = builder.prijevoz;
        this.brojDorucka = builder.brojDorucka;
        this.brojRuckova = builder.brojRuckova;
        this.brojVecera = builder.brojVecera;
    }
    
    public String getOznaka() {
        return oznaka;
    }
    
    public String getNaziv() {
        return naziv;
    }
    
    public String getProgram() {
        return program;
    }
    
    public LocalDate getPocetniDatum() {
        return pocetniDatum;
    }
    
    public LocalDate getZavrsniDatum() {
        return zavrsniDatum;
    }
    
    public LocalTime getVrijemeKretanja() {
        return vrijemeKretanja;
    }
    
    public LocalTime getVrijemePovratka() {
        return vrijemePovratka;
    }
    
    public double getCijena() {
        return cijena;
    }
    
    public int getMinBrojPutnika() {
        return minBrojPutnika;
    }
    
    public int getMaksBrojPutnika() {
        return maksBrojPutnika;
    }
    
    public int getBrojNocenja() {
        return brojNocenja;
    }
    
    public Double getDoplataZaJednokrevetnuSobu() {
        return doplataZaJednokrevetnuSobu;
    }
    
    public String getPrijevoz() {
        return prijevoz;
    }
    
    public int getBrojDorucka() {
        return brojDorucka;
    }
    
    public int getBrojRuckova() {
        return brojRuckova;
    }
    
    public int getBrojVecera() {
        return brojVecera;
    }
    
    /**
     * Provjerava preklapaju li se datumi ovog aranžmana s drugim.
     *
     * @param drugi Drugi aranžman za provjeru
     * @return true ako se preklapaju, inače false
     */
    public boolean preklapa(Aranzman drugi) {
        return !this.zavrsniDatum.isBefore(drugi.pocetniDatum) 
            && !this.pocetniDatum.isAfter(drugi.zavrsniDatum);
    }
    
    @Override
    public String toString() {
        return oznaka + " - " + naziv;
    }
    
    /**
     * Builder klasa za postupno kreiranje Aranzmana.
     * 
     * <p>BUILDER UZORAK - omogućava fleksibilno kreiranje objekta
     * sa mnogo atributa od kojih su neki opcionalni.</p>
     */
    public static class Builder {
        
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
        public Builder(String oznaka, String naziv, LocalDate pocetniDatum, 
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
        
        public Builder program(String program) {
            this.program = program;
            return this;
        }
        
        public Builder vrijemeKretanja(LocalTime vrijeme) {
            this.vrijemeKretanja = vrijeme;
            return this;
        }
        
        public Builder vrijemePovratka(LocalTime vrijeme) {
            this.vrijemePovratka = vrijeme;
            return this;
        }
        
        public Builder brojNocenja(int broj) {
            this.brojNocenja = broj;
            return this;
        }
        
        public Builder doplataZaJednokrevetnuSobu(Double doplata) {
            this.doplataZaJednokrevetnuSobu = doplata;
            return this;
        }
        
        public Builder prijevoz(String prijevoz) {
            this.prijevoz = prijevoz;
            return this;
        }
        
        public Builder brojDorucka(int broj) {
            this.brojDorucka = broj;
            return this;
        }
        
        public Builder brojRuckova(int broj) {
            this.brojRuckova = broj;
            return this;
        }
        
        public Builder brojVecera(int broj) {
            this.brojVecera = broj;
            return this;
        }
        
        /**
         * Kreira konačni objekt Aranzman.
         *
         * @return Novi aranžman sa postavljenim atributima
         */
        public Aranzman build() {
            return new Aranzman(this);
        }
    }
}