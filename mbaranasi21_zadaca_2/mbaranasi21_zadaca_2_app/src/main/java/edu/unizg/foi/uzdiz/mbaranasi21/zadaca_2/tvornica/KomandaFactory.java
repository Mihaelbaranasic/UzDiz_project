package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica;

import java.util.HashMap;
import java.util.Map;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica.konkretni.*;

/**
 * Factory klasa koja mapira komande na njihove Creator objekte.
 */
public class KomandaFactory {
    
    private static final Map<String, KomandaCreator> CREATORI = new HashMap<>();
    
    static {
        CREATORI.put("ITAK", new ItakKomandaCreator());
        CREATORI.put("ITAP", new ItapKomandaCreator());
        CREATORI.put("ITAC", new ItacKomandaCreator());
        CREATORI.put("IRTA", new IrtaKomandaCreator());
        CREATORI.put("IRO", new IroKomandaCreator());
        CREATORI.put("ORTA", new OrtaKomandaCreator());
        CREATORI.put("DRTA", new DrtaKomandaCreator());
        CREATORI.put("IEI", new IeiKomandaCreator());
        CREATORI.put("ITAS", new ItasKomandaCreator());
        CREATORI.put("OTA", new OtaKomandaCreator());
        CREATORI.put("UP", new UpKomandaCreator());
        CREATORI.put("BP", new BpKomandaCreator());
        CREATORI.put("IP", new IpKomandaCreator());
    }
    
    /**
     * Dohvaća Creator za zadanu komandu.
     */
    public static KomandaCreator dohvatiCreator(String nazivKomande) {
        return CREATORI.get(nazivKomande.toUpperCase());
    }
    
    /**
     * Provjerava postoji li komanda.
     */
    public static boolean postojiKomanda(String nazivKomande) {
        return CREATORI.containsKey(nazivKomande.toUpperCase());
    }
}