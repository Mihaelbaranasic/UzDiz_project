package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.konfiguracija.tvornica;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.konfiguracija.citac.CsvCitacAranzmana;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.konfiguracija.citac.CsvCitacRezervacija;

/**
 * Apstraktna tvornica za kreiranje obitelji čitača različitih formata podataka.
 */
public abstract class TvornicaCitaca {
	/**
     * Kreira čitač aranžmana za odgovarajući format.
     *
     * @return Čitač aranžmana
     */
    public abstract CsvCitacAranzmana createCitacAranzmana();
    
    /**
     * Kreira čitač rezervacija za odgovarajući format.
     *
     * @return Čitač rezervacija
     */
    public abstract CsvCitacRezervacija createCitacRezervacija();
}
