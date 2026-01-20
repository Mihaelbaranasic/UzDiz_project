package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lanac;

/**
 * ConcreteHandler za --ta argument (datoteka aranžmana).
 */
public class TaArgumentHandler extends ApstraktniArgumentHandler {
    
    @Override
    public void obradiArgument(String[] args, int index, KonfiguracijaAplikacije config) {
        if (args[index].equals("--ta")) {
            if (index + 1 < args.length && !args[index + 1].startsWith("--")) {
                config.setDatotekaAranzmani(args[index + 1]);
            } else {
                System.err.println("GREŠKA: Nedostaje naziv datoteke nakon --ta");
            }
        } else {
            super.obradiArgument(args, index, config);
        }
    }
}