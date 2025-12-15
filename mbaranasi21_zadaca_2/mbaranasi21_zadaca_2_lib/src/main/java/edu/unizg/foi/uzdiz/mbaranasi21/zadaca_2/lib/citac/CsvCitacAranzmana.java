package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.citac;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.dto.AranzmanDTO;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.pomocne.DatumParser;

/**
 * Čitač CSV datoteke s turističkim aranžmanima.
 * Učitava podatke iz CSV formata i vraća DTO objekte (bez poslovne logike).
 */
public class CsvCitacAranzmana {
    
    /**
     * Učitava aranžmane iz CSV datoteke.
     *
     * @param putanja Putanja do CSV datoteke s aranžmanima
     * @return Lista uspješno učitanih aranžmana kao DTO objekti
     */
    public List<AranzmanDTO> ucitaj(String putanja) {
        List<AranzmanDTO> aranzmani = new ArrayList<>();
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
                
                AranzmanDTO dto = parsirajRedak(linija, redniBroj);
                if (dto != null) {
                    aranzmani.add(dto);
                }
            }
            
        } catch (IOException e) {
            System.err.println("GREŠKA: Nije moguće pročitati datoteku: " + putanja);
        }
        
        return aranzmani;
    }
    
    /**
     * Parsira jedan redak CSV datoteke u DTO.
     *
     * @param linija Linija CSV datoteke za parsiranje
     * @param redniBroj Redni broj linije (za ispis grešaka)
     * @return AranzmanDTO objekt ili null ako parsiranje nije uspjelo
     */
    private AranzmanDTO parsirajRedak(String linija, int redniBroj) {
        try {
            String[] dijelovi = parsirajCsv(linija);
            
            if (dijelovi.length < 10) {
                ispisiGresku(redniBroj, linija, 
                    "Nedostaju obavezni atributi (potrebno min 10)");
                return null;
            }
            
            // Parsiranje obaveznih atributa
            String oznaka = dohvatiVrijednost(dijelovi, 0);
            String naziv = dohvatiVrijednost(dijelovi, 1);
            String program = dohvatiVrijednost(dijelovi, 2);
            LocalDate pocetniDatum = DatumParser.parsirajDatum(
                dohvatiVrijednost(dijelovi, 3));
            LocalDate zavrsniDatum = DatumParser.parsirajDatum(
                dohvatiVrijednost(dijelovi, 4));
            LocalTime vrijemeKretanja = DatumParser.parsirajVrijeme(
                dohvatiVrijednost(dijelovi, 5));
            LocalTime vrijemePovratka = DatumParser.parsirajVrijeme(
                dohvatiVrijednost(dijelovi, 6));
            Double cijena = parsirajDouble(dohvatiVrijednost(dijelovi, 7));
            Integer minBrojPutnika = parsirajInteger(dohvatiVrijednost(dijelovi, 8));
            Integer maksBrojPutnika = parsirajInteger(dohvatiVrijednost(dijelovi, 9));
            
            // Validacija obaveznih atributa
            if (oznaka == null || naziv == null || pocetniDatum == null 
                || zavrsniDatum == null || cijena == null 
                || minBrojPutnika == null || maksBrojPutnika == null) {
                ispisiGresku(redniBroj, linija, "Neispravan format obaveznih atributa");
                return null;
            }
            
            // Parsiranje opcionalnih atributa
            Integer brojNocenja = dijelovi.length > 10 ? 
                parsirajInteger(dohvatiVrijednost(dijelovi, 10)) : 0;
            Double doplata = dijelovi.length > 11 ? 
                parsirajDouble(dohvatiVrijednost(dijelovi, 11)) : null;
            String prijevoz = dijelovi.length > 12 ? 
                dohvatiVrijednost(dijelovi, 12) : "";
            Integer brojDorucka = dijelovi.length > 13 ? 
                parsirajInteger(dohvatiVrijednost(dijelovi, 13)) : 0;
            Integer brojRuckova = dijelovi.length > 14 ? 
                parsirajInteger(dohvatiVrijednost(dijelovi, 14)) : 0;
            Integer brojVecera = dijelovi.length > 15 ? 
                parsirajInteger(dohvatiVrijednost(dijelovi, 15)) : 0;
            
            // Kreiranje DTO objekta
            return new AranzmanDTO(
                oznaka, naziv, program != null ? program : "",
                pocetniDatum, zavrsniDatum,
                vrijemeKretanja, vrijemePovratka,
                cijena, minBrojPutnika, maksBrojPutnika,
                brojNocenja != null ? brojNocenja : 0,
                doplata,
                prijevoz != null ? prijevoz : "",
                brojDorucka != null ? brojDorucka : 0,
                brojRuckova != null ? brojRuckova : 0,
                brojVecera != null ? brojVecera : 0
            );
            
        } catch (Exception e) {
            ispisiGresku(redniBroj, linija, "Greška: " + e.getMessage());
            return null;
        }
    }
    
    private String[] parsirajCsv(String linija) {
        List<String> rezultat = new ArrayList<>();
        boolean uNavodnicima = false;
        StringBuilder trenutni = new StringBuilder();
        
        for (int i = 0; i < linija.length(); i++) {
            char c = linija.charAt(i);
            
            if (c == '"') {
                uNavodnicima = !uNavodnicima;
            } else if (c == ',' && !uNavodnicima) {
                rezultat.add(trenutni.toString());
                trenutni = new StringBuilder();
            } else {
                trenutni.append(c);
            }
        }
        rezultat.add(trenutni.toString());
        
        return rezultat.toArray(new String[0]);
    }
    
    private String dohvatiVrijednost(String[] dijelovi, int index) {
        if (index >= dijelovi.length) {
            return null;
        }
        String vrijednost = dijelovi[index].trim();
        return vrijednost.isEmpty() ? null : vrijednost;
    }
    
    private Integer parsirajInteger(String tekst) {
        if (tekst == null) {
            return null;
        }
        try {
            return Integer.parseInt(tekst);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    private Double parsirajDouble(String tekst) {
        if (tekst == null) {
            return null;
        }
        try {
            return Double.parseDouble(tekst);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    private void ispisiGresku(int redniBroj, String sadrzaj, String opis) {
        System.err.println("GREŠKA u retku " + redniBroj + ": " + opis);
        System.err.println("  Sadržaj: " + sadrzaj);
    }
}