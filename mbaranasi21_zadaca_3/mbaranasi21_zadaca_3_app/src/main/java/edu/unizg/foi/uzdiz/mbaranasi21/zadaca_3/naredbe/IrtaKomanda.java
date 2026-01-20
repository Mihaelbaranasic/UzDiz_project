package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

import java.util.ArrayList;
import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.pomocne.DatumParser;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

public class IrtaKomanda implements Komanda {
    
    private String oznakaAranzmana;
    private String filter;
    
    public IrtaKomanda(String oznakaAranzmana) {
        this.oznakaAranzmana = oznakaAranzmana;
        this.filter = null;
    }
    
    public IrtaKomanda(String oznakaAranzmana, String filter) {
        this.oznakaAranzmana = oznakaAranzmana;
        this.filter = filter;
    }
    
    @Override
    public boolean izvrsi() {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        Aranzman aranzman = agencija.dohvatiAranzman(oznakaAranzmana);
        
        if (aranzman == null) {
            System.err.println("GREŠKA: Aranžman '" + oznakaAranzmana + "' ne postoji!");
            return false;
        }
        
        List<Rezervacija> rezervacije = agencija.dohvatiRezervacije(oznakaAranzmana);
        List<Rezervacija> filtrirane = filtrirajRezervacije(rezervacije);
        
        ispisiRezervacije(aranzman, filtrirane);
        return true;
    }
    
    private List<Rezervacija> filtrirajRezervacije(List<Rezervacija> rezervacije) {
        if (filter == null || filter.isEmpty()) {
            return rezervacije;
        }
        
        boolean primljenePrimljene = filter.contains("P");
        boolean aktivneAktivne = filter.contains("A");
        boolean cekanjeCekanje = filter.contains("Č") || filter.contains("C");
        boolean otkazaneOtkazane = filter.contains("O") && !filter.contains("OD");
        boolean odgodeneOdgodene = filter.contains("OD");
        
        List<Rezervacija> rezultat = new ArrayList<>();
        
        for (Rezervacija r : rezervacije) {
            boolean dodaj = false;
            
            if (primljenePrimljene && r.jePrimljena()) {
                dodaj = true;
            }
            if (aktivneAktivne && r.jeAktivna()) {
                dodaj = true;
            }
            if (cekanjeCekanje && r.jeNaCekanju()) {
                dodaj = true;
            }
            if (otkazaneOtkazane && r.jeOtkazana()) {
                dodaj = true;
            }
            if (odgodeneOdgodene && r.jeOdgodena()) {
                dodaj = true;
            }
            
            if (dodaj) {
                rezultat.add(r);
            }
        }
        
        return rezultat;
    }
    
    private void ispisiRezervacije(Aranzman aranzman, List<Rezervacija> rezervacije) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  REZERVACIJE ZA ARANŽMAN: " + aranzman.getOznaka());
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println();
        
        if (rezervacije.isEmpty()) {
            System.out.println("Nema rezervacija za prikaz.");
            return;
        }
        
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        rezervacije = agencija.primiijeniRedoslijedRezervacije(rezervacije);
        
        ispisiHeader();
        ispisiLiniju();
        
        for (Rezervacija r : rezervacije) {
            ispisiRedak(r);
        }
    }
    
    private void ispisiHeader() {
        System.out.printf("%-20s %-20s %-20s %-12s%n",
            "Ime", "Prezime", "Datum prijema", "Stanje");
    }
    
    private void ispisiLiniju() {
        System.out.println("─────────────────────────────────────────────────────"
            + "───────────────");
    }
    
    private void ispisiRedak(Rezervacija r) {
        System.out.printf("%-20s %-20s %-20s %-12s%n",
            r.getOsoba().getIme(),
            r.getOsoba().getPrezime(),
            DatumParser.formatirajDatumVrijeme(r.getDatumVrijemePrijema()),
            r.getStanje().name());
    }
    
    @Override
    public String getOpis() {
        return "Ispis rezervacija za turistički aranžman";
    }
}