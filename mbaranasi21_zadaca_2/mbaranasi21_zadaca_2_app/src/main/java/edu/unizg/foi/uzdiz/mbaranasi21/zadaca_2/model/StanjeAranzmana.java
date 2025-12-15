package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model;

/**
 * Enumeracija koja predstavlja moguća stanja aranžmana.
 */
public enum StanjeAranzmana {
    
    U_PRIPREMI("UP"),
    AKTIVAN("A"),
    POPUNJEN("POP"),
    OTKAZAN("O");
    
    private final String oznaka;
    
    StanjeAranzmana(String oznaka) {
        this.oznaka = oznaka;
    }
    
    public String getOznaka() {
        return oznaka;
    }
}