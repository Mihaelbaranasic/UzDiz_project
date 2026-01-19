package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.facade;

import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.citac.CsvCitacAranzmana;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.citac.CsvCitacRezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.dto.AranzmanDTO;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.dto.RezervacijaDTO;

/**
 * Facade za učitavanje i provjeru podataka iz datoteka.
 * Pruža jednostavno sučelje za pristup podsustavu učitavanja CSV datoteka.
 * Ovaj modul radi SAMO sintaktičku provjeru podataka.
 */
public class PodaciFacade {
    
    private CsvCitacAranzmana citacAranzmana;
    private CsvCitacRezervacija citacRezervacija;
    
    /**
     * Konstruktor - inicijalizira podsustav.
     */
    public PodaciFacade() {
        this.citacAranzmana = new CsvCitacAranzmana();
        this.citacRezervacija = new CsvCitacRezervacija();
    }
    
    /**
     * Učitava aranžmane iz CSV datoteke.
     * Vrši sintaktičku provjeru (ispravnost formata, tipova podataka).
     *
     * @param putanja Putanja do CSV datoteke s aranžmanima
     * @return Lista ispravnih aranžmana kao DTO objekti
     */
    public List<AranzmanDTO> ucitajAranzmane(String putanja) {
        return citacAranzmana.ucitaj(putanja);
    }
    
    /**
     * Učitava rezervacije iz CSV datoteke.
     * Vrši sintaktičku provjeru (ispravnost formata, tipova podataka).
     *
     * @param putanja Putanja do CSV datoteke s rezervacijama
     * @return Lista ispravnih rezervacija kao DTO objekti
     */
    public List<RezervacijaDTO> ucitajRezervacije(String putanja) {
        return citacRezervacija.ucitaj(putanja);
    }
}