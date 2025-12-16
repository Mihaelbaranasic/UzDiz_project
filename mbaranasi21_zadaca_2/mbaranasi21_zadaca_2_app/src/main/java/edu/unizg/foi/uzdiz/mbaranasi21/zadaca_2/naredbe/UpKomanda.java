package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe;

import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.builder.AranzmanBuilder;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.dto.AranzmanDTO;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.dto.RezervacijaDTO;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.facade.PodaciFacade;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.StanjeRezervacije;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.singleton.TuristickaAgencija;

/**
 * Komanda za učitavanje podataka iz CSV datoteka.
 * Koristi Facade za pristup podacima.
 */
public class UpKomanda implements Komanda {
    
    private String datotekaAranzmana;
    private String datotekaRezervacija;
    
    /**
     * Konstruktor.
     * 
     * @param datotekaAranzmana Putanja do CSV datoteke s aranžmanima
     * @param datotekaRezervacija Putanja do CSV datoteke s rezervacijama
     */
    public UpKomanda(String datotekaAranzmana, String datotekaRezervacija) {
        this.datotekaAranzmana = datotekaAranzmana;
        this.datotekaRezervacija = datotekaRezervacija;
    }
    
    @Override
    public boolean izvrsi() {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        PodaciFacade facade = new PodaciFacade();
        
        int brojAranzmana = ucitajAranzmane(facade, agencija);
        int brojRezervacija = ucitajRezervacije(facade, agencija);
        
        System.out.println("Podaci uspješno učitani:");
        System.out.println("  - Aranžmani: " + brojAranzmana);
        System.out.println("  - Rezervacije: " + brojRezervacija);
        
        return true;
    }
    
    /**
     * Učitava aranžmane iz datoteke.
     */
    private int ucitajAranzmane(PodaciFacade facade, TuristickaAgencija agencija) {
        List<AranzmanDTO> aranzmaniDTO = facade.ucitajAranzmane(datotekaAranzmana);
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
    
    /**
     * Učitava rezervacije iz datoteke.
     */
    private int ucitajRezervacije(PodaciFacade facade, TuristickaAgencija agencija) {
        List<RezervacijaDTO> rezervacijeDTO = facade.ucitajRezervacije(datotekaRezervacija);
        
        for (RezervacijaDTO dto : rezervacijeDTO) {
            Rezervacija rezervacija = konvertirajURezervaciju(dto);
            agencija.dodajRezervaciju(rezervacija);
        }
        
        return rezervacijeDTO.size();
    }
    
    /**
     * Konvertira AranzmanDTO u Aranzman koristeći Builder.
     */
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
    
    /**
     * Konvertira RezervacijaDTO u Rezervacija.
     * Početni status je NOVA.
     */
    private Rezervacija konvertirajURezervaciju(RezervacijaDTO dto) {
        Osoba osoba = new Osoba(dto.getIme(), dto.getPrezime());
        return new Rezervacija(osoba, dto.getOznakaAranzmana(),
            dto.getDatumVrijemePrijema(), StanjeRezervacije.NOVA);
    }
    
    @Override
    public String getOpis() {
        return "Učitavanje podataka iz CSV datoteka";
    }
}