package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lanac;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.strategija.JednaRezervacijaStrategija;

/**
 * ConcreteHandler za --jdr argument (jedna rezervacija po osobi).
 */
public class JdrArgumentHandler extends ApstraktniArgumentHandler {
    
    @Override
    public void obradiArgument(String[] args, int index, KonfiguracijaAplikacije config) {
        if (args[index].equals("--jdr")) {
        	config.postaviJdr();
        } else {
            super.obradiArgument(args, index, config);
        }
    }
}