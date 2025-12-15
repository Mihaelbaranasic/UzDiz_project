package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.citac;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.dto.RezervacijaDTO;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.pomocne.DatumParser;

/**
 * Čitač CSV datoteke s rezervacijama.
 * Učitava podatke iz CSV formata i vraća DTO objekte (bez poslovne logike).
 */
public class CsvCitacRezervacija {
    
    /**
     * Učitava rezervacije iz CSV datoteke.
     *
     * @param putanja Putanja do CSV datoteke
     * @return Lista uspješno učitanih rezervacija kao DTO objekti
     */
    public List<RezervacijaDTO> ucitaj(String putanja) {
        List<RezervacijaDTO> rezervacije = new ArrayList<>();
        int redniBroj = 0;
        
        try (BufferedReader br = new BufferedReader(
                new FileReader(putanja, StandardCharsets.UTF_8))) {
            
            String linija;
            boolean prviRedak = true;
            
            while ((linija = br.readLine()) != null) {
                redniBroj++;
                
                if (prviRedak) {
                    prviRedak = false;
                    continue;
                }
                
                if (linija.trim().isEmpty() || linija.trim().startsWith("#")) {
                    continue;
                }
                
                RezervacijaDTO dto = parsirajRedak(linija, redniBroj);
                if (dto != null) {
                    rezervacije.add(dto);
                }
            }
            
        } catch (IOException e) {
            System.err.println("GREŠKA: Nije moguće pročitati datoteku: " + putanja);
        }
        
        return rezervacije;
    }
    
    /**
     * Parsira jedan redak CSV datoteke u DTO.
     *
     * @param linija Linija za parsiranje
     * @param redniBroj Redni broj linije
     * @return RezervacijaDTO ili null ako parsiranje nije uspjelo
     */
    private RezervacijaDTO parsirajRedak(String linija, int redniBroj) {
        try {
            String[] dijelovi = linija.split(",");
            
            if (dijelovi.length < 4) {
                ispisiGresku(redniBroj, linija, "Nedostaju obavezni atributi (potrebna 4)");
                return null;
            }
            
            String ime = dijelovi[0].trim();
            String prezime = dijelovi[1].trim();
            String oznakaAranzmana = dijelovi[2].trim();
            LocalDateTime datumVrijeme = DatumParser.parsirajDatumVrijeme(dijelovi[3].trim());
            
            if (ime.isEmpty() || prezime.isEmpty() || oznakaAranzmana.isEmpty() 
                || datumVrijeme == null) {
                ispisiGresku(redniBroj, linija, "Neispravan format atributa");
                return null;
            }
            
            return new RezervacijaDTO(ime, prezime, oznakaAranzmana, datumVrijeme);
            
        } catch (Exception e) {
            ispisiGresku(redniBroj, linija, "Greška: " + e.getMessage());
            return null;
        }
    }
    
    private void ispisiGresku(int redniBroj, String sadrzaj, String opis) {
        System.err.println("GREŠKA u retku " + redniBroj + ": " + opis);
        System.err.println("  Sadržaj: " + sadrzaj);
    }
}