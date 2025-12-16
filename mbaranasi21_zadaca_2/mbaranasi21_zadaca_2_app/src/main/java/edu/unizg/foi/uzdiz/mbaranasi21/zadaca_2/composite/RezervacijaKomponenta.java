package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.composite;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.pomocne.DatumParser;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.visitor.TuristickaVisitor;

/**
 * Leaf u Composite pattern-u.
 * Predstavlja rezervaciju - nema djecu.
 */
public class RezervacijaKomponenta implements TuristickaKomponenta {
    
    private Rezervacija rezervacija;
    
    /**
     * Konstruktor.
     * 
     * @param rezervacija Rezervacija objekt
     */
    public RezervacijaKomponenta(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }
    
    /**
     * Dohvaća rezervaciju.
     * 
     * @return Rezervacija objekt
     */
    public Rezervacija getRezervacija() {
        return rezervacija;
    }
    
    @Override
    public void prikazi() {
        System.out.println("    - Rezervacija: " 
            + rezervacija.getOsoba().getIme() + " " 
            + rezervacija.getOsoba().getPrezime() + " | "
            + DatumParser.formatirajDatumVrijeme(rezervacija.getDatumVrijemePrijema()) + " | "
            + "Stanje: " + rezervacija.getStanje().getOznaka());
    }
    
    @Override
    public void prihvati(TuristickaVisitor visitor) {
        visitor.visitRezervacija(this);
    }
}