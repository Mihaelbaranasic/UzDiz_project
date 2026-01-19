package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

import java.time.LocalDate;
import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.pomocne.DatumParser;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * Komanda za ispis svih turističkih aranžmana.
 * Podržava filtriranje po datumu.
 */
public class ItakKomanda implements Komanda {
    
    private LocalDate datumOd;
    private LocalDate datumDo;
    
    /**
     * Konstruktor bez filtera.
     */
    public ItakKomanda() {
        this.datumOd = null;
        this.datumDo = null;
    }
    
    /**
     * Konstruktor s filterom po datumu.
     */
    public ItakKomanda(LocalDate datumOd, LocalDate datumDo) {
        this.datumOd = datumOd;
        this.datumDo = datumDo;
    }
    
    @Override
    public boolean izvrsi() {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        
        List<Aranzman> aranzmani = agencija.dohvatiSveAranzmane();
        List<Aranzman> filtrirani = filtrirajPoDatamu(aranzmani);
        
        ispisiAranzmane(filtrirani);
        return true;
    }
    
    /**
     * Filtrira aranžmane po datumu ako je postavljen filter.
     */
    private List<Aranzman> filtrirajPoDatamu(List<Aranzman> aranzmani) {
        if (datumOd == null && datumDo == null) {
            return aranzmani;
        }
        
        List<Aranzman> rezultat = new java.util.ArrayList<>();
        
        for (Aranzman a : aranzmani) {
            if (zadovoljavaFilter(a)) {
                rezultat.add(a);
            }
        }
        
        return rezultat;
    }
    
    /**
     * Provjerava zadovoljava li aranžman filter po datumu.
     */
    private boolean zadovoljavaFilter(Aranzman a) {
        LocalDate pocetni = a.getPocetniDatum();
        
        if (datumOd != null && pocetni.isBefore(datumOd)) {
            return false;
        }
        
        if (datumDo != null && pocetni.isAfter(datumDo)) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Ispisuje aranžmane u tabličnom formatu.
     */
    private void ispisiAranzmane(List<Aranzman> aranzmani) {
        if (aranzmani.isEmpty()) {
            System.out.println("Nema aranžmana za prikaz.");
            return;
        }
        
        ispisiHeader();
        ispisiLiniju();
        
        for (Aranzman a : aranzmani) {
            ispisiRedak(a);
        }
    }
    
    /**
     * Ispisuje zaglavlje tablice.
     */
    private void ispisiHeader() {
        System.out.printf("%-10s %-30s %-15s %-15s %-10s %-8s %-8s%n",
            "Oznaka", "Naziv", "Početni", "Završni", "Cijena", 
            "Min", "Maks");
    }
    
    /**
     * Ispisuje liniju razdvajanja.
     */
    private void ispisiLiniju() {
        System.out.println("─────────────────────────────────────────────────"
            + "──────────────────────────────────────────────");
    }
    
    /**
     * Ispisuje jedan redak tablice.
     */
    private void ispisiRedak(Aranzman a) {
        System.out.printf("%-10s %-30s %-15s %-15s %-10.2f %-8d %-8d%n",
            a.getOznaka(),
            skratiTekst(a.getNaziv(), 30),
            DatumParser.formatirajDatum(a.getPocetniDatum()),
            DatumParser.formatirajDatum(a.getZavrsniDatum()),
            a.getCijena(),
            a.getMinBrojPutnika(),
            a.getMaksBrojPutnika());
    }
    
    /**
     * Skraćuje tekst na zadanu duljinu.
     */
    private String skratiTekst(String tekst, int maxDuljina) {
        if (tekst.length() <= maxDuljina) {
            return tekst;
        }
        return tekst.substring(0, maxDuljina - 3) + "...";
    }
    
    @Override
    public String getOpis() {
        return "Ispis turističkih aranžmana";
    }
}