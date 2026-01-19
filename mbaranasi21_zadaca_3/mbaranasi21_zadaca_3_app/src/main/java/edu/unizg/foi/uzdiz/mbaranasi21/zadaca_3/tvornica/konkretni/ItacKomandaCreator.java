package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.composite.AranzmanKomponenta;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.KomandaCreator;

/**
 * ConcreteCreator za ITAC komandu (Composite prikaz).
 */
public class ItacKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (!provjeriArgumente(argumenti, 2)) {
            System.err.println("Format: ITAC oznaka");
            return null;
        }
        
        String oznaka = argumenti[1];
        
        return new Komanda() {
            @Override
            public boolean izvrsi() {
                TuristickaAgencija agencija = TuristickaAgencija.getInstance();
                AranzmanKomponenta aranzmanKomp = agencija.kreirajCompositeStrukturuAranzmana(oznaka);
                
                if (aranzmanKomp == null) {
                    System.err.println("GREŠKA: Aranžman '" + oznaka + "' ne postoji!");
                    return false;
                }
                
                aranzmanKomp.prikazi();
                return true;
            }
            
            @Override
            public String getOpis() {
                return "Ispis turističkog aranžmana (Composite struktura)";
            }
        };
    }
}