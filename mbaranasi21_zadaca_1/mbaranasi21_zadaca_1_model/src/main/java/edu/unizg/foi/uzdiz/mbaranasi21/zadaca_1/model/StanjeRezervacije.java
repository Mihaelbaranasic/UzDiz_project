package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model;

/**
 * Enumeracija mogućih stanja rezervacije.
 * 
 * <p>Stanja rezervacije:</p>
 * <ul>
 *   <li>PRIMLJENA - Rezervacija je primljena, aranžman još nije dostigao minimalan broj putnika</li>
 *   <li>AKTIVNA - Rezervacija je aktivna, aranžman ima dovoljno putnika</li>
 *   <li>NA_CEKANJU - Rezervacija je na čekanju jer je dostignut maksimalan broj putnika</li>
 *   <li>OTKAZANA - Rezervacija je otkazana</li>
 * </ul>
 */
public enum StanjeRezervacije {
    
    /**
     * Rezervacija je primljena, ali aranžman još nije dostigao minimalan broj putnika.
     */
    PRIMLJENA("P"),
    
    /**
     * Rezervacija je aktivna - aranžman ima dovoljno putnika.
     */
    AKTIVNA("A"),
    
    /**
     * Rezervacija je na čekanju jer je dostignut maksimalan broj putnika.
     */
    NA_CEKANJU("C"),
    
    /**
     * Rezervacija je otkazana.
     */
    OTKAZANA("O");
    
    private final String oznaka;
    
    /**
     * Konstruktor enumeracije.
     * 
     * @param oznaka Jednoslovna oznaka stanja
     */
    private StanjeRezervacije(String oznaka) {
        this.oznaka = oznaka;
    }
    
    /**
     * Dohvaća jednoslovnu oznaku stanja.
     * 
     * @return Oznaka stanja (P, A, C, O)
     */
    public String getOznaka() {
        return oznaka;
    }
    
    /**
     * Vraća StanjeRezervacije na temelju oznake.
     * 
     * @param oznaka Jednoslovna oznaka stanja (P, A, C, O)
     * @return Stanje rezervacije ili null ako oznaka nije važeća
     */
    public static StanjeRezervacije fromOznaka(String oznaka) {
        if (oznaka == null) {
            return null;
        }
        
        for (StanjeRezervacije stanje : values()) {
            if (stanje.oznaka.equals(oznaka)) {
                return stanje;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return this.name() + " (" + oznaka + ")";
    }
}