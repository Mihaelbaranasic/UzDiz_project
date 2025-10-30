package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.konfiguracija.citac;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.konfiguracija.pomocne.DatumParser;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.StanjeRezervacije;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.rezervacija.RezervacijaCreator;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.rezervacija.StandardniRezervacijaCreator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Čitač CSV datoteke s rezervacijama.
 */
public class CsvCitacRezervacija {
    
    private final RezervacijaCreator creator;
    
    public CsvCitacRezervacija() {
        this.creator = new StandardniRezervacijaCreator();
    }
    
    /**
     * Učitava rezervacije iz CSV datoteke.
     *
     * @param putanja Putanja do CSV datoteke
     * @return Lista uspješno učitanih rezervacija
     */
    public List<Rezervacija> ucitaj(String putanja) {
        List<Rezervacija> rezervacije = new ArrayList<>();
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
                
                Rezervacija rezervacija = parsirajRedak(linija, redniBroj);
                if (rezervacija != null) {
                    rezervacije.add(rezervacija);
                }
            }
            
        } catch (IOException e) {
            System.err.println("GREŠKA: Nije moguće pročitati datoteku: " + putanja);
        }
        
        return rezervacije;
    }
    
    /**
     * Parsira jedan redak CSV datoteke u rezervaciju.
     *
     * @param linija Linija za parsiranje
     * @param redniBroj Redni broj linije
     * @return Rezervacija ili null ako parsiranje nije uspjelo
     */
    private Rezervacija parsirajRedak(String linija, int redniBroj) {
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
            
            Osoba osoba = new Osoba(ime, prezime);
            
            return creator.kreirajRezervaciju(
                osoba, oznakaAranzmana, datumVrijeme, StanjeRezervacije.PRIMLJENA
            );
            
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