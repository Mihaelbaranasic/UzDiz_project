package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.singleton.TuristickaAgencija;

/**
 * Komanda za postavljanje redoslijeda ispisa.
 * Definira kriterij sortiranja rezultata.
 */
public class IpKomanda implements Komanda {
    
    private String kriterij;
    
    /**
     * Konstruktor.
     * 
     * @param kriterij Kriterij sortiranja (A-abeceda, D-datum, C-cijena)
     */
    public IpKomanda(String kriterij) {
        this.kriterij = kriterij;
    }
    
    @Override
    public boolean izvrsi() {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        
        String kriterijUpper = kriterij.toUpperCase();
        
        if (kriterijUpper.equals("A")) {
            agencija.postaviKriterijSortiranja("ABECEDA");
            System.out.println("Redoslijed ispisa postavljen na: Abecedni");
        } else if (kriterijUpper.equals("D")) {
            agencija.postaviKriterijSortiranja("DATUM");
            System.out.println("Redoslijed ispisa postavljen na: Datum");
        } else if (kriterijUpper.equals("C")) {
            agencija.postaviKriterijSortiranja("CIJENA");
            System.out.println("Redoslijed ispisa postavljen na: Cijena");
        } else {
            System.err.println("GREŠKA: Nepoznat kriterij '" + kriterij + "'!");
            System.err.println("Dopušteni kriteriji: A (abeceda), D (datum), C (cijena)");
            return false;
        }
        
        return true;
    }
    
    @Override
    public String getOpis() {
        return "Postavljanje redoslijeda ispisa";
    }
}