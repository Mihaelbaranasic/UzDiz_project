package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lanac;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.strategija.ViseRezervacijaStrategija;

/**
 * ConcreteHandler za --vdr argument (više rezervacija po osobi).
 */
public class VdrArgumentHandler extends ApstraktniArgumentHandler {
    
    @Override
    public void obradiArgument(String[] args, int index, KonfiguracijaAplikacije config) {
        if (args[index].equals("--vdr")) {
        	config.postaviVdr();
        } else {
            super.obradiArgument(args, index, config);
        }
    }
}