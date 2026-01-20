package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * Komanda za brisanje podataka o aranžmanima ili rezervacijama.
 * Format: BP [A|R]
 * A - brisanje svih aranžmana (i njihovih rezervacija)
 * R - brisanje svih rezervacija
 */
public class BpKomanda implements Komanda {

    private String tip;

    public BpKomanda(String tip) {
        this.tip = tip;
    }

    @Override
    public boolean izvrsi() {
        System.out.println("BP " + tip);
        
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        
        if (tip.equalsIgnoreCase("A")) {
            agencija.obrisiSveAranzmane();
            System.out.println("Svi aranžmani (i njihove rezervacije) uspješno obrisani.");
            return true;
        } else if (tip.equalsIgnoreCase("R")) {
            agencija.obrisiSveRezervacije();
            System.out.println("Sve rezervacije uspješno obrisane.");
            return true;
        } else {
            System.err.println("GREŠKA: Nepoznat tip '" + tip + "'!");
            System.err.println("Dopušteni tipovi: A (aranžmani), R (rezervacije)");
            return false;
        }
    }

    @Override
    public String getOpis() {
        return "Brisanje podataka o aranžmanima ili rezervacijama";
    }
}