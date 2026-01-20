package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * Komanda za postavljanje redoslijeda ispisa.
 * Format: IP [N|S]
 * N - kronološki redoslijed (stari -> novi)
 * S - obrnuti kronološki redoslijed (novi -> stari)
 */
public class IpKomanda implements Komanda {

    private String redoslijed;

    public IpKomanda(String redoslijed) {
        this.redoslijed = redoslijed;
    }

    @Override
    public boolean izvrsi() {
        System.out.println("IP " + redoslijed);
        
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        
        if (redoslijed.equalsIgnoreCase("N")) {
            agencija.postaviRedoslijedIspisa("NORMALNI");
            System.out.println("Redoslijed ispisa postavljen na: Kronološki (stari -> novi)");
            return true;
        } else if (redoslijed.equalsIgnoreCase("S")) {
            agencija.postaviRedoslijedIspisa("OBRNUTI");
            System.out.println("Redoslijed ispisa postavljen na: Obrnuti kronološki (novi -> stari)");
            return true;
        } else {
            System.err.println("GREŠKA: Nepoznat redoslijed '" + redoslijed + "'!");
            System.err.println("Dopušteni redoslijedi: N (normalni), S (obrnuti)");
            return false;
        }
    }

    @Override
    public String getOpis() {
        return "Postavljanje redoslijeda ispisa";
    }
}