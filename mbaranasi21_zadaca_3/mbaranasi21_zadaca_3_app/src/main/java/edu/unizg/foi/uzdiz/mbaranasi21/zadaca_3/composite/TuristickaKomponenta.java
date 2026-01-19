package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.composite;

import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.visitor.TuristickaVisitor;

/**
 * Component interface za Composite pattern.
 */
public interface TuristickaKomponenta {
    
    default void dodaj(TuristickaKomponenta komponenta) {
        throw new UnsupportedOperationException(
            "Operacija nije podržana za ovaj tip komponente.");
    }
    
    default void ukloni(TuristickaKomponenta komponenta) {
        throw new UnsupportedOperationException(
            "Operacija nije podržana za ovaj tip komponente.");
    }
    
    default TuristickaKomponenta dohvatiDijete(int index) {
        throw new UnsupportedOperationException(
            "Operacija nije podržana za ovaj tip komponente.");
    }
    
    default List<TuristickaKomponenta> dohvatiSvuDjecu() {
        throw new UnsupportedOperationException(
            "Operacija nije podržana za ovaj tip komponente.");
    }
    
    void prikazi();
    
    /**
     * Prihvaća visitor za obilazak strukture (Visitor pattern).
     * 
     * @param visitor Visitor objekt
     */
    void prihvati(TuristickaVisitor visitor);
}