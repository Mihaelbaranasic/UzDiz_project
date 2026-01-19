package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object za rezervaciju.
 * Jednostavni POJO bez poslovne logike.
 */
public class RezervacijaDTO {
    
    private String ime;
    private String prezime;
    private String oznakaAranzmana;
    private LocalDateTime datumVrijemePrijema;
    
    public RezervacijaDTO() {}
    
    public RezervacijaDTO(String ime, String prezime, String oznakaAranzmana, 
                         LocalDateTime datumVrijemePrijema) {
        this.ime = ime;
        this.prezime = prezime;
        this.oznakaAranzmana = oznakaAranzmana;
        this.datumVrijemePrijema = datumVrijemePrijema;
    }
    
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    
    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }
    
    public String getOznakaAranzmana() { return oznakaAranzmana; }
    public void setOznakaAranzmana(String oznakaAranzmana) { this.oznakaAranzmana = oznakaAranzmana; }
    
    public LocalDateTime getDatumVrijemePrijema() { return datumVrijemePrijema; }
    public void setDatumVrijemePrijema(LocalDateTime datumVrijemePrijema) { 
        this.datumVrijemePrijema = datumVrijemePrijema; 
    }
}