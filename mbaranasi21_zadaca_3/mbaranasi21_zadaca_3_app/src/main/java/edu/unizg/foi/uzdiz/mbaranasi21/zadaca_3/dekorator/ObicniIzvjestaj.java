package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.dekorator;

import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.pomocne.DatumParser;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * ConcreteComponent - osnovni izvještaj o aranžmanu.
 */
public class ObicniIzvjestaj implements IzvjestajKomponenta {
    
    private String oznakaAranzmana;
    private StringBuilder sadrzaj;
    
    /**
     * Konstruktor.
     * 
     * @param oznakaAranzmana Oznaka aranžmana za izvještaj
     */
    public ObicniIzvjestaj(String oznakaAranzmana) {
        this.oznakaAranzmana = oznakaAranzmana;
        this.sadrzaj = new StringBuilder();
    }
    
    @Override
    public void generiraj() {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        Aranzman aranzman = agencija.dohvatiAranzman(oznakaAranzmana);
        
        if (aranzman == null) {
            sadrzaj.append("GREŠKA: Aranžman '").append(oznakaAranzmana)
                   .append("' ne postoji!\n");
            return;
        }
        
        generirајOsnovneInformacije(aranzman);
        generirајRezervacije(agencija, aranzman);
    }
    
    /**
     * Generira osnovne informacije o aranžmanu.
     */
    private void generirајOsnovneInformacije(Aranzman aranzman) {
        sadrzaj.append("═══════════════════════════════════════════════════════\n");
        sadrzaj.append("  IZVJEŠTAJ O ARANŽMANU: ").append(aranzman.getOznaka()).append("\n");
        sadrzaj.append("═══════════════════════════════════════════════════════\n\n");
        
        sadrzaj.append("Naziv: ").append(aranzman.getNaziv()).append("\n");
        sadrzaj.append("Početni datum: ")
               .append(DatumParser.formatirajDatum(aranzman.getPocetniDatum())).append("\n");
        sadrzaj.append("Završni datum: ")
               .append(DatumParser.formatirajDatum(aranzman.getZavrsniDatum())).append("\n");
        sadrzaj.append("Cijena: ").append(aranzman.getCijena()).append(" EUR\n");
        sadrzaj.append("Broj putnika: ").append(aranzman.getMinBrojPutnika())
               .append(" - ").append(aranzman.getMaksBrojPutnika()).append("\n\n");
    }
    
    /**
     * Generira informacije o rezervacijama.
     */
    private void generirајRezervacije(TuristickaAgencija agencija, Aranzman aranzman) {
        List<Rezervacija> rezervacije = agencija.dohvatiRezervacije(aranzman.getOznaka());
        
        sadrzaj.append("Ukupno rezervacija: ").append(rezervacije.size()).append("\n");
        
        long aktivne = prebrojiPoStanju(rezervacije, "aktivna");
        long primljene = prebrojiPoStanju(rezervacije, "primljena");
        long naCekanju = prebrojiPoStanju(rezervacije, "cekanje");
        long otkazane = prebrojiPoStanju(rezervacije, "otkazana");
        
        sadrzaj.append("  - Aktivne: ").append(aktivne).append("\n");
        sadrzaj.append("  - Primljene: ").append(primljene).append("\n");
        sadrzaj.append("  - Na čekanju: ").append(naCekanju).append("\n");
        sadrzaj.append("  - Otkazane: ").append(otkazane).append("\n\n");
    }
    
    /**
     * Prebraja rezervacije po stanju.
     */
    private long prebrojiPoStanju(List<Rezervacija> rezervacije, String stanje) {
        long broj = 0;
        for (Rezervacija r : rezervacije) {
            if (stanje.equals("aktivna") && r.jeAktivna()) {
                broj++;
            } else if (stanje.equals("primljena") && r.jePrimljena()) {
                broj++;
            } else if (stanje.equals("cekanje") && r.jeNaCekanju()) {
                broj++;
            } else if (stanje.equals("otkazana") && r.jeOtkazana()) {
                broj++;
            }
        }
        return broj;
    }
    
    @Override
    public String dohvatiSadrzaj() {
        return sadrzaj.toString();
    }
}