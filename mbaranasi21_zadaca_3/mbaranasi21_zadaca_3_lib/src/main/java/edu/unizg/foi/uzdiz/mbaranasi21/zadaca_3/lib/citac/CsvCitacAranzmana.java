package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.citac;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.dto.AranzmanDTO;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.pomocne.DatumParser;

/**
 * Čitač CSV datoteke s turističkim aranžmanima.
 */
public class CsvCitacAranzmana {
    
    private int brojGreske = 0;
    
    /**
     * Učitava aranžmane iz CSV datoteke.
     */
    public List<AranzmanDTO> ucitaj(String putanja) {
        List<AranzmanDTO> aranzmani = new ArrayList<>();
        int redniBroj = 0;
        brojGreske = 0;
        
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
     */
    private AranzmanDTO parsirajRedak(String linija, int redniBroj) {
        try {
            String[] dijelovi = parsirajCsv(linija);
            
            if (dijelovi.length < 10) {
                ispisiGresku(redniBroj, linija, 
                    "Nedostaju obavezni atributi (potrebno min 10)");
                return null;
            }
            
            AranzmanDTO dto = kreirajDTOIzDijelova(dijelovi, redniBroj, linija);
            return dto;
            
        } catch (Exception e) {
            ispisiGresku(redniBroj, linija, "Greška: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Kreira DTO objekt iz parsiranih dijelova CSV-a.
     */
    private AranzmanDTO kreirajDTOIzDijelova(String[] dijelovi, int redniBroj, String linija) {
        String oznaka = dohvatiVrijednost(dijelovi, 0);
        String naziv = dohvatiVrijednost(dijelovi, 1);
        String program = dohvatiVrijednost(dijelovi, 2);
        LocalDate pocetniDatum = DatumParser.parsirajDatum(dohvatiVrijednost(dijelovi, 3));
        LocalDate zavrsniDatum = DatumParser.parsirajDatum(dohvatiVrijednost(dijelovi, 4));
        LocalTime vrijemeKretanja = DatumParser.parsirajVrijeme(dohvatiVrijednost(dijelovi, 5));
        LocalTime vrijemePovratka = DatumParser.parsirajVrijeme(dohvatiVrijednost(dijelovi, 6));
        Double cijena = parsirajDouble(dohvatiVrijednost(dijelovi, 7));
        Integer minBrojPutnika = parsirajInteger(dohvatiVrijednost(dijelovi, 8));
        Integer maksBrojPutnika = parsirajInteger(dohvatiVrijednost(dijelovi, 9));
        
        if (!validirajObavezneAtribute(oznaka, naziv, pocetniDatum, zavrsniDatum, 
                cijena, minBrojPutnika, maksBrojPutnika)) {
            ispisiGresku(redniBroj, linija, "Neispravan format obaveznih atributa");
            return null;
        }
        
        int brojNocenja = parsirajOpcionalniBroj(dijelovi, 10);
        Double doplata = parsirajOpcionalniDouble(dijelovi, 11);
        String prijevoz = parsirajOpcionalniString(dijelovi, 12);
        int brojDorucka = parsirajOpcionalniBroj(dijelovi, 13);
        int brojRuckova = parsirajOpcionalniBroj(dijelovi, 14);
        int brojVecera = parsirajOpcionalniBroj(dijelovi, 15);
        
        return new AranzmanDTO(oznaka, naziv, program != null ? program : "",
            pocetniDatum, zavrsniDatum, vrijemeKretanja, vrijemePovratka,
            cijena, minBrojPutnika, maksBrojPutnika, brojNocenja,
            doplata, prijevoz, brojDorucka, brojRuckova, brojVecera);
    }
    
    /**
     * Validira obavezne atribute.
     */
    private boolean validirajObavezneAtribute(String oznaka, String naziv, 
            LocalDate pocetniDatum, LocalDate zavrsniDatum, 
            Double cijena, Integer minBrojPutnika, Integer maksBrojPutnika) {
        return oznaka != null && naziv != null && pocetniDatum != null 
            && zavrsniDatum != null && cijena != null 
            && minBrojPutnika != null && maksBrojPutnika != null;
    }
    
    /**
     * Parsira opcionalni broj iz CSV-a.
     */
    private int parsirajOpcionalniBroj(String[] dijelovi, int index) {
        if (dijelovi.length <= index) {
            return 0;
        }
        Integer parsed = parsirajInteger(dohvatiVrijednost(dijelovi, index));
        return parsed != null ? parsed : 0;
    }
    
    /**
     * Parsira opcionalni double iz CSV-a.
     */
    private Double parsirajOpcionalniDouble(String[] dijelovi, int index) {
        if (dijelovi.length <= index) {
            return null;
        }
        return parsirajDouble(dohvatiVrijednost(dijelovi, index));
    }
    
    /**
     * Parsira opcionalni string iz CSV-a.
     */
    private String parsirajOpcionalniString(String[] dijelovi, int index) {
        if (dijelovi.length <= index) {
            return "";
        }
        String vrijednost = dohvatiVrijednost(dijelovi, index);
        return vrijednost != null ? vrijednost : "";
    }
    
    /**
     * Parsira CSV liniju vodeći računa o navodnicima.
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
    
    /**
     * Dohvaća vrijednost iz polja dijelova.
     */
    private String dohvatiVrijednost(String[] dijelovi, int index) {
        if (index >= dijelovi.length) {
            return null;
        }
        String vrijednost = dijelovi[index].trim();
        return vrijednost.isEmpty() ? null : vrijednost;
    }
    
    /**
     * Parsira string u Integer.
     */
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
    
    /**
     * Parsira string u Double.
     */
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
    
    /**
     * Ispisuje grešku u formatu: [GREŠKA N] + cijeli redak + objašnjenje.
     */
    private void ispisiGresku(int redniBroj, String sadrzaj, String opis) {
        brojGreske++;
        System.err.println("[GREŠKA " + brojGreske + "] Redak " + redniBroj + ":");
        System.err.println(sadrzaj);
        System.err.println("Razlog: " + opis);
        System.err.println();
    }
}