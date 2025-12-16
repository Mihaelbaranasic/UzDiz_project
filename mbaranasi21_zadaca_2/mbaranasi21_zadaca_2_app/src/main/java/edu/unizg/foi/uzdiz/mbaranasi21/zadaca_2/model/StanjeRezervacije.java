package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model;

/**
 * Enumeracija koja predstavlja moguća stanja rezervacije.
 */
public enum StanjeRezervacije {
    
    NOVA("N"),
    PRIMLJENA("P"),
    AKTIVNA("A"),
    NA_CEKANJU("C"),
    ODGOĐENA("OD"),
    OTKAZANA("O");
    
    private final String oznaka;
    
    StanjeRezervacije(String oznaka) {
        this.oznaka = oznaka;
    }
    
    public String getOznaka() {
        return oznaka;
    }
}