package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * Komanda za pretraživanje podataka u aranžmanima i rezervacijama.
 * Format: PPTAR [A|R] riječ
 * Koristi Visitor pattern.
 */
public class PptarKomanda implements Komanda {
    
    private String tip;
    private String kljucnaRijec;
    
    public PptarKomanda(String tip, String kljucnaRijec) {
        this.tip = tip;
        this.kljucnaRijec = kljucnaRijec;
    }
    
    @Override
    public boolean izvrsi() {
        System.out.println("PPTAR " + tip + " " + kljucnaRijec);
        
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        
        if (tip.equalsIgnoreCase("A")) {
            pretraziAranzmane(agencija);
            return true;
        } else if (tip.equalsIgnoreCase("R")) {
            pretraziRezervacije(agencija);
            return true;
        } else {
            System.err.println("GREŠKA: Nepoznat tip '" + tip + "'!");
            System.err.println("Dopušteni tipovi: A (aranžmani), R (rezervacije)");
            return false;
        }
    }
    
    private void pretraziAranzmane(TuristickaAgencija agencija) {
        List<String> rezultati = agencija.pretraziAranzmane(kljucnaRijec);
        
        if (rezultati.isEmpty()) {
            System.out.println("Nema aranžmana koji sadrže ključnu riječ '" 
                + kljucnaRijec + "'");
            return;
        }
        
        System.out.println("Pronađeni aranžmani:");
        for (String rezultat : rezultati) {
            System.out.println("  - " + rezultat);
        }
    }
    
    private void pretraziRezervacije(TuristickaAgencija agencija) {
        List<String> rezultati = agencija.pretraziRezervacije(kljucnaRijec);
        
        if (rezultati.isEmpty()) {
            System.out.println("Nema rezervacija koje sadrže ključnu riječ '" 
                + kljucnaRijec + "'");
            return;
        }
        
        System.out.println("Pronađene rezervacije:");
        for (String rezultat : rezultati) {
            System.out.println("  - " + rezultat);
        }
    }
    
    @Override
    public String getOpis() {
        return "Pretraživanje aranžmana i rezervacija";
    }
}