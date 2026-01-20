package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.promatrac;

import java.util.ArrayList;
import java.util.List;

/**
 * ConcreteSubject - upravlja promatračima za jedan aranžman.
 * Prema profesorovim materijalima - Observer pattern.
 */
public class AranzmanSubjekt implements PromatracSubjekt {
    
    private String oznakaAranzmana;
    protected List<TuristickaPromatrac> promatraci = new ArrayList<>();
    
    public AranzmanSubjekt(String oznakaAranzmana) {
        this.oznakaAranzmana = oznakaAranzmana;
    }
    
    @Override
    public void prijaviPromatraca(TuristickaPromatrac promatrac) {
        if (!promatraci.contains(promatrac)) {
            promatraci.add(promatrac);
        }
    }
    
    @Override
    public void odjaviPromatraca(TuristickaPromatrac promatrac) {
        promatraci.remove(promatrac);
    }
    
    @Override
    public void obavijesti(String poruka) {
        for (TuristickaPromatrac p : promatraci) {
            p.azuriraj(poruka);
        }
    }
    
    public String getOznakaAranzmana() {
        return oznakaAranzmana;
    }
    
    public void ocistiPromatrace() {
        promatraci.clear();
    }
}