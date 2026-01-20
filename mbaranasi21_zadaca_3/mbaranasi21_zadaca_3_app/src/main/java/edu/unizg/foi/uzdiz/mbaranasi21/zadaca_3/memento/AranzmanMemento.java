package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.memento;

import java.util.ArrayList;
import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.stanje.aranzman.AranzmanStanje;

/**
 * Memento koji čuva stanje aranžmana i njegovih rezervacija.
 * Immutable objekt.
 */
public class AranzmanMemento {
    
    private final Aranzman aranzman;
    private final List<Rezervacija> rezervacije;
    private final AranzmanStanje stanje;
    
    public AranzmanMemento(Aranzman aranzman, List<Rezervacija> rezervacije, 
                          AranzmanStanje stanje) {
        this.aranzman = kopirajAranzman(aranzman);
        this.rezervacije = kopirajRezervacije(rezervacije);
        this.stanje = stanje;
    }
    
    public Aranzman getAranzman() {
        return kopirajAranzman(aranzman);
    }
    
    public List<Rezervacija> getRezervacije() {
        return kopirajRezervacije(rezervacije);
    }
    
    public AranzmanStanje getStanje() {
        return stanje;
    }
    
    private Aranzman kopirajAranzman(Aranzman original) {
        return new Aranzman(
            original.getOznaka(),
            original.getNaziv(),
            original.getProgram(),
            original.getPocetniDatum(),
            original.getZavrsniDatum(),
            original.getVrijemeKretanja(),
            original.getVrijemePovratka(),
            original.getCijena(),
            original.getMinBrojPutnika(),
            original.getMaksBrojPutnika(),
            original.getBrojNocenja(),
            original.getDoplataZaJednokrevetnuSobu(),
            original.getPrijevoz(),
            original.getBrojDorucka(),
            original.getBrojRuckova(),
            original.getBrojVecera()
        );
    }
    
    private List<Rezervacija> kopirajRezervacije(List<Rezervacija> originalne) {
        List<Rezervacija> kopija = new ArrayList<>();
        for (Rezervacija r : originalne) {
            kopija.add(r.kreirajKopiju());
        }
        return kopija;
    }
    
}