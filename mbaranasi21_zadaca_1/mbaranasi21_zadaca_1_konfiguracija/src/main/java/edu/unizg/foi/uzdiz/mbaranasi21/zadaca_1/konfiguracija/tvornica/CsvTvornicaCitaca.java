package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.konfiguracija.tvornica;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.konfiguracija.citac.CsvCitacAranzmana;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.konfiguracija.citac.CsvCitacRezervacija;

public class CsvTvornicaCitaca extends TvornicaCitaca{
	/**
     * Kreira CSV čitač aranžmana.
     *
     * @return Nova instanca CSV čitača aranžmana
     */
    @Override
    public CsvCitacAranzmana createCitacAranzmana() {
        return new CsvCitacAranzmana();
    }
    
    /**
     * Kreira CSV čitač rezervacija.
     *
     * @return Nova instanca CSV čitača rezervacija
     */
    @Override
    public CsvCitacRezervacija createCitacRezervacija() {
        return new CsvCitacRezervacija();
    }
}
