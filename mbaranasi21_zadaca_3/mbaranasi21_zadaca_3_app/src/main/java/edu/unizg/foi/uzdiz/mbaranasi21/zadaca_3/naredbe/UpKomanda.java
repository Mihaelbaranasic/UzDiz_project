package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.builder.AranzmanBuilder;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.dto.AranzmanDTO;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.dto.RezervacijaDTO;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.facade.PodaciFacade;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.StanjeRezervacije;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * Komanda za učitavanje podataka iz CSV datoteke.
 * Format: UP [A|R] nazivDatoteke
 * A - učitavanje aranžmana
 * R - učitavanje rezervacija
 */
public class UpKomanda implements Komanda {
    
    private String tip;
    private String datoteka;
    
    public UpKomanda(String tip, String datoteka) {
        this.tip = tip;
        this.datoteka = datoteka;
    }
    
    @Override
    public boolean izvrsi() {
        System.out.println("UP " + tip + " " + datoteka);
        
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        PodaciFacade facade = new PodaciFacade();
        
        if (tip.equalsIgnoreCase("A")) {
            int brojUcitanih = ucitajAranzmane(facade, agencija);
            System.out.println("Učitano " + brojUcitanih + " novih aranžmana.");
            return true;
        } else if (tip.equalsIgnoreCase("R")) {
            int brojUcitanih = ucitajRezervacije(facade, agencija);
            System.out.println("Učitano " + brojUcitanih + " novih rezervacija.");
            return true;
        } else {
            System.err.println("GREŠKA: Nepoznat tip '" + tip + "'!");
            System.err.println("Dopušteni tipovi: A (aranžmani), R (rezervacije)");
            return false;
        }
    }
    
    private int ucitajAranzmane(PodaciFacade facade, TuristickaAgencija agencija) {
        List<AranzmanDTO> aranzmaniDTO = facade.ucitajAranzmane(datoteka);
        int brojUcitanih = 0;
        
        for (AranzmanDTO dto : aranzmaniDTO) {
            if (agencija.dohvatiAranzman(dto.getOznaka()) != null) {
                System.err.println("GREŠKA: Aranžman s oznakom '" + dto.getOznaka() 
                    + "' već postoji. Duplikat ignoriran.");
                continue;
            }
            
            Aranzman aranzman = konvertirajUAranzman(dto);
            agencija.dodajAranzman(aranzman);
            brojUcitanih++;
        }
        
        return brojUcitanih;
    }
    
    private int ucitajRezervacije(PodaciFacade facade, TuristickaAgencija agencija) {
        List<RezervacijaDTO> rezervacijeDTO = facade.ucitajRezervacije(datoteka);
        
        for (RezervacijaDTO dto : rezervacijeDTO) {
            Rezervacija rezervacija = konvertirajURezervaciju(dto);
            agencija.dodajRezervaciju(rezervacija);
        }
        
        return rezervacijeDTO.size();
    }
    
    private Aranzman konvertirajUAranzman(AranzmanDTO dto) {
        AranzmanBuilder builder = new AranzmanBuilder(
            dto.getOznaka(), dto.getNaziv(), dto.getPocetniDatum(),
            dto.getZavrsniDatum(), dto.getCijena(), dto.getMinBrojPutnika(),
            dto.getMaksBrojPutnika());
        
        return builder
            .program(dto.getProgram())
            .vrijemeKretanja(dto.getVrijemeKretanja())
            .vrijemePovratka(dto.getVrijemePovratka())
            .brojNocenja(dto.getBrojNocenja())
            .doplataZaJednokrevetnuSobu(dto.getDoplataZaJednokrevetnuSobu())
            .prijevoz(dto.getPrijevoz())
            .brojDorucka(dto.getBrojDorucka())
            .brojRuckova(dto.getBrojRuckova())
            .brojVecera(dto.getBrojVecera())
            .build();
    }
    
    private Rezervacija konvertirajURezervaciju(RezervacijaDTO dto) {
        Osoba osoba = new Osoba(dto.getIme(), dto.getPrezime());
        return new Rezervacija(osoba, dto.getOznakaAranzmana(),
            dto.getDatumVrijemePrijema(), StanjeRezervacije.NOVA);
    }
    
    @Override
    public String getOpis() {
        return "Učitavanje podataka iz CSV datoteke";
    }
}