package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.visitor;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.composite.AranzmanKomponenta;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.composite.RezervacijaKomponenta;

/**
 * Visitor interface za Visitor pattern.
 * Definira visit metode za različite tipove komponenti.
 */
public interface TuristickaVisitor {
    
    /**
     * Posjećuje aranžman komponentu.
     * 
     * @param komponenta Aranžman komponenta
     */
    void visitAranzman(AranzmanKomponenta komponenta);
    
    /**
     * Posjećuje rezervaciju komponentu.
     * 
     * @param komponenta Rezervacija komponenta
     */
    void visitRezervacija(RezervacijaKomponenta komponenta);
}