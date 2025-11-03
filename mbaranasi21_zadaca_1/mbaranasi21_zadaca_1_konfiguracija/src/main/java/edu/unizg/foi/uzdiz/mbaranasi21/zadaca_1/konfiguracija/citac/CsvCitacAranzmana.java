package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.konfiguracija.citac;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.konfiguracija.pomocne.DatumParser;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.AranzmanBuilder;  // ← DODAJ

/**
 * Čitač CSV datoteke s turističkim aranžmanima.
 */
public class CsvCitacAranzmana {
    
    /**
     * Učitava aranžmane iz CSV datoteke.
     *
     * @param putanja Putanja do CSV datoteke
     * @return Lista uspješno učitanih aranžmana
     */
    public List<Aranzman> ucitaj(String putanja) {
        List<Aranzman> aranzmani = new ArrayList<>();
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
                
                Aranzman aranzman = parsirajRedak(linija, redniBroj);
                if (aranzman != null) {
                    aranzmani.add(aranzman);
                }
            }
            
        } catch (IOException e) {
            System.err.println("GREŠKA: Nije moguće pročitati datoteku: " + putanja);
        }
        
        return aranzmani;
    }
    
    /**
     * Parsira jedan redak CSV datoteke u aranžman.
     *
     * @param linija Linija za parsiranje
     * @param redniBroj Redni broj linije
     * @return Aranzman ili null ako parsiranje nije uspjelo
     */
    private Aranzman parsirajRedak(String linija, int redniBroj) {
        try {
            String[] dijelovi = parsirajCsv(linija);
            
            if (dijelovi.length < 10) {
                ispisiGresku(redniBroj, linija, 
                    "Nedostaju obavezni atributi (potrebno min 10)");
                return null;
            }
            
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
            
            if (oznaka == null || naziv == null || pocetniDatum == null 
                || zavrsniDatum == null || cijena == null 
                || minBrojPutnika == null || maksBrojPutnika == null) {
                ispisiGresku(redniBroj, linija, "Neispravan format obaveznih atributa");
                return null;
            }
            
            AranzmanBuilder builder = new AranzmanBuilder(
                oznaka, naziv, pocetniDatum, zavrsniDatum, cijena, 
                minBrojPutnika, maksBrojPutnika
            );
            
            if (program != null) builder.program(program);
            if (vrijemeKretanja != null) builder.vrijemeKretanja(vrijemeKretanja);
            if (vrijemePovratka != null) builder.vrijemePovratka(vrijemePovratka);
            
            if (dijelovi.length > 10) {
                Integer brojNocenja = parsirajInteger(dohvatiVrijednost(dijelovi, 10));
                if (brojNocenja != null) builder.brojNocenja(brojNocenja);
            }
            
            if (dijelovi.length > 11) {
                Double doplata = parsirajDouble(dohvatiVrijednost(dijelovi, 11));
                if (doplata != null) builder.doplataZaJednokrevetnuSobu(doplata);
            }
            
            if (dijelovi.length > 12) {
                String prijevoz = dohvatiVrijednost(dijelovi, 12);
                if (prijevoz != null) builder.prijevoz(prijevoz);
            }
            
            if (dijelovi.length > 13) {
                Integer brojDorucka = parsirajInteger(dohvatiVrijednost(dijelovi, 13));
                if (brojDorucka != null) builder.brojDorucka(brojDorucka);
            }
            
            if (dijelovi.length > 14) {
                Integer brojRuckova = parsirajInteger(dohvatiVrijednost(dijelovi, 14));
                if (brojRuckova != null) builder.brojRuckova(brojRuckova);
            }
            
            if (dijelovi.length > 15) {
                Integer brojVecera = parsirajInteger(dohvatiVrijednost(dijelovi, 15));
                if (brojVecera != null) builder.brojVecera(brojVecera);
            }
            
            return builder.build();
            
        } catch (Exception e) {
            ispisiGresku(redniBroj, linija, "Greška: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Parsira CSV liniju u dijelove.
     *
     * @param linija CSV linija
     * @return Polje vrijednosti
     */
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
        if (index >= dijelovi.length) return null;
        String vrijednost = dijelovi[index].trim();
        return vrijednost.isEmpty() ? null : vrijednost;
    }
    
    private Integer parsirajInteger(String tekst) {
        if (tekst == null) return null;
        try {
            return Integer.parseInt(tekst);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    private Double parsirajDouble(String tekst) {
        if (tekst == null) return null;
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