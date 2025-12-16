package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe.IeiKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica.KomandaCreator;

/**
 * ConcreteCreator za IEI komandu.
 * Format: IEI oznaka [SAF]
 * S = Statistika, A = Analiza, F = Financijski podaci
 * Opcije se pišu zajedno (npr. SAF, SA, SF, itd.)
 */
public class IeiKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (!provjeriArgumente(argumenti, 2)) {
            System.err.println("Format: IEI oznaka [opcije]");
            System.err.println("  Opcije: S (Statistika), A (Analiza), F (Financijski)");
            System.err.println("  Primjer: IEI 1 SAF, IEI 1 SA, IEI 1 F");
            return null;
        }
        
        String oznaka = argumenti[1];
        
        boolean statistika = false;
        boolean analiza = false;
        boolean financije = false;
        
        if (argumenti.length >= 3) {
            String opcije = argumenti[2].toUpperCase();
            
            statistika = opcije.contains("S");
            analiza = opcije.contains("A");
            financije = opcije.contains("F");
            
            for (char c : opcije.toCharArray()) {
                if (c != 'S' && c != 'A' && c != 'F') {
                    System.err.println("UPOZORENJE: Nepoznata opcija '" + c 
                        + "' ignorirana.");
                }
            }
        }
        
        return new IeiKomanda(oznaka, statistika, analiza, financije);
    }
}