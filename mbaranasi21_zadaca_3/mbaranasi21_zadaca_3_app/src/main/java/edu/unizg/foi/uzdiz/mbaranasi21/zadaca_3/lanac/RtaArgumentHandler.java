package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lanac;

/**
 * ConcreteHandler za --rta argument (datoteka rezervacija).
 */
public class RtaArgumentHandler extends ApstraktniArgumentHandler {
    
    @Override
    public void obradiArgument(String[] args, int index, KonfiguracijaAplikacije config) {
        if (args[index].equals("--rta")) {
            if (index + 1 < args.length && !args[index + 1].startsWith("--")) {
                config.setDatotekaRezervacije(args[index + 1]);
            } else {
                System.err.println("GREŠKA: Nedostaje naziv datoteke nakon --rta");
            }
        } else {
            super.obradiArgument(args, index, config);
        }
    }
}