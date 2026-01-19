package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.composite;

import java.util.ArrayList;
import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.pomocne.DatumParser;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.visitor.TuristickaVisitor;

/**
 * Composite u Composite pattern-u.
 * Predstavlja aranžman koji sadrži rezervacije (djeca).
 */
public class AranzmanKomponenta implements TuristickaKomponenta {
    
    private Aranzman aranzman;
    private List<TuristickaKomponenta> djeca;
    
    /**
     * Konstruktor.
     * 
     * @param aranzman Aranžman objekt
     */
    public AranzmanKomponenta(Aranzman aranzman) {
        this.aranzman = aranzman;
        this.djeca = new ArrayList<>();
    }
    
    /**
     * Dohvaća aranžman.
     * 
     * @return Aranzman objekt
     */
    public Aranzman getAranzman() {
        return aranzman;
    }
    
    @Override
    public void dodaj(TuristickaKomponenta komponenta) {
        if (komponenta instanceof RezervacijaKomponenta) {
            djeca.add(komponenta);
        } else {
            throw new IllegalArgumentException("Aranžman može sadržavati samo rezervacije!");
        }
    }
    
    @Override
    public void ukloni(TuristickaKomponenta komponenta) {
        djeca.remove(komponenta);
    }
    
    @Override
    public TuristickaKomponenta dohvatiDijete(int index) {
        if (index >= 0 && index < djeca.size()) {
            return djeca.get(index);
        }
        return null;
    }
    
    @Override
    public List<TuristickaKomponenta> dohvatiSvuDjecu() {
        return new ArrayList<>(djeca);
    }
    
    @Override
    public void prikazi() {
        System.out.println("Aranžman: " + aranzman.getOznaka() + " - " + aranzman.getNaziv());
        System.out.println("  Datum: " + DatumParser.formatirajDatum(aranzman.getPocetniDatum()) 
            + " do " + DatumParser.formatirajDatum(aranzman.getZavrsniDatum()));
        System.out.println("  Cijena: " + aranzman.getCijena() + " EUR");
        System.out.println("  Putnici: " + aranzman.getMinBrojPutnika() + " - " + aranzman.getMaksBrojPutnika());
        System.out.println("  Broj rezervacija: " + djeca.size());
        
        if (!djeca.isEmpty()) {
            System.out.println("  Rezervacije:");
            for (TuristickaKomponenta dijete : djeca) {
                dijete.prikazi();
            }
        }
    }
    
    @Override
    public void prihvati(TuristickaVisitor visitor) {
        visitor.visitAranzman(this);
        
        for (TuristickaKomponenta dijete : djeca) {
            dijete.prihvati(visitor);
        }
    }
}