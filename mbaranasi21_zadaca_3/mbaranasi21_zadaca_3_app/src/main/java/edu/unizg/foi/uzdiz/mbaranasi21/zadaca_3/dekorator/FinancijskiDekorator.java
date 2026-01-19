package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.dekorator;

import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * ConcreteDecorator - dodaje financijske podatke.
 */
public class FinancijskiDekorator extends IzvjestajDekorator {
    
    private String oznakaAranzmana;
    
    /**
     * Konstruktor.
     */
    public FinancijskiDekorator(IzvjestajKomponenta komponenta, String oznakaAranzmana) {
        super(komponenta);
        this.oznakaAranzmana = oznakaAranzmana;
    }
    
    @Override
    public void generiraj() {
        super.generiraj();
        dodajFinancijskePodatke();
    }
    
    /**
     * Dodaje financijske podatke izvještaju.
     */
    private void dodajFinancijskePodatke() {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        Aranzman aranzman = agencija.dohvatiAranzman(oznakaAranzmana);
        
        if (aranzman == null) {
            return;
        }
        
        List<Rezervacija> rezervacije = agencija.dohvatiRezervacije(oznakaAranzmana);
        
        StringBuilder dodatak = new StringBuilder();
        dodatak.append("───────────────────────────────────────────────────────\n");
        dodatak.append("  FINANCIJSKI PODACI\n");
        dodatak.append("───────────────────────────────────────────────────────\n\n");
        
        izracunajPrihode(dodatak, rezervacije, aranzman);
        izracunajPotencijalnePrihode(dodatak, rezervacije, aranzman);
        
        komponenta = new DekoriranaKomponenta(komponenta, dodatak.toString());
    }
    
    /**
     * Izračunava ostvarene prihode.
     */
    private void izracunajPrihode(StringBuilder dodatak, 
            List<Rezervacija> rezervacije, Aranzman aranzman) {
        long brojAktivnih = 0;
        for (Rezervacija r : rezervacije) {
            if (r.jeAktivna()) {
                brojAktivnih++;
            }
        }
        
        double prihod = brojAktivnih * aranzman.getCijena();
        
        dodatak.append("Ostvareni prihod: ")
               .append(String.format("%.2f EUR", prihod))
               .append(" (").append(brojAktivnih).append(" x ")
               .append(aranzman.getCijena()).append(" EUR)\n");
    }
    
    /**
     * Izračunava potencijalne prihode.
     */
    private void izracunajPotencijalnePrihode(StringBuilder dodatak, 
            List<Rezervacija> rezervacije, Aranzman aranzman) {
        long brojPrimljenih = 0;
        long brojNaCekanju = 0;
        
        for (Rezervacija r : rezervacije) {
            if (r.jePrimljena()) {
                brojPrimljenih++;
            } else if (r.jeNaCekanju()) {
                brojNaCekanju++;
            }
        }
        
        double potencijalniPrihod = (brojPrimljenih + brojNaCekanju) * aranzman.getCijena();
        
        dodatak.append("Potencijalni dodatni prihod: ")
               .append(String.format("%.2f EUR", potencijalniPrihod))
               .append(" (").append(brojPrimljenih + brojNaCekanju)
               .append(" rezervacija)\n");
        
        double maksimalniPrihod = aranzman.getMaksBrojPutnika() * aranzman.getCijena();
        dodatak.append("Maksimalni mogući prihod: ")
               .append(String.format("%.2f EUR", maksimalniPrihod))
               .append("\n\n");
    }
    
    /**
     * Pomoćna klasa za dodavanje dodatnog sadržaja.
     */
    private static class DekoriranaKomponenta implements IzvjestajKomponenta {
        private IzvjestajKomponenta originalna;
        private String dodatniSadrzaj;
        
        public DekoriranaKomponenta(IzvjestajKomponenta originalna, 
                String dodatniSadrzaj) {
            this.originalna = originalna;
            this.dodatniSadrzaj = dodatniSadrzaj;
        }
        
        @Override
        public void generiraj() {
            originalna.generiraj();
        }
        
        @Override
        public String dohvatiSadrzaj() {
            return originalna.dohvatiSadrzaj() + dodatniSadrzaj;
        }
    }
}