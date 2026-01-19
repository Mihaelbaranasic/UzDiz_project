package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

import java.time.LocalDateTime;
import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.stanje.aranzman.AranzmanStanje;

/**
 * Komanda za otkaz turističkog aranžmana.
 * Otkazuje aranžman i sve njegove rezervacije.
 */
public class OtaKomanda implements Komanda {
    
    private String oznakaAranzmana;
    
    /**
     * Konstruktor.
     * 
     * @param oznakaAranzmana Oznaka aranžmana za otkazati
     */
    public OtaKomanda(String oznakaAranzmana) {
        this.oznakaAranzmana = oznakaAranzmana;
    }
    
    @Override
    public boolean izvrsi() {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        Aranzman aranzman = agencija.dohvatiAranzman(oznakaAranzmana);
        
        if (aranzman == null) {
            System.err.println("GREŠKA: Aranžman '" + oznakaAranzmana + "' ne postoji!");
            return false;
        }
        
        otkaziAranzmana(agencija);
        otkaziSveRezervacije(agencija);
        
        System.out.println("Aranžman '" + oznakaAranzmana 
            + "' i sve njegove rezervacije uspješno otkazani.");
        return true;
    }
    
    /**
     * Otkazuje aranžman koristeći State pattern.
     */
    private void otkaziAranzmana(TuristickaAgencija agencija) {
        AranzmanStanje trenutnoStanje = agencija.dohvatiStanjeAranzmana(oznakaAranzmana);
        
        if (trenutnoStanje != null) {
            trenutnoStanje.otkazi(agencija, oznakaAranzmana);
        }
    }
    
    /**
     * Otkazuje sve rezervacije za aranžman.
     */
    private void otkaziSveRezervacije(TuristickaAgencija agencija) {
        List<Rezervacija> rezervacije = agencija.dohvatiRezervacije(oznakaAranzmana);
        LocalDateTime datumOtkaza = LocalDateTime.now();
        
        for (Rezervacija r : rezervacije) {
            if (!r.jeOtkazana()) {
                r.otkazi(datumOtkaza);
            }
        }
    }
    
    @Override
    public String getOpis() {
        return "Otkaz turističkog aranžmana";
    }
}