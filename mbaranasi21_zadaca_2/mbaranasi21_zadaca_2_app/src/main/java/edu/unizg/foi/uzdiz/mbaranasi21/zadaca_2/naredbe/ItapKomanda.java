package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.pomocne.DatumParser;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.singleton.TuristickaAgencija;

/**
 * Komanda za ispis pojedinog aranžmana po oznaci.
 */
public class ItapKomanda implements Komanda {
    
    private String oznaka;
    
    public ItapKomanda(String oznaka) {
        this.oznaka = oznaka;
    }
    
    @Override
    public boolean izvrsi() {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        Aranzman a = agencija.dohvatiAranzman(oznaka);
        
        if (a == null) {
            System.err.println("GREŠKA: Aranžman '" + oznaka + "' ne postoji!");
            return false;
        }
        
        System.out.println("Oznaka: " + a.getOznaka());
        System.out.println("Naziv: " + a.getNaziv());
        
        if (a.getProgram() != null && !a.getProgram().isEmpty()) {
            System.out.println("Program:");
            String program = a.getProgram().replace("\\n", "\n");
            for (String linija : program.split("\n")) {
                System.out.println("  " + linija);
            }
        } else {
            System.out.println("Program: -");
        }
        
        System.out.println("Početni datum: " + DatumParser.formatirajDatum(a.getPocetniDatum()));
        System.out.println("Završni datum: " + DatumParser.formatirajDatum(a.getZavrsniDatum()));
        System.out.println("Vrijeme kretanja: " + DatumParser.formatirajVrijeme(a.getVrijemeKretanja()));
        System.out.println("Vrijeme povratka: " + DatumParser.formatirajVrijeme(a.getVrijemePovratka()));
        System.out.println("Cijena: " + a.getCijena() + " EUR");
        System.out.println("Min broj putnika: " + a.getMinBrojPutnika());
        System.out.println("Maks broj putnika: " + a.getMaksBrojPutnika());
        System.out.println("Broj noćenja: " + a.getBrojNocenja());
        System.out.println("Doplata za jednokrevetnu sobu: "
            + (a.getDoplataZaJednokrevetnuSobu() != null ? a.getDoplataZaJednokrevetnuSobu() + " EUR" : "-"));
        System.out.println("Prijevoz: " + (a.getPrijevoz() != null ? a.getPrijevoz() : "-"));
        System.out.println("Broj doručka: " + a.getBrojDorucka());
        System.out.println("Broj ručkova: " + a.getBrojRuckova());
        System.out.println("Broj večera: " + a.getBrojVecera());
        
        return true;
    }
    
    @Override
    public String getOpis() {
        return "Ispis turističkog aranžmana po oznaci";
    }
}